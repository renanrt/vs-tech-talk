package com.mkyong.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.com.videosoft.pinpad.domain.entities.Customer;
import br.com.videosoft.pinpad.services.spec.CustomerService;

@Controller
@RequestMapping("/customer")
public class CustomerController {
	
	@Autowired
	private CustomerService service;
	
	
	@RequestMapping(value="/addCustomerPage", method = RequestMethod.GET)
	public String getAddCustomerPage(ModelMap model) {
		return "add";
	}
	
	@RequestMapping(value="/add", method = RequestMethod.POST)
	public ModelAndView add(HttpServletRequest request, ModelMap model) {

		String name = request.getParameter("name");
		String email = request.getParameter("email");
		
		
		Customer customer = new Customer();
		customer.setEmail(email);
		customer.setName(name);
		customer.setDate(new Date());
		service.saveNewCustomer(customer);
		return new ModelAndView("redirect:list");
        
	}
		
	@RequestMapping(value="/update/{name}", method = RequestMethod.GET)
	public String getUpdateCustomerPage(@PathVariable String name, 
			HttpServletRequest request, ModelMap model) {

		Customer customer = service.findCustomerByName(name);
		model.addAttribute("customer",  customer);
		return "update";

	}
	
	@RequestMapping(value="/update", method = RequestMethod.POST)
	public ModelAndView update(HttpServletRequest request, ModelMap model) {

		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String originalName =  request.getParameter("originalName");
		
		Customer customer = service.findCustomerByName(originalName);
		customer.setEmail(email);
		customer.setName(name);
		customer.setDate(new Date());
		service.updateCustomer(customer);
		
        return new ModelAndView("redirect:list");
        
	}
		
	@RequestMapping(value="/delete/{name}", method = RequestMethod.GET)
	public ModelAndView delete(@PathVariable String name,
			HttpServletRequest request, ModelMap model) {

		service.removeCustomerByName(name);
        return new ModelAndView("redirect:../list");
        
	}
	
	
	//get all customers
	@RequestMapping(value="/list", method = RequestMethod.GET)
	public String listCustomer(ModelMap model) {
		List<Customer> customers = service.findAllCustomers(); 
	    model.addAttribute("customerList",  customers);
		return "list";

	}
	
}