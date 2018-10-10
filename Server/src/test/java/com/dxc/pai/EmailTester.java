package com.dxc.pai;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


public class EmailTester {
	private final transient Properties props = System.getProperties();
	private transient Authenticator authenticator;
	private transient Session session;
	public EmailTester() {
		props.put("mail.smtp.auth", "false");
		props.put("mail.smtp.host", "127.0.0.1");
		session = Session.getInstance(props);
	}
	public void send(String recipient, String subject, String content)throws AddressException, MessagingException {
		final MimeMessage message = new MimeMessage(session);
		message.setFrom(new InternetAddress("MyOpp@pai.com"));
		if(recipient!=null&&recipient.indexOf(";")!=-1){
			String[] rec = recipient.split(";");
			int len = rec.length;
			InternetAddress[] recaddr = new InternetAddress[len];
			for(int i=0; i<len; i++){
				recaddr[i] =  new InternetAddress(rec[i]);
			}
			message.setRecipients(RecipientType.TO, recaddr);
		}else {
			message.setRecipient(RecipientType.TO, new InternetAddress(recipient));
		}
		message.setSubject(subject);
		message.setContent(content.toString(), "text/html;charset=utf-8");
		Transport.send(message);
		
	}
	
	
	
	public static void main(String[] args) {
		try {
			new EmailTester().send("changying.wen@dxc.com", "Hello by java", "hahahahaha");
		} catch (AddressException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
