package com.quixxxy.solmyr.dao.impl.hibernate;

import com.quixxxy.solmyr.dao.BaseDao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.List;

abstract class BaseDaoImpl<E, PK extends Serializable> implements BaseDao<E, PK> {

    @Autowired
    private SessionFactory session;

    public void save(E obj) {
        getSession().save(obj);
    }

    public E findById(PK id) {
        return (E) getSession().get(getEntityClass(), id);
    }

    public E merge(E obj) {
        return (E) getSession().merge(obj);
    }

    public void delete(E obj) {
        getSession().delete(obj);
    }

    public List<E> findAll() {
        return getSession().createQuery("from " + getEntityClass().getName()).list();
    }

    protected abstract Class<E> getEntityClass();

    protected Session getSession() {
        return session.getCurrentSession();
    }

}
