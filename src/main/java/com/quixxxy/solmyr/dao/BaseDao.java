package com.quixxxy.solmyr.dao;

import java.io.Serializable;
import java.util.List;

public interface BaseDao<E, PK extends Serializable> {
	
	void save(E obj);

	E merge(E obj);

	void delete(E obj);

	E findById(PK id);
	
	List<E> findAll();

}