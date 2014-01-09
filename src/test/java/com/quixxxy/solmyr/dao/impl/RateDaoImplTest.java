package com.quixxxy.solmyr.dao.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.quixxxy.solmyr.dao.RateDao;
import com.quixxxy.solmyr.domain.Rate;

@ContextConfiguration(locations="classpath:dao-test-context.xml")
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class RateDaoImplTest {

	@Autowired
	private RateDao rateDao;
	
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetRates() {
		List<Rate> rates = rateDao.getRates();
		assertNotNull(rates);
	}
	
	@Test
	public void testAddRate() {
		Rate rate = new Rate();
		rate.setRate(90);
		rate = rateDao.addRate(rate);
		assertNotNull(rate.getId());
		assertEquals(90, rate.getRate());
	}
	
	@Test
	public void testGetRate() {
		Rate rate = new Rate();
		rate.setRate(90);
		long id = rateDao.addRate(rate).getId();
		rate = rateDao.getRate(id);
		assertNotNull(rate);
	}
	
	@Test
	public void testDeleteRate() {
		Rate rate = new Rate();
		rate.setRate(90);
		rate = rateDao.addRate(rate);
		
		long id = rateDao.getRate(rate.getId()).getId();
		rateDao.deleteRate(id);
		assertNull(rateDao.getRate(id));
	}

}
