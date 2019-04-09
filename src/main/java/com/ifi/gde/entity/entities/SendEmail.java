package com.ifi.gde.entity.entities;

import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;



public class SendEmail {
	 final static String username="gestionsuivipatient@gmail.com";
	 final static String password="binome@10AA";

	public static  void sendEmail(String email/*mail receiver*/,String url/*contenu message*/){
		 
		   
		   try {  
			   
			   String host ="smtp.gmail.com" ;
	            String user = username;
	            String pass = password;
	            String to = email;
	            String from = username;
	            String subject = "Confirmation de suivi de dossier medical";
	            String messageText = url;
	            boolean sessionDebug = false;

	            Properties props = System.getProperties();

	            props.put("mail.smtp.starttls.enable", "true");
	            props.put("mail.smtp.host", host);
	            props.put("mail.smtp.port", "587");
	            props.put("mail.smtp.auth", "true");
	            props.put("mail.smtp.starttls.required", "true");

	            java.security.Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
	            Session mailSession = Session.getDefaultInstance(props, null);
	            mailSession.setDebug(sessionDebug);
	            Message msg = new MimeMessage(mailSession);
	            msg.setFrom(new InternetAddress(from));
	            InternetAddress[] address = {new InternetAddress(to)};
	            msg.setRecipients(Message.RecipientType.TO, address);
	            msg.setSubject(subject); msg.setSentDate(new Date());
	            msg.setText(messageText);

	           Transport transport=mailSession.getTransport("smtp");
	           transport.connect(host, user, pass);
	           transport.sendMessage(msg, msg.getAllRecipients());
	           transport.close();
	           System.out.println("le message à été bien envoyé et réçu!!!"); 
			   
			     } catch (MessagingException e) {e.printStackTrace();}  
			 }  

}

