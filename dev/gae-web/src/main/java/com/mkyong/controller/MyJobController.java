package com.mkyong.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.com.videosoft.pinpad.services.spec.EmailService;

@Controller
@RequestMapping("/jobs")
public class MyJobController {
	
	@Autowired
	private EmailService service;
	
	
	@RequestMapping(value="/everyminute", method = RequestMethod.GET)
	public String runJobTest(ModelMap model) {
		System.out.println("Fake job is runnin");
		service.sendEmailToUsers();
		return "add";
	}
		
}