package com.quixxxy.solmyr.dao.impl.hibernate;

import com.quixxxy.solmyr.dao.QuoteDao;
import com.quixxxy.solmyr.domain.Quote;

import org.hibernate.criterion.Projections;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class QuoteDaoImpl extends BaseDaoImpl<Quote, Long> implements QuoteDao {

    public List<Quote> getQuotes(int startFrom, int limit) {
        return getSession().createQuery("from Quote quote order by quote.id desc").setMaxResults(limit)
                .setFirstResult(startFrom).list();
    }

    public Quote getQuote(Long quoteId) {
        return findById(quoteId);
    }

    public Quote addQuote(Quote quote) {
        return merge(quote);
    }

    public List<Quote> findQuotes(String quoteText) {
        return getSession()
                .createQuery("from Quote quote where quote.text like :quoteText order by quote.id desc")
                .setString("quoteText", "%" + quoteText + "%").list();

    }

    public List<Quote> findAllQuotes() {
        return findAll();
    }

    public Long getQoutesCount() {
        return ((Long) getSession().createCriteria(getEntityClass()).setProjection(Projections.rowCount())
                .uniqueResult());
    }

    public Quote getQuoteByHash(String quoteHash) {
        Object obj = getSession().createQuery("from Quote quote where quote.quoteHash = :quoteHash")
                .setString("quoteHash", quoteHash).uniqueResult();
        return (Quote) obj;
    }

    @Override
    protected Class<Quote> getEntityClass() {
        return Quote.class;
    }

    public Quote updateQuote(Quote quote) {
        return merge(quote);
    }
}
