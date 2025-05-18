package com.cd.service;

import java.awt.Color;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;

import com.cd.entity.CourceDetails;
import com.cd.model.SearchInputs;
import com.cd.model.SearchOutputs;
import com.cd.reposity.ICourceDetailsRepository;
import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;

public class CourceMgmtServiceImpl implements ICourceMgmtService {

	@Autowired
	private ICourceDetailsRepository repo;

	@Override
	public Set<String> getAllCourseCategories() {
		return repo.getUniqueCourceCategory();
	}

	@Override
	public Set<String> getAllTrainingModes() {
		return repo.getUniqueTrainingModes();
	}

	@Override
	public Set<String> showAllFaculties() {
		return repo.getUniqueFacultyNames();
	}

	@Override
	public List<SearchOutputs> showCourceByFilters(SearchInputs inputs) {
	
		CourceDetails entity=new CourceDetails();
		String category=inputs.getCourseCategory();
		if(category!=null&& !category.equals("") && category.length()!=0)
			entity.setCourseCategory(category);
		String facultyName=inputs.getFacultyName();
		if(facultyName!=null&& !facultyName.equals("") && facultyName.length()!=0)
			entity.setCourseCategory(facultyName);
		String trainingMode=inputs.getTrainingMode();
		if(trainingMode!=null&& !trainingMode.equals("") && trainingMode.length()!=0)
			entity.setCourseCategory(trainingMode);
		
		LocalDateTime startDate=inputs.getStartsOn();
		if(startDate!=null)
			entity.setStartDate(startDate);
		
		Example<CourceDetails> exmple=Example.of(entity);
		List<CourceDetails> listEntities=repo.findAll();
		List<SearchOutputs> listoutputs=new ArrayList();
		listoutputs.forEach(cource->{
			SearchOutputs result=new SearchOutputs();
			BeanUtils.copyProperties(cource,result);
			listoutputs.add(result);
		});
		return listoutputs;
	}

	@Override
	public void generatePdfReport(SearchInputs inputs, HttpServletResponse res) throws Exception {
		List<SearchOutputs> results = showCourceByFilters(inputs);
		Document document = new Document(PageSize.A4);
		PdfWriter.getInstance(document, res.getOutputStream());
		document.open();

		Font font = FontFactory.getFont(FontFactory.TIMES_BOLD);
		font.setSize(30);
		font.setColor(Color.CYAN);

		Paragraph para = new Paragraph("Search Report of Cources", font);
		para.setAlignment(Paragraph.ALIGN_CENTER);
		document.add(para);

		PdfPTable table = new PdfPTable(10);
		table.setWidthPercentage(70);
		table.setWidths(new float[] { 3.0f, 3.0f, 3.0f, 3.0f, 3.0f, 3.0f, 3.0f, 3.0f, 3.0f, 3.0f });
		table.setSpacingBefore(2.0f);

		// prepare heading row cells in the pdf table
		PdfPCell cell = new PdfPCell();
		cell.setBackgroundColor(Color.gray);
		cell.setPadding(5);
		Font cellFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
		cellFont.setColor(Color.BLACK);

		cell.setPhrase(new Phrase("courseID", cellFont));
		table.addCell(cell);
		cell.setPhrase(new Phrase("courseName", cellFont));
		table.addCell(cell);
		cell.setPhrase(new Phrase("Category", cellFont));
		table.addCell(cell);
		cell.setPhrase(new Phrase("facultyName", cellFont));
		table.addCell(cell);
		cell.setPhrase(new Phrase("Location", cellFont));
		table.addCell(cell);
		cell.setPhrase(new Phrase("Fee", cellFont));
		table.addCell(cell);
		cell.setPhrase(new Phrase("Course Status", cellFont));
		table.addCell(cell);
		cell.setPhrase(new Phrase("TrainingMode", cellFont));
		table.addCell(cell);
		cell.setPhrase(new Phrase("adminContant", cellFont));
		table.addCell(cell);
		cell.setPhrase(new Phrase("StartDate", cellFont));
		table.addCell(cell);

		results.forEach(result -> {
			table.addCell(String.valueOf(result.getCourseId()));
			table.addCell(result.getCourseName());
			table.addCell(result.getCourseCategory());
			table.addCell(result.getFacultyName());
			table.addCell(result.getLocation());
			table.addCell(String.valueOf(result.getFee()));
			table.addCell(result.getCourseStatus());
			table.addCell(result.getTrainingMode());
			table.addCell(String.valueOf(result.getAdminContact()));
			table.addCell(result.getStartDate().toString());
		});

		document.add(table);
		document.close();
	}

