package com.quixxxy.solmyr.web.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.support.SessionStatus;

import com.quixxxy.solmyr.domain.Pagination;
import com.quixxxy.solmyr.domain.Quote;
import com.quixxxy.solmyr.domain.User;
import com.quixxxy.solmyr.jmx.QuotesSettings;
import com.quixxxy.solmyr.security.SecurityHelper;
import com.quixxxy.solmyr.service.QuoteService;
import com.quixxxy.solmyr.service.UserService;

@Controller
@RequestMapping("/quote")
public class QuoteController {

	@Autowired
	private QuoteService quoteService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private QuotesSettings quotesSettings;
	
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	@RequestMapping(value="/add", method = RequestMethod.GET)
	public String showAddQuotePage(Model model) {
		model.addAttribute(new Quote());
		return "solmyr.quote.add";
	}
	
	@RequestMapping(value="/{quoteId}", method = RequestMethod.GET)
	public String getCustomQuote(@PathVariable("quoteId") Long quoteId, Model model) {
		Quote quote = quoteService.getQuote(quoteId);
		if (quote == null) {
			return "redirect:/";
		}
		model.addAttribute(quote);
		return "solmyr.quote.show";
	}
	
	@RequestMapping(value="/add", method = RequestMethod.POST)
	public String addQuote(@ModelAttribute("quote") @Valid Quote quote, BindingResult result, SessionStatus status, Model model) {
		quote.setQuoteHash(String.valueOf(quote.hashCode()));
		if (result.hasErrors()) {
			logger.debug("Validation quote failed, cannot add: {}. caused by {}", quote, result);
			return "solmyr.quote.add";
		}
		quote.setCreationDate(new Date());
		quote.setRating(0L);
		User user = userService.getUserByName(SecurityHelper.getUserName());
		quote.setUser(user);
		quoteService.addQuote(quote);
		model.addAttribute("success", true);
		model.addAttribute(new Quote());
		status.setComplete();
		return "solmyr.quote.add";
	}
	
	@RequestMapping(value="/find*", method = RequestMethod.GET)
	public String findQuotes(@ModelAttribute("quote") @Valid Quote quote, BindingResult result, Model model, HttpServletRequest req) {
		if (result.hasErrors()) {
			return "redirect:/";
		}
		List<Quote> quotes = quoteService.findQuotes(quote.getText());
		if (quotes.isEmpty()) {
			model.addAttribute("quoteNotFound", true);
		} else {
			Pagination<Quote> pagination = new Pagination<Quote>(quotes, quotesSettings.getQuoutesPerPage(), 1, 1);
			model.addAttribute(pagination);
		}
		return "solmyr.search.result.view";
	}
	
	@RequestMapping(value="/{quoteId}/{value}", method = RequestMethod.GET)
	public @ResponseBody Long getVote(@PathVariable("quoteId") Long quoteId, @PathVariable("value") String value) {
		logger.info("Voted for quote - {}, {}", quoteId, value);
		return quoteService.updateRating(quoteId, value);
	}
}
