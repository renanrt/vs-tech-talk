package br.com.videosoft.pinpad.domain.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.google.appengine.api.datastore.Key;

@Entity
public class Customer implements Serializable {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Key id;

	private String name;
	
	private String email;
	
	private String message;
	
	private Date date;

	public Customer() {
	}
	
	public String getEmail() {
		return email;
	}
	
	public Key getId() {
		return id;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
	public String getMessage() {
		return message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
}