package br.com.videosoft.pinpad.services.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.videosoft.pinpad.domain.entities.Customer;
import br.com.videosoft.pinpad.services.dao.CustomerDAO;
import br.com.videosoft.pinpad.services.spec.CustomerService;

@Service("orderService")
public class CustomerServiceImpl extends AbstractService implements CustomerService{
	
	private static final Logger LOGGER = Logger.getLogger(CustomerServiceImpl.class);

	@Autowired
	private CustomerDAO dao;
	
	public List<Customer> findAllCustomers() {
		return dao.findAll();
	}

	@Override
	public void removeCustomerByName(String name) {
		dao.removeByName(name);
	}

	@Override
	public void saveNewCustomer(Customer customer) {
		dao.persist(customer);
	}

	@Override
	public Customer findCustomerByName(String name) {
		return dao.findByName(name);
	}

	@Override
	public void updateCustomer(Customer customer) {
		dao.merge(customer);
	}
}