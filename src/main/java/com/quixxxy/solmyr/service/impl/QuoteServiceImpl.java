package com.quixxxy.solmyr.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.quixxxy.solmyr.dao.QuoteDao;
import com.quixxxy.solmyr.domain.Pagination;
import com.quixxxy.solmyr.domain.Quote;
import com.quixxxy.solmyr.service.QuoteService;
import com.quixxxy.solmyr.util.QuoteVote;

@Service
@Transactional(readOnly = true)
public class QuoteServiceImpl implements QuoteService {

	@Autowired
	@Qualifier("quoteDaoImpl")
	private QuoteDao quoteDao;
	
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	public List<Quote> getQuotes(int startFrom, int limit) {
		return quoteDao.getQuotes(startFrom, limit);
	}

	@Transactional
	public Quote addQuote(Quote quote) {
		Quote quoteFromDb = getQuoteByHash(quote.getQuoteHash());
		if (quoteFromDb == null) {
			quote = quoteDao.addQuote(quote);
			logger.info("New quoute was add {}", quote);
			return quote; 
		} else {
			logger.info("Quote already exists {}, ignore it", quoteFromDb);			
		}
		return quote;

	}

	public Quote getQuote(Long quoteId) {
		return quoteDao.getQuote(quoteId);
	}
	
	public List<Quote> findQuotes(String quoteText) {
		return quoteDao.findQuotes(quoteText);
	}
	
	public Pagination<Quote> getPaginatedQuotes(int pageNumber, int quotesPerPage) {		
		long quotesNumber = quoteDao.getQoutesCount();
		int i = pageNumber * quotesPerPage;
		List<Quote> quotes = quoteDao.getQuotes(i - quotesPerPage, i);
		return new Pagination<Quote>(quotes, quotesPerPage, quotesNumber, pageNumber);
	}

	@Transactional
	public Long updateRating(Long quoteId, String value) {
		QuoteVote vote = QuoteVote.getQuoteVote(value);	
		Quote quote = quoteDao.getQuote(quoteId);
		Long rating = quote.getRating();
		switch (vote) {
			case VOTE_YES: {
				quote.setRating(++rating);
				break;
			}
			case VOTE_NO: {
				quote.setRating(--rating);
				break;
			}
			case VOTE_BAYAN: {
				//TODO Add bayan implementation
				break;
			}
		}
		return quoteDao.updateQuote(quote).getRating();
	}

	public Quote getQuoteByHash(String quoteHash) {
		return quoteDao.getQuoteByHash(quoteHash);
	}
}
