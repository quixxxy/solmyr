package com.quixxxy.solmyr.util;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang.StringUtils;

public final class QuoteUtils {

	private static final String SYSTEM_LINE_SEPARATOR = System.getProperty("line.separator");
	private static final String HTML_LINE_SEPARATOR = "<br>";

	private QuoteUtils() {
	}
	
	public static String escapeHtml(String text) {
		return StringUtils.replace(StringEscapeUtils.escapeHtml(text), SYSTEM_LINE_SEPARATOR, HTML_LINE_SEPARATOR); 
	}
	
	public static String unescapeHtml(String text) {
		return StringUtils.replace(StringEscapeUtils.unescapeHtml(text), HTML_LINE_SEPARATOR, SYSTEM_LINE_SEPARATOR); 
	}
	
	
}
