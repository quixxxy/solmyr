package com.quixxxy.solmyr.service;

import java.util.List;

import com.quixxxy.solmyr.domain.Rate;

public interface RateService {

	List<Rate> getRates();

	void addRate(Rate rate);

	void deleteRate(Long contactId);
	
}
