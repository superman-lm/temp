package com.tool.test;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.tool.common.Constraint;

public class TestJavaMail {
	
	public static void main(String[] args) {
		try {
			Properties prop = new Properties();
			prop.put("mail.smtp.host", "smtp.qq.com");
			prop.put("mail.smtp.auth", "true");
			prop.put("mail.transport.protocol","smtp");
			prop.put("mail.smtp.port", "25");
			Session session = Session.getDefaultInstance(prop, null);
			session.setDebug(true);
			MimeMessage message = new MimeMessage(session);
			InternetAddress fromAddress = new InternetAddress("1062406890@qq.com");
			message.setFrom(fromAddress);
			InternetAddress toAddress = new InternetAddress("wangshengi18n@163.com");
			message.addRecipient(Message.RecipientType.TO,toAddress);
			message.setSubject("Hello JavaMail!!!");
			message.setText("this is test java mail!");
			Transport transport = session.getTransport("smtp"); 
			transport.connect("smtp.qq.com", Constraint.USER_NAME, Constraint.PASSWORD);
			transport.sendMessage(message,  message.getAllRecipients());
			transport.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
