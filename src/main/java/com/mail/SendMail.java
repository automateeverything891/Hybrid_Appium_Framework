package com.mail;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.Map.Entry;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import com.basetest.TestBase;
import com.config.VariableConstants;

public class SendMail extends TestBase{

	public static void custom_Mail() {

        String to = VariableConstants.to_mail;

        String from = VariableConstants.from_mail;

        String host = "smtp.gmail.com";

        Properties properties = System.getProperties();

        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
       
        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {

            @Override
			protected PasswordAuthentication getPasswordAuthentication() {

                return new PasswordAuthentication(VariableConstants.from_mail, VariableConstants.password);

            }

        });

      
        session.setDebug(true);

        try {
           
            MimeMessage message = new MimeMessage(session);
 
            message.setFrom(new InternetAddress(from));
            
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            
            message.addRecipients(Message.RecipientType.CC, VariableConstants.cc_mails);
           
            message.setSubject("AUTOMATION TEST CASE EXECUTION REPORT!!!");
            
            MimeBodyPart mimebody1 = new MimeBodyPart();
            mimebody1.setText("Dear Sir/Madam, \n"+
            		"<p>Automation test suite has been executed successfully. Here is the summary report,</p>\n"+
            		"<html>\r\n" + 
            		"<head>\r\n" + 
            		"<style>\r\n" + 
            		"table, th, td {\r\n" + 
            		"  border: 1px solid black;\r\n" + 
            		"  border-collapse: collapse;\r\n" + 
            		"}\r\n" + 
            		"th, td {\r\n" + 
            		"  padding: 5px;\r\n" + 
            		"  text-align: left;\r\n" + 
            		"}\r\n" + 
            		"</style>\r\n" + 
            		"</head>\r\n" + 
            		"<body>\r\n" + 
            		"\r\n" +
            		"<table style=\"width:100%\">\r\n" + 
            		"  <caption><b>TEST EXECUTION OVERVIEW</b></caption>\r\n" + 
            		"  <tr>\r\n" + 
            		"    <th style=\"color:purple;\"><b> NO.OF TEST CASES </b></th>\r\n" + 
            		"    <th style=\"color:green;\"><b>NO.OF PASS TC </b></th>\r\n" + 
            		"    <th style=\"color:red;\"><b>NO.OF FAIL TC</b> </th>\r\n" +
            		"    <th style=\"color:orange;\"><b>NO.OF SKIP TC</b> </th>\r\n" +
            		"  </tr>\r\n" + 
            		"  <tr>\r\n "+
            		tableHeader()+"</table>\r\n \n " +
            		"<table style=\"width:100%\">\r\n" + 
            		"  <caption><b>TEST SUMMARY</b></caption>\r\n" + 
            		"  <tr>\r\n" + 
            		"    <th style=\"color:brown;\"><b>TESTCASE NAME</b></th>\r\n" + 
            		"    <th style=\"color:brown;\"><b>STATUS</b></th>\r\n" + 
            		"  </tr>\r\n" + 
            		table().toString().replace("[", "").replace("]", "").replace(",", "")+"</table> \r \n" + 
            		"\r\n" + 
            		"</body>\r\n" + 
            		"</html>\r\n"+
            		"<p> For more details of the test execution report.Please find below attachment,</p>\n"+
            		"\n"+
            		"\n"+
            		"\n"+
            		"\n"+
            		"\n"+
            		"Thanks & Regards, \n"+
            		"<b>Testing Team</b>","UTF-8","html");
            
           

            MimeBodyPart mimebody = new MimeBodyPart();
        
           
            
            try {
				mimebody.attachFile(System.getProperty("user.dir")+"\\src\\main\\java\\com\\report\\extentreport.html");
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            
            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(mimebody);
            multipart.addBodyPart(mimebody1);
            
            message.setContent(multipart);            
           
            Transport.send(message);
            
            System.out.println("Sent message successfully!!!!!!!!");
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }

    }

	private static String tableHeader() {
		
		 Set<Entry<String, String>> testentries = testcasesummary.entrySet(); 
		  
		  int pass = 0;
		  int fail = 0;
		  int skip = 0;
		  
		  for(Entry<String, String> entry:testentries) 
		  {
			  if(entry.getValue().equalsIgnoreCase("Pass"))
				  pass++;
			  else if(entry.getValue().equalsIgnoreCase("Fail"))
				  fail++;
			  else if(entry.getValue().equalsIgnoreCase("Skip"))
				  skip++;
			  
			  } 
		  return " <td style=\"color:purple;\"> <b>"+testentries.size()+"</b></td>\r\n <td style=\"color:green;\"> <b>"+String.valueOf(pass)+"</b></td>\r\n <td style=\"color:red;\"><b>"+String.valueOf(fail)+"</b></td> "
		  		+ "<td style=\"color:orange;\"> <b>"+String.valueOf(skip)+"</b> </td> "
		  				+ "</tr>";
		  
	}

	private static List<String> table() {
	
		
		  Set<Entry<String, String>> testentries = testcasesummary.entrySet(); 
		  List<String> string = new ArrayList<String>();
		  for(Entry<String, String> entry:testentries) 
		  {
			  if(entry.getValue().equalsIgnoreCase("Pass")) {
				  
				  string.add(" <tr> <td><b>"+entry.getKey()+"<b></td>\r\n <td style=\"color:green;\"><b>"+entry.getValue()+"</b> </td>\r\n </tr>\r\n"); 
				  
			  }
			  else if(entry.getValue().equalsIgnoreCase("Fail")) {
				  
				  string.add(" <tr> <td><b>"+entry.getKey()+"<b></td>\r\n <td style=\"color:red;\"><b>"+entry.getValue()+"</b> </td>\r\n </tr>\r\n"); 
				  
			  }
			  else if(entry.getValue().equalsIgnoreCase("Skip")) {
				  
				  string.add(" <tr> <td><b>"+entry.getKey()+"<b></td>\r\n <td style=\"color:orange;\"><b>"+entry.getValue()+"</b> </td>\r\n </tr>\r\n"); 
				  
			  }
			 
			  } 
	  return string;
		 
 		
	} 
	
	public static void main(String[] args) {
		
		SendMail custom = new SendMail();
		custom.custom_Mail();
	}
	
}
