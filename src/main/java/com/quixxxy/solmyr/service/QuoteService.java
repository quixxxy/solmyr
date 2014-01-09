package com.quixxxy.solmyr.service;

import java.util.List;

import com.quixxxy.solmyr.domain.Pagination;
import com.quixxxy.solmyr.domain.Quote;

public interface QuoteService {

	List<Quote> getQuotes(int startFrom, int limit);

	Quote addQuote(Quote quote);

	Quote getQuote(Long quoteId);

	List<Quote> findQuotes(String quoteText);

	Pagination<Quote> getPaginatedQuotes(int pageNumber, int quotesPerPage);

	Long updateRating(Long quoteId, String value);

	Quote getQuoteByHash(String quoteHash);

}
