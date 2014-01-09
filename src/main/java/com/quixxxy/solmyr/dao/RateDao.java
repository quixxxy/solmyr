package com.quixxxy.solmyr.dao;

import java.util.List;

import com.quixxxy.solmyr.domain.Rate;

public interface RateDao {

	List<Rate> getRates();
	
	Rate getRate(Long rateId);

	Rate addRate(Rate rate);

	void deleteRate(Long rateId);
	
}
