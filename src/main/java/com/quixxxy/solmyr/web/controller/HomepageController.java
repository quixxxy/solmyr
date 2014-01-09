package com.quixxxy.solmyr.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.quixxxy.solmyr.domain.Pagination;
import com.quixxxy.solmyr.domain.Quote;
import com.quixxxy.solmyr.jmx.QuotesSettings;
import com.quixxxy.solmyr.service.QuoteService;

@Controller
@RequestMapping("/")
public class HomepageController {

	private static final int FIRST_PAGE = 1;
	@Autowired
	private QuoteService quoteService;
	
	@Autowired
	private QuotesSettings quotesSettings;
	
	@RequestMapping(method = RequestMethod.GET)
	public String showHomepage(Model model) {
		Pagination<Quote> pagination = quoteService.getPaginatedQuotes(FIRST_PAGE, quotesSettings.getQuoutesPerPage()); 
		model.addAttribute(pagination);
		model.addAttribute(new Quote());		
		return "solmyr.homepage.view";
	}
}
