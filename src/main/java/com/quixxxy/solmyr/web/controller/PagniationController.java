package com.quixxxy.solmyr.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.quixxxy.solmyr.domain.Pagination;
import com.quixxxy.solmyr.domain.Quote;
import com.quixxxy.solmyr.jmx.QuotesSettings;
import com.quixxxy.solmyr.service.QuoteService;

@Controller
@RequestMapping("/page")
public class PagniationController {
	
	@Autowired
	private QuoteService quoteService;
	
	@Autowired
	private QuotesSettings quotesSettings;
	
	@RequestMapping(value="/{pageNumber}", method = RequestMethod.GET)
	public String showPage(@PathVariable("pageNumber") Integer pageNumber, Model model) {
		Pagination<Quote> pagination = quoteService.getPaginatedQuotes(pageNumber, quotesSettings.getQuoutesPerPage());
		if (pagination.isEmptySource()) {
			return "redirect:/";
		}
		model.addAttribute(pagination);
		model.addAttribute(new Quote());
		return "solmyr.homepage.view";
	}
}
