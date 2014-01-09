package com.quixxxy.solmyr.web.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.quixxxy.solmyr.domain.Rate;
import com.quixxxy.solmyr.service.RateService;

@Controller
@RequestMapping("/rates")
public class RateController {

	@Autowired
	private RateService rateService;

	private final Logger logger = LoggerFactory.getLogger(getClass());

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView rates() {
		ModelAndView mav = new ModelAndView("solmyr.index.view");
		mav.addObject(rateService.getRates());
		mav.addObject(new Rate());
		return mav;
	}

	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView addRate(@ModelAttribute("rate") @Valid Rate rate, BindingResult result) {
		logger.info("adding new rate {}", rate);	
		if (result.hasErrors()) {
			ModelAndView mav = new ModelAndView("solmyr.index.view");
			mav.addObject(rateService.getRates());
			return mav;
		}
		rateService.addRate(rate);
		return new ModelAndView("redirect:/rates");
	}

	@RequestMapping(value = "/delete/{rateId}", method = RequestMethod.POST)
	public String deleteRate(@PathVariable("rateId") Long rateId) {
		logger.info("removing rateId {}", rateId);
		rateService.deleteRate(rateId);
		return "redirect:/rates";
	}
		
	void setRateService(RateService rateService) {
		this.rateService = rateService;
	}
}
