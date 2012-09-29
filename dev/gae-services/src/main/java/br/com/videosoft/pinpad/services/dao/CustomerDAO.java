package br.com.videosoft.pinpad.services.dao;

import br.com.videosoft.pinpad.domain.entities.Customer;
import br.com.videosoft.pinpad.services.dao.impl.Repository;

public interface CustomerDAO extends Repository<Customer>{

	void removeByName(String name);

	Customer findByName(String name);
	
}