	@Override
	public void generateExcelReport(SearchInputs inputs, HttpServletResponse res) throws Exception {
		List<CourceDetails> list=repo.findAll();
		//copy  List<CourseDetails> to List<SearchResults>
		List<SearchOutputs> listResults=new ArrayList();
		list.forEach(course->{
			SearchOutputs  result=new SearchOutputs();
			BeanUtils.copyProperties(course, result);
			listResults.add(result);
		});
		
		//create ExcelWorkBook  (apache poi  api)
		  HSSFWorkbook  workbook=new HSSFWorkbook();
		  //create  Sheet in the  Work book
		  HSSFSheet  sheet1=workbook.createSheet("CourseDetails");
		  //create  Heading Row in sheet1
		  HSSFRow   headerRow=sheet1.createRow(0);
		  headerRow.createCell(0).setCellValue("CourseID");
		  headerRow.createCell(1).setCellValue("CourseName");
		  headerRow.createCell(2).setCellValue("Location");
		  headerRow.createCell(3).setCellValue("CouseCategory");
		  headerRow.createCell(4).setCellValue("FacultyName");
		  headerRow.createCell(5).setCellValue("fee");
		  headerRow.createCell(6).setCellValue("adminContact");
		  headerRow.createCell(7).setCellValue("trainingMode");
		  headerRow.createCell(8).setCellValue("startDate");
		  headerRow.createCell(9).setCellValue("CourseStatus");
		  //add  data rows to the sheet
		  int i=1;
		  for(SearchOutputs result:listResults) {
			  HSSFRow   dataRow=sheet1.createRow(i);
			  dataRow.createCell(0).setCellValue(result.getCourseId());
			  dataRow.createCell(1).setCellValue(result.getCourseName());
			  dataRow.createCell(2).setCellValue(result.getLocation());
			  dataRow.createCell(3).setCellValue(result.getCourseCategory());
			  dataRow.createCell(4).setCellValue(result.getFacultyName());
			  dataRow.createCell(5).setCellValue(result.getFee());
			  dataRow.createCell(6).setCellValue(result.getAdminContact());
			  dataRow.createCell(7).setCellValue(result.getTrainingMode());
			  dataRow.createCell(8).setCellValue(result.getStartDate());
			  dataRow.createCell(9).setCellValue(result.getCourseStatus());
			  i++;
		  }
		  
		  //get OutputStream  pointing to  response obj
		  ServletOutputStream  outputStream=res.getOutputStream();
		  // write the Excel work book  data  response  object using the above stream
		  workbook.write(outputStream);
		  //close  the stream
		  outputStream.close();
		  workbook.close();
	}

	@Override
	public void generatePdfReportAllData(HttpServletResponse res) throws Exception {
		// get All the records from Db table
		List<CourceDetails> list = repo.findAll();
		// copy List<CourseDetails> to List<SearchResults>
		List<SearchOutputs> listResults = new ArrayList();
		list.forEach(course -> {
			SearchOutputs result = new SearchOutputs();
			BeanUtils.copyProperties(course, result);
			listResults.add(result);
		});
// create Document  obj (openPdf)
		Document document = new Document(PageSize.A4);
//get  PdfWriter  to  write  to the document and response obj
		PdfWriter.getInstance(document, res.getOutputStream());
//open the document
		document.open();
//Define  Font  for the Paragraph
		Font font = FontFactory.getFont(FontFactory.TIMES_BOLD);
		font.setSize(30);
		font.setColor(Color.CYAN);

//create the paragraph having  content  and above font  style
		Paragraph para = new Paragraph("Search Report of Courses", font);
		para.setAlignment(Paragraph.ALIGN_CENTER);
//add paragraph  to  document
		document.add(para);

//  Display search results as the pdf table
		PdfPTable table = new PdfPTable(10);
		table.setWidthPercentage(70);
		table.setWidths(new float[] { 3.0f, 3.0f, 3.0f, 3.0f, 3.0f, 3.0f, 3.0f, 3.0f, 3.0f, 3.0f });
		table.setSpacingBefore(2.0f);

//prepare  heading  row cells  in the pdf table
		PdfPCell cell = new PdfPCell();
		cell.setBackgroundColor(Color.gray);
		cell.setPadding(5);
		Font cellFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
		cellFont.setColor(Color.BLACK);

		cell.setPhrase(new Phrase("courseID", cellFont));
		table.addCell(cell);
		cell.setPhrase(new Phrase("courseName", cellFont));
		table.addCell(cell);
		cell.setPhrase(new Phrase("Category", cellFont));
		table.addCell(cell);
		cell.setPhrase(new Phrase("facultyName", cellFont));
		table.addCell(cell);
		cell.setPhrase(new Phrase("Location", cellFont));
		table.addCell(cell);
		cell.setPhrase(new Phrase("Fee", cellFont));
		table.addCell(cell);
		cell.setPhrase(new Phrase("Course Status", cellFont));
		table.addCell(cell);
		cell.setPhrase(new Phrase("TrainingMode", cellFont));
		table.addCell(cell);
		cell.setPhrase(new Phrase("adminContant", cellFont));
		table.addCell(cell);
		cell.setPhrase(new Phrase("StartDate", cellFont));
		table.addCell(cell);

// add   data cells  to   pdftable
		listResults.forEach(result -> {
			table.addCell(String.valueOf(result.getCourseId()));
			table.addCell(result.getCourseName());
			table.addCell(result.getCourseCategory());
			table.addCell(result.getFacultyName());
			table.addCell(result.getLocation());
			table.addCell(String.valueOf(result.getFee()));
			table.addCell(result.getCourseStatus());
			table.addCell(result.getTrainingMode());
			table.addCell(String.valueOf(result.getAdminContact()));
			table.addCell(result.getStartDate().toString());
		});
// add table  to  document
		document.add(table);
//close the document
		document.close();

	}

