package br.com.videosoft.pinpad.services.spec;

import java.util.List;

import br.com.videosoft.pinpad.domain.entities.Customer;

public interface CustomerService {

	List<Customer> findAllCustomers();

	void removeCustomerByName(String name);

	void saveNewCustomer(Customer customer);

	Customer findCustomerByName(String name);

	void updateCustomer(Customer customer);
}
