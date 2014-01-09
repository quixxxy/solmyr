package com.quixxxy.solmyr.dao.impl.jpa;

import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import org.springframework.stereotype.Repository;

import com.quixxxy.solmyr.dao.QuoteDao;
import com.quixxxy.solmyr.domain.Quote;

@Repository
public class JpaQuoteDaoImpl extends JpaBaseDaoImpl<Quote, Long> implements
		QuoteDao {

	public List<Quote> getQuotes(int startFrom, int limit) {
		TypedQuery<Quote> query = getEntityManager().createQuery(
				"select quote from Quote quote order by quote.id desc",
				Quote.class);
		return query.setMaxResults(limit).setFirstResult(startFrom)
				.getResultList();
	}

	public Quote getQuote(Long quoteId) {
		return findById(quoteId);
	}

	public Quote addQuote(Quote quote) {
		return merge(quote);
	}

	public List<Quote> findQuotes(String quoteText) {
		TypedQuery<Quote> query = getEntityManager()
				.createQuery(
						"select quote from Quote quote where quote.text like :quoteText order by quote.id desc",
						Quote.class);
		return query.setParameter("quoteText", "%" + quoteText + "%")
				.getResultList();
	}

	public List<Quote> findAllQuotes() {
		return findAll();
	}

	public Long getQoutesCount() {
		CriteriaBuilder qb = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<Long> cq = qb.createQuery(Long.class);
		cq.select(qb.count(cq.from(Quote.class)));
		return getEntityManager().createQuery(cq).getSingleResult();
	}

	public Quote getQuoteByHash(String quoteHash) {
		TypedQuery<Quote> query = getEntityManager()
				.createQuery(
						"select quote from Quote quote where quote.quoteHash = :quoteHash", Quote.class)
				.setParameter("quoteHash", quoteHash);
		return query.getSingleResult();
	}

	@Override
	protected Class<Quote> getEntityClass() {
		return Quote.class;
	}

	public Quote updateQuote(Quote quote) {
		return merge(quote);
	}

}
