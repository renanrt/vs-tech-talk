package br.com.videosoft.pinpad.web.mbeans;

import java.util.Date;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.videosoft.pinpad.domain.entities.Customer;
import br.com.videosoft.pinpad.services.spec.CustomerService;

@Component("contactsMBean")
@ManagedBean
@SessionScoped
//@Scope("request")
public class ContactsMBean {

	private String fullName = "";
	
	private String email = "";
	private String message = "";
	
	private Date date = new Date();
	private Long b2wOrderId;
	
	@Autowired
	private CustomerService service;
	
	public String send(){
		System.out.println("Enviando informacoes");

		Customer customer = new Customer();
		customer.setEmail(email);
		customer.setName(fullName);
		customer.setMessage(message);
		customer.setDate(new Date());
		
		service.saveNewCustomer(customer);
		
		
		resetFields();
		return "contacts_sent";
	}


	private void resetFields() {
		this.fullName = "";
		this.email = "";
	}
	
	
	public String clear(){
		System.out.println("Clear");
		fullName = "";
		return null;
	}
	
	
	public Date getDate() {
		return date;
	}
	
	public void setDate(Date date) {
		this.date = date;
	}

	public Long getB2wOrderId() {
		return b2wOrderId;
	}

	public void setB2wOrderId(Long b2wOrderId) {
		if (b2wOrderId > 0) {
			this.b2wOrderId = b2wOrderId;
		} else {
			this.b2wOrderId = null;
		}
	}

	public String getFullName() {
		return fullName;
	}
	
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getMessage() {
		return message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
}