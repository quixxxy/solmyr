package com.quixxxy.solmyr.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.quixxxy.solmyr.dao.RateDao;
import com.quixxxy.solmyr.domain.Rate;
import com.quixxxy.solmyr.service.RateService;

@Service
@Transactional("jpaTransactionManager")
public class RateServiceImpl implements RateService {
	
	@Autowired
	@Qualifier("jpaRateDaoImpl")
	private RateDao rateDao;
	
	@Cacheable(value = { "rates" })
	@Transactional(readOnly = true)
	public List<Rate> getRates() {
		return rateDao.getRates();
	}

	public void addRate(Rate rate) {
		rateDao.addRate(rate);
	}

	public void deleteRate(Long rateId) {
		rateDao.deleteRate(rateId);
	}
	
	public void setRateDao(RateDao rateDao) {
		this.rateDao = rateDao;
	}

	
}
