package org.strotmann.mail;
import java.io.File;
import java.util.Date;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.faces.context.FacesContext;
import javax.mail.Authenticator;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.servlet.ServletContext;

import org.apache.commons.io.FileUtils;
import org.strotmann.tcg.Benutzer;
/**
  * A email sender class.
  */
public class MailSender
{
	private ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
	private Benutzer benutzer = (Benutzer)servletContext.getAttribute("Benutzer");
  
 /**
    * "send" method to send the message.
    */
  public void send(InternetAddress[] to, String subject, String body
		  , String attachment1Name, byte[] attachment1, String attachment2Name, byte[] attachment2, String attachment3Name, byte[] attachment3)
  {
    try
    {
      //String fileAttachment = "/home/peter/Desktop/fileUpload.java";
      Properties props = System.getProperties();
      props.put("mail.host", this.benutzer.getMailServer());
      props.put("mail.smtp.auth", "true");
      System.out.println("mailUser:"+benutzer.getMailUser());
      Session session = Session.getDefaultInstance(props, new AutentCorreo(benutzer.getMailUser(), benutzer.getMailPasswort()));
      MimeMessage msg = new MimeMessage(session);
      msg.setFrom(new InternetAddress(benutzer.getMailFrom()));
      
      msg.setSubject(subject);
      msg.setHeader("1und1", "TcgEmail");
      msg.setSentDate(new Date());
      
   // create a MultipartMessage for 2 parts messagePart and attachmentPart
      Multipart multipart = new MimeMultipart();
      
   // Part one is the message part 
      MimeBodyPart messageBodyPart = new MimeBodyPart();
      
   // fill message
      messageBodyPart.setText(body);  
      
   // add message as 1st part   
      multipart.addBodyPart(messageBodyPart);
      
   if (attachment1 != null) {
	   System.out.println("attachment1 angehängt");
		// Part two is attachment1
		MimeBodyPart attachmentPart1 = new MimeBodyPart();
		File fileAttachment1 = new File("dummy1");
		FileUtils.writeByteArrayToFile(fileAttachment1, attachment1);
		DataSource source1 = new FileDataSource(fileAttachment1);
		attachmentPart1.setDataHandler(new DataHandler(source1));
		attachmentPart1.setFileName(attachment1Name);
		multipart.addBodyPart(attachmentPart1);
   }
   if (attachment2 != null) {
	   System.out.println("attachment2 angehängt");
		// Part three is attachment2
		MimeBodyPart attachmentPart2 = new MimeBodyPart();
		File fileAttachment2 = new File("dummy2");
		FileUtils.writeByteArrayToFile(fileAttachment2, attachment2);
		DataSource source2 = new FileDataSource(fileAttachment2);
		attachmentPart2.setDataHandler(new DataHandler(source2));
		attachmentPart2.setFileName(attachment2Name);
		multipart.addBodyPart(attachmentPart2);
   }
   if (attachment3 != null) {
	   System.out.println("attachment3 angehängt");
		// Part four is attachment3
		MimeBodyPart attachmentPart3 = new MimeBodyPart();
		File fileAttachment3 = new File("dummy3");
		FileUtils.writeByteArrayToFile(fileAttachment3, attachment3);
		DataSource source3 = new FileDataSource(fileAttachment3);
		attachmentPart3.setDataHandler(new DataHandler(source3));
		attachmentPart3.setFileName(attachment3Name);
		multipart.addBodyPart(attachmentPart3);
  }
	// Put parts in message
      msg.setContent(multipart);

   // Send the message
      Transport.send(msg, to);
      System.out.println("Message sent OK.");
    }
    catch (Exception ex)
    {
      ex.printStackTrace();
    }
  }
  public static class AutentCorreo extends Authenticator {
	    private PasswordAuthentication autentic;
	    
	    public AutentCorreo(String user, String password) {
	        autentic = new PasswordAuthentication(user, password);
	    }
	    
	    public PasswordAuthentication getPasswordAuthentication() {
	        return autentic;
	    }
  }
}
