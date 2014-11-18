package gui;

import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.mail.PasswordAuthentication;

/**
 * Class for connecting to Gmail server
 *
 */

public class SendEmail extends Thread{	
	
	private String to, subject, messageBody;
	
	public SendEmail(String to, String subject, String messageBody) {
		this.to = to;
		this.subject = subject;
		this.messageBody = messageBody;
	}
	
	/**
	 * Sends the email
	 * <p></p>
	 * @param to the email it is being sent to
	 * @param subject 
	 * @param messageBody
	 * @return
	 */
	public boolean sendEmail(String to, String subject, String messageBody){
		System.out.println(to);
		System.out.println(subject);
		System.out.println(messageBody);
			Properties props = new Properties();
		//These are all settings so that we can use the google smtp server
	      props.put("mail.smtp.auth", "true");
	      props.put("mail.smtp.starttls.enable", "true");
	      props.put("mail.smtp.host", "smtp.gmail.com");
	      props.put("mail.smtp.port", "587");
	      
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
	
	/**
	 * Closes the mailthread
	 */
	private void closeThread(){
		this.stop();
	}

	@Override
	public void run() {
		sendEmail(to, subject, messageBody);
		closeThread();
	}
}