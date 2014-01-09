package com.quixxxy.solmyr.dao.impl.hibernate;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.quixxxy.solmyr.dao.RateDao;
import com.quixxxy.solmyr.domain.Rate;

@Repository
public class RateDaoImpl extends BaseDaoImpl<Rate, Long> implements RateDao {

	@SuppressWarnings("unchecked")
	public List<Rate> getRates() {
		return getSession().createQuery("from Rate").list();
	}

	public void deleteRate(Long rateId) {
		Rate rate = findById(rateId);
		if (null != rate) {
			delete(rate);
		}
	}
	
	public Rate getRate(Long rateId) {
		return findById(rateId);
	}
	
	public Rate addRate(Rate rate) {
		return merge(rate);
	}

	@Override
	protected Class<Rate> getEntityClass() {
		return Rate.class;
	}
}
