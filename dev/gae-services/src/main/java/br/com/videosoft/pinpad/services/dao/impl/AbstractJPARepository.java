package br.com.videosoft.pinpad.services.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.google.appengine.api.datastore.Key;

public abstract class AbstractJPARepository<T> implements Repository<T> {
	
	private EntityManager em;

    protected Log logger = LogFactory.getLog(getClass());

	protected Class<T> type;

	protected AbstractJPARepository(Class<T> type) {
		this.type  = type;
	}

	public void delete(Key id) {
		em = getEntityManager();
		em.remove(findById(id));
		em.close();
	}

	public void delete(T entity) {
		em.remove(entity);
	}

	@SuppressWarnings("unchecked")
	public List<T> findAll() {
		em = getEntityManager();
		String qryStr = "select from " + type.getName();
		logger.debug(qryStr);
		Query query = em.createQuery(qryStr);
		List<T> resultList = query.getResultList();
		return resultList;
	}

	public T findById(Key id) {
		em = getEntityManager();
		T t = (T) em.find(type, id);
		return t;
	}

	public void persist(T entity) {
		EntityManager em = EMF.get().createEntityManager();
		em.persist(entity);
		em.close();
	}
	
	public T merge(T entity) {
		em = getEntityManager();
		em.merge(entity);
		em.close();
		return entity;
	}
	
	@SuppressWarnings("unchecked")
	public List<T> findByCriteria(EntityCriteria ec) {
		EntityManager em = getEntityManager();
		EntityCriteria checkedEc = (ec == null) ? new EntityCriteria() : ec;
		Query q = new JpaCriteriaConverter(checkedEc, type, em).getQuery();
		return q.getResultList();
	}
	
	public EntityManager getEntityManager() {
		return EMF.getEntityManager();
	}
}