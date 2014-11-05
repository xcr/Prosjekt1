package application;

import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.mail.PasswordAuthentication;

public class SendEmail
{
	public SendEmail() {
		// TODO Auto-generated constructor stub
	}
	public static boolean sendEmail(String to, String subject, String messageBody){
		System.out.println(to);
		System.out.println(subject);
		System.out.println(messageBody);
			Properties props = new Properties();
	      props.put("mail.smtp.auth", "true");
	      props.put("mail.smtp.starttls.enable", "true");
	      props.put("mail.smtp.host", "smtp.gmail.com");
	      props.put("mail.smtp.port", "587");
	      /*These are all settings so that we can use the google smtp server*/
	      
	      
	      Session session = Session.getInstance(props,
	    		  new javax.mail.Authenticator() {
	    			protected PasswordAuthentication getPasswordAuthentication() {
	    				return new PasswordAuthentication("koieadm1n@gmail.com", "koie1234");
	    				//Try to login in so we can use the gmail smtp server.
	    			}
	    		  });
	     
	      try{
	         // Create a default MimeMessage object.
	         MimeMessage message = new MimeMessage(session);

	         // Set From: header field of the header.
	         message.setFrom(new InternetAddress("from-admin@koier.no"));

	         // Set To: header field of the header.
	         message.setRecipients(Message.RecipientType.TO,
	 				InternetAddress.parse(to));

	         // Set Subject: header field
	         message.setSubject(subject);

	         // Now set the actual message
	         message.setText(messageBody);

	         // Send message
	         Transport.send(message);
	         System.out.println("Sent message successfully....");
	         return true;
	      }catch (MessagingException mex) {
	    	  System.out.println(mex);
	         mex.printStackTrace();
	    	  return false;
	      }
	}
   public static void main(String [] args)
   {    

      
   }
}