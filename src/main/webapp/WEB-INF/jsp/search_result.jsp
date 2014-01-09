<%@ include file="/WEB-INF/jsp/common/include.jsp" %>

<tiles:insertAttribute name="header" />

<tiles:insertAttribute name="search" />

<c:choose>
	<c:when test="${quoteNotFound}">
		<div class="error"><spring:message code="quote.not.found"/></div>
	</c:when>
	<c:otherwise>
		<div class="notrly "><spring:message code="quote.search.result" />${quote.text}</div>
	</c:otherwise>
</c:choose>


<c:forEach items="${pagination.source}" var="quote" >
	<c:set var="quote" value="${quote}" scope="request"/>
 	<tiles:insertAttribute name="quote" />
</c:forEach>

<tiles:insertAttribute name="footer"/>