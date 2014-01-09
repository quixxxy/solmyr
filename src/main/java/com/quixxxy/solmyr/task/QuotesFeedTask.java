package com.quixxxy.solmyr.task;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.quixxxy.solmyr.domain.Quote;
import com.quixxxy.solmyr.domain.User;
import com.quixxxy.solmyr.feed.exception.SolmyrFeedException;
import com.quixxxy.solmyr.feed.service.FeedService;
import com.quixxxy.solmyr.service.QuoteService;
import com.quixxxy.solmyr.service.UserService;

@Component
public class QuotesFeedTask {

	private final Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private FeedService feedService;
	@Autowired
	private QuoteService quoteService;
	@Autowired
	private UserService userService;
	
	@Scheduled(fixedDelay = 1000 * 60 * 60 * 3)
	public void bashOrgFeedTask() {
		User user = userService.getUserByName("admin");
		try {
			List<Quote> quotes = feedService.getRemoteQuotes();
			for (Quote quote: quotes) {
				quote.setUser(user);
				quoteService.addQuote(quote);
			}
		} catch (SolmyrFeedException e) {
			logger.error("Cannot get info from remote feeder: {}", e);
		}
	}
}
