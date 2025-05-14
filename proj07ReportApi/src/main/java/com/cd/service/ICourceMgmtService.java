package com.cd.service;

import java.util.List;
import java.util.Set;

import com.cd.model.SearchInputs;
import com.cd.model.SearchOutputs;

import jakarta.servlet.http.HttpServletResponse;

public interface ICourceMgmtService {

	public Set<String> getAllCourseCategories();
	public Set<String> getAllTrainingModes();
	public Set<String> showAllFaculties();
	
	public List<SearchOutputs> showCourceByFilters(SearchInputs inputs);
	
	public void generatePdfReport(SearchInputs inputs,HttpServletResponse res)throws Exception;
	public void generateExcelReport(SearchInputs inputs,HttpServletResponse res)throws Exception;
	
	public     void    generatePdfReportAllData(HttpServletResponse  res)throws Exception;
    public     void    generateExcelReportAllData(HttpServletResponse  res)throws Exception;
}
