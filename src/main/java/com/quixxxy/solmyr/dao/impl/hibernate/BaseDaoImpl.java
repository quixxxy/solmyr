package com.quixxxy.solmyr.dao.impl.hibernate;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.quixxxy.solmyr.dao.BaseDao;

abstract class BaseDaoImpl<E, PK extends Serializable> implements
		BaseDao<E, PK> {

	@Autowired
	private SessionFactory session;

	public void save(E obj) {
		getSession().save(obj);
	}

	@SuppressWarnings("unchecked")
	public E findById(PK id) {
		return (E) getSession().get(getEntityClass(), id);
	}

	@SuppressWarnings("unchecked")
	public E merge(E obj) {
		 return (E) getSession().merge(obj);
	}

	public void delete(E obj) {
		 getSession().delete(obj);
	}
	
	@SuppressWarnings("unchecked")
	public List<E> findAll() {
		return (List<E>) getSession().createQuery("from " + getEntityClass().getName()).list();
	}

	protected abstract Class<E> getEntityClass();

	protected Session getSession() {
		return session.getCurrentSession();
	}

}