	@Override
	public void generateExcelReportAllData(HttpServletResponse res) throws Exception {
		List<CourceDetails> list = repo.findAll();
		// copy List<CourseDetails> to List<SearchResults>
		List<SearchOutputs> listResults = new ArrayList();
		list.forEach(course -> {
			SearchOutputs result = new SearchOutputs();
			BeanUtils.copyProperties(course, result);
			listResults.add(result);
		});
// create Document  obj (openPdf)
		Document document = new Document(PageSize.A4);
//get  PdfWriter  to  write  to the document and response obj
		PdfWriter.getInstance(document, res.getOutputStream());
//open the document
		document.open();
//Define  Font  for the Paragraph
		Font font = FontFactory.getFont(FontFactory.TIMES_BOLD);
		font.setSize(30);
		font.setColor(Color.CYAN);

//create the paragraph having  content  and above font  style
		Paragraph para = new Paragraph("Search Report of Courses", font);
		para.setAlignment(Paragraph.ALIGN_CENTER);
//add paragraph  to  document
		document.add(para);

//  Display search results as the pdf table
		PdfPTable table = new PdfPTable(10);
		table.setWidthPercentage(70);
		table.setWidths(new float[] { 3.0f, 3.0f, 3.0f, 3.0f, 3.0f, 3.0f, 3.0f, 3.0f, 3.0f, 3.0f });
		table.setSpacingBefore(2.0f);

//prepare  heading  row cells  in the pdf table
		PdfPCell cell = new PdfPCell();
		cell.setBackgroundColor(Color.gray);
		cell.setPadding(5);
		Font cellFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
		cellFont.setColor(Color.BLACK);

		cell.setPhrase(new Phrase("courseID", cellFont));
		table.addCell(cell);
		cell.setPhrase(new Phrase("courseName", cellFont));
		table.addCell(cell);
		cell.setPhrase(new Phrase("Category", cellFont));
		table.addCell(cell);
		cell.setPhrase(new Phrase("facultyName", cellFont));
		table.addCell(cell);
		cell.setPhrase(new Phrase("Location", cellFont));
		table.addCell(cell);
		cell.setPhrase(new Phrase("Fee", cellFont));
		table.addCell(cell);
		cell.setPhrase(new Phrase("Course Status", cellFont));
		table.addCell(cell);
		cell.setPhrase(new Phrase("TrainingMode", cellFont));
		table.addCell(cell);
		cell.setPhrase(new Phrase("adminContant", cellFont));
		table.addCell(cell);
		cell.setPhrase(new Phrase("StartDate", cellFont));
		table.addCell(cell);

// add   data cells  to   pdftable
		listResults.forEach(result -> {
			table.addCell(String.valueOf(result.getCourseId()));
			table.addCell(result.getCourseName());
			table.addCell(result.getCourseCategory());
			table.addCell(result.getFacultyName());
			table.addCell(result.getLocation());
			table.addCell(String.valueOf(result.getFee()));
			table.addCell(result.getCourseStatus());
			table.addCell(result.getTrainingMode());
			table.addCell(String.valueOf(result.getAdminContact()));
			table.addCell(result.getStartDate().toString());
		});
// add table  to  document
		document.add(table);
//close the document
		document.close();

	}

}
