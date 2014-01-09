package com.quixxxy.solmyr.dao.impl.jpa;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.quixxxy.solmyr.dao.BaseDao;

abstract class JpaBaseDaoImpl<E, PK extends Serializable> implements
		BaseDao<E, PK> {

	@PersistenceContext(unitName="JpaRate")
	private EntityManager em;

	public void save(E obj) {
		getEntityManager().persist(obj);
	}

	public E findById(PK id) {
		return getEntityManager().find(getEntityClass(), id);
	}

	public E merge(E obj) {
		return getEntityManager().merge(obj);
	}

	public void delete(E obj) {
		getEntityManager().remove(obj);
	}

	@SuppressWarnings("unchecked")
	public List<E> findAll() {
		Query query = em.createQuery("SELECT e FROM " + getEntityClass().getName() + " e");
		return (List<E>) query.getResultList();
	}

	protected abstract Class<E> getEntityClass();

	protected EntityManager getEntityManager() {
		return em;
	}

}
