package com.quixxxy.solmyr.feed.service;

import java.util.List;

import com.quixxxy.solmyr.domain.Quote;
import com.quixxxy.solmyr.feed.exception.SolmyrFeedException;

public interface FeedService {
	
	List<Quote> getRemoteQuotes() throws SolmyrFeedException;

}
