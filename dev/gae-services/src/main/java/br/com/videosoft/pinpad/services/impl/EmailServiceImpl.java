package br.com.videosoft.pinpad.services.impl;

import java.util.List;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.videosoft.pinpad.domain.entities.Customer;
import br.com.videosoft.pinpad.services.dao.CustomerDAO;
import br.com.videosoft.pinpad.services.spec.EmailService;
@Service("mailService")
public class EmailServiceImpl extends AbstractService implements EmailService{
	
	private static final Logger LOGGER = Logger.getLogger(EmailServiceImpl.class);

	@Autowired
	private CustomerDAO dao;

	@Override
	public void sendEmailToUsers() {
		System.out.println("Sending email from emailservice");
		List<Customer> all = dao.findAll();
		
		for (Customer customer : all) {
			System.out.println("sendin email to " + customer.getName());
			if(customer.getEmail().equals("renanrt@gmail.com")){
				sendEmail(customer.getEmail(),customer.getName(),customer.getMessage());
			}
		}
		System.out.println("Finished sending email from emailservice");
		
	}

	private void sendEmail(String recipient, String recipientName, String message) {
		Properties props = new Properties();
        Session session = Session.getDefaultInstance(props, null);

        String msgBody = "...";

        try {
            Message msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress("renanrt@gmail.com", "Example.com Admin"));
            msg.addRecipient(Message.RecipientType.TO,
                             new InternetAddress(recipient, recipientName));
            msg.setSubject("This is an example message. Message: " + message);
            msg.setText(msgBody);
            Transport.send(msg);
    
        } catch (Exception e) {
        	e.printStackTrace();
           LOGGER.error(e);
        } 
	}
	
}