package com.quixxxy.solmyr.feed.service.impl;

import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.quixxxy.solmyr.domain.Quote;
import com.quixxxy.solmyr.feed.exception.SolmyrFeedException;
import com.quixxxy.solmyr.feed.service.FeedService;
import com.quixxxy.solmyr.util.QuoteUtils;
import com.sun.syndication.feed.synd.SyndContent;
import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.XmlReader;

@Service
public class BashOrgFeedServiceImpl implements FeedService {

	@Value("${solmyr.rss.url}")
	private String bashOrgRssURL;
	
	private final Logger logger = LoggerFactory.getLogger(getClass());

	public List<Quote> getRemoteQuotes() throws SolmyrFeedException {

		List<Quote> result = new ArrayList<Quote>();
		try {
			SyndFeed feed;
			URL url = new URL(bashOrgRssURL);
			XmlReader reader = new XmlReader(url);
			feed = new SyndFeedInput().build(reader);
			@SuppressWarnings("unchecked")
			List<SyndEntry> syndEntryList = feed.getEntries();
			logger.info("We have got {} quotes from {}", syndEntryList.size(), bashOrgRssURL);
			for (SyndEntry entry : syndEntryList) {
				SyndContent content = entry.getDescription();
				String text = content.getValue();
				Quote quote = new Quote();
				quote.setText(QuoteUtils.unescapeHtml(text));
				quote.setCreationDate(new Date());
				quote.setRating(0L);
				quote.setQuoteHash(entry.getUri());
				result.add(quote);
			}
		} catch (Exception e) {
			throw new SolmyrFeedException(e);
		}

		return result;
	}

}
