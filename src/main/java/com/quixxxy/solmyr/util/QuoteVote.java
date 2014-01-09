package com.quixxxy.solmyr.util;

import org.apache.commons.lang.StringUtils;

public enum QuoteVote {
	
	VOTE_YES("yes"), VOTE_NO("no"), VOTE_BAYAN("bayan");

	private String value;
	
	private QuoteVote(String value){
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public static QuoteVote getQuoteVote(String value) {
		for (QuoteVote values: QuoteVote.values()) {
			if (StringUtils.equalsIgnoreCase(value, values.getValue())){
				return values;
			}
		}
		throw new IllegalArgumentException("Unknown vote type " + value);
	}
}
