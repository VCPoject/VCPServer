package controller;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
 
public class SendMailSSL {
	
		public SendMailSSL() {
	}
		
	public boolean sendMail(String email,String subject, String text){
		Properties props = new Properties();  
        props.put("mail.smtp.host", "smtp.gmail.com");  
        props.put("mail.smtp.auth", "true");  
        props.put("mail.debug", "true");  
        props.put("mail.smtp.port", 25);  
        props.put("mail.smtp.socketFactory.port", 25);  
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.transport.protocol", "smtp");
        Session mailSession = null;

        mailSession = Session.getInstance(props,  
                new javax.mail.Authenticator() {  
            protected PasswordAuthentication getPasswordAuthentication() {  
                return new PasswordAuthentication("vcpserver", "vcpbraude");  
            }  
        });  


        try {

            Transport transport = mailSession.getTransport();

            MimeMessage message = new MimeMessage(mailSession);

            message.setSubject(subject);
            message.setFrom(new InternetAddress("Sample@sample.com"));
            String []to = new String[]{email};
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to[0]));
            String body = text;
            message.setContent(body,"text/html");
            transport.connect();

            transport.sendMessage(message,message.getRecipients(Message.RecipientType.TO));
            transport.close();
        } catch (Exception exception) {
        	exception.printStackTrace();
        }
		return true;
    }
		
	
}
