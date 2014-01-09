package com.quixxxy.solmyr.dao;

import java.util.List;

import com.quixxxy.solmyr.domain.Quote;

public interface QuoteDao {

	List<Quote> getQuotes(int startFrom, int limit);

	Quote addQuote(Quote quote);

	Quote getQuote(Long quoteId);

	List<Quote> findQuotes(String quoteText);

	List<Quote> findAllQuotes();

	Long getQoutesCount();
	
	Quote updateQuote(Quote quote);

	Quote getQuoteByHash(String quoteHash);
}
