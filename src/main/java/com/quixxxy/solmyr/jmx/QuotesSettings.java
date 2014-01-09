package com.quixxxy.solmyr.jmx;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.jmx.export.annotation.ManagedAttribute;
import org.springframework.jmx.export.annotation.ManagedResource;
import org.springframework.stereotype.Service;

@Service
@Scope("singleton")
@ManagedResource(objectName = "somyr:name=QuotesSettings", description = "QuotesSettings mbean")
public class QuotesSettings implements IQuotesSettings {
	
	@Value("${solmyr.quote.per.page}")
	private int quoutesPerPage;

	@ManagedAttribute(description = "Quotes per page")
	public int getQuoutesPerPage() {
		return quoutesPerPage;
	}

	@ManagedAttribute(description = "Set new number of quotes per page")
	public void setQuoutesPerPage(int quoutesPerPage) {
		this.quoutesPerPage = quoutesPerPage;
	}

}
