package com.cd.utils;


import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import jakarta.mail.internet.MimeMessage;

@Component
public class EmailUtils {
	
	@Autowired
	private JavaMailSender mailSender;

	public boolean sendEmailMessage(String toMail,String subject,String body) throws Exception{
		boolean mailSentStatus=false;
		try {
			MimeMessage message=mailSender.createMimeMessage();
			MimeMessageHelper helper=new MimeMessageHelper(message, true, "UTF-8");
			helper.setTo(toMail);
			helper.setSentDate(new Date());
			helper.setSubject(subject);
			helper.setText(body,true);
			mailSender.send(message);
			mailSentStatus=true;
		}
		catch (Exception e) {
			throw e;
		}
		return mailSentStatus;
	}
}
