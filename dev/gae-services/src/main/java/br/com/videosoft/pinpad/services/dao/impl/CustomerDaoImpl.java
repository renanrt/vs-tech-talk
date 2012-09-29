package br.com.videosoft.pinpad.services.dao.impl;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import br.com.videosoft.pinpad.domain.entities.Customer;
import br.com.videosoft.pinpad.services.dao.CustomerDAO;

@Repository
public class CustomerDaoImpl extends AbstractJPARepository<Customer> implements CustomerDAO {

	protected CustomerDaoImpl() {
		super(Customer.class);
	}

	public Customer findByName(String name){
		EntityManager em = EMF.get().createEntityManager();
		Query qry = em.createQuery("select from " + Customer.class.getName() + " c where c.name = :name");
		qry.setParameter("name", name);
		Customer customer = (Customer) qry.getSingleResult();
		em.close();
		return customer;
	}
	
	public void removeByName(String name){
		EntityManager em = EMF.get().createEntityManager();
		Query qry = em.createQuery("select from " + Customer.class.getName() + " c where c.name = :name");
		qry.setParameter("name", name);
		Customer customer = (Customer) qry.getSingleResult();
		em.remove(customer);
		em.close();
	}
}