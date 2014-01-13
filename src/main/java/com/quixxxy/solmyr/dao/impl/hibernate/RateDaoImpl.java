package com.quixxxy.solmyr.dao.impl.hibernate;

import com.quixxxy.solmyr.dao.RateDao;
import com.quixxxy.solmyr.domain.Rate;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RateDaoImpl extends BaseDaoImpl<Rate, Long> implements RateDao {

    public List<Rate> getRates() {
        return getSession().createQuery("from Rate").list();
    }

    public void deleteRate(Long rateId) {
        Rate rate = findById(rateId);
        if (null != rate) {
            delete(rate);
        }
    }

    public Rate getRate(Long rateId) {
        return findById(rateId);
    }

    public Rate addRate(Rate rate) {
        return merge(rate);
    }

    @Override
    protected Class<Rate> getEntityClass() {
        return Rate.class;
    }
}
