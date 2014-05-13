<%@ include file="/WEB-INF/jsp/common/include.jsp" %>
<div class="search">
	<spring:url var="action" value="/quote/find" />
	<form:form name="search" method="get" modelAttribute="quote" action="${action}">
		<form:input type="text" path="text" placeholder="enter something"/>
		<button type="submit"><spring:message code="search.find"/></button>
	</form:form>
</div>