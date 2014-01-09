<%@ include file="/WEB-INF/jsp/common/include.jsp"%>

<div class="quote">
	<div class="actions">
		<a href="<spring:url value="/quote/${quote.id}/yes" />" class="up" onclick="voteQuote(${quote.id}, 'yes'); return false;">+</a>
		<span id="${quote.id}">${quote.rating}</span>
		<a href="<spring:url value="/quote/${quote.id}/no" />" class="down" onclick="voteQuote(${quote.id}, 'no'); return false;">-</a>
		<a href="" class="old">[:|||||:]</a>
		<span class="author">${quote.user.username}</span>
		<span class="date">${quote.creationDate}</span>
		<a href="<spring:url value="/quote/${quote.id}" />" class="id">#${quote.id}</a>
	</div>
	<div class="text">${quote.htmlEscapedText}</div>
</div>