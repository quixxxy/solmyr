package com.quixxxy.solmyr.service;

import static org.junit.Assert.assertNull;

import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.quixxxy.solmyr.dao.RateDao;
import com.quixxxy.solmyr.domain.Rate;
import com.quixxxy.solmyr.service.impl.RateServiceImpl;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:cache-test-context.xml")
public class RateServiceImplTest {

	private RateDao mockDao;
	private RateServiceImpl rateService;

	@Before
	public void setUp() throws Exception {
		mockDao = EasyMock.createMock(RateDao.class);
		rateService = new RateServiceImpl();
		rateService.setRateDao(mockDao);
	}

	@Test
	public void testGetRates() {
		assertNull(rateService.getRates());
	}

	@Test
	public void testAddRate() {
		rateService.addRate(new Rate());
	}

	@Test
	public void testDeleteRate() {
		rateService.deleteRate(0L);
	}
}
