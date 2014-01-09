package com.quixxxy.solmyr.dao.impl.jpa;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.quixxxy.solmyr.dao.RateDao;
import com.quixxxy.solmyr.domain.Rate;

@Repository
public class JpaRateDaoImpl extends JpaBaseDaoImpl<Rate, Long> implements RateDao {
	
	public List<Rate> getRates() {
		return findAll();
	}

	public Rate getRate(Long rateId) {
		return findById(rateId);
	}

	public Rate addRate(Rate rate) {
		return merge(rate);
	}

	public void deleteRate(Long rateId) {
		delete(getRate(rateId));
	}

	@Override
	protected Class<Rate> getEntityClass() {
		return Rate.class;
	}

}
