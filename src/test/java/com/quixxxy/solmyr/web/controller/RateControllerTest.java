package com.quixxxy.solmyr.web.controller;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.easymock.EasyMock;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;

import com.quixxxy.solmyr.domain.Rate;
import com.quixxxy.solmyr.service.RateService;
import com.quixxxy.solmyr.web.controller.RateController;

@RunWith(PowerMockRunner.class)
public class RateControllerTest {

	private RateService rateService;

	@Before
	public void setUp() throws Exception {
		rateService = EasyMock.createMock(RateService.class);
	}

	@Test
	public void testRates() throws Exception {
		RateController controller = new RateController();

		List<Rate> testRates = new ArrayList<Rate>();
		testRates.add(new Rate());
		EasyMock.expect(rateService.getRates()).andReturn(testRates);
		EasyMock.replay(rateService);
		controller.setRateService(rateService);

		ModelAndView modelAndView = controller.rates();

		assertNotNull(modelAndView.getModel());
		
		assertEquals(2, modelAndView.getModel().size());
		assertTrue(modelAndView.getModel().containsKey("rate"));
		assertTrue(modelAndView.getModel().containsKey("rateList"));
		
		assertEquals("solmyr.index.view", modelAndView.getViewName());

		EasyMock.verify(rateService);
	}
	
	@Test
	public void testAddRate() throws Exception {
		RateController controller = new RateController();	
		controller.setRateService(rateService);
		BindingResult result = EasyMock.createMock(BindingResult.class);
		EasyMock.expect(result.hasErrors()).andReturn(false);
		EasyMock.replay(result);
		assertEquals("redirect:/rates", controller.addRate(new Rate(), result).getViewName());
		EasyMock.verify(result);
	}
	
	@Test
	public void testDeleteRate()  {
		RateController controller = new RateController();
		controller.setRateService(rateService);
		assertEquals("redirect:/rates", controller.deleteRate(0L));
	}
	
	@After
	public void tearDown() throws Exception {
	}

}
