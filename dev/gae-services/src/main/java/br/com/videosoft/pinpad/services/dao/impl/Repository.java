package br.com.videosoft.pinpad.services.dao.impl;

import java.util.List;

import com.google.appengine.api.datastore.Key;

public interface Repository<T> {

	public void delete(Key id);

	public void delete(T entity);

	public List<T> findAll() ;

	public T findById(Key id) ;

	public void persist(T entity);

	public T merge(T entity) ;
	
	public List<T> findByCriteria(EntityCriteria ec) ;
}
