<%@ include file="/WEB-INF/jsp/common/include.jsp" %>
<div class="search">
	<form:form name="search" method="get" modelAttribute="quote" action="/solmyr-cms/quote/find">
		<form:input type="text" path="text" placeholder="enter something"/>
		<button type="submit"><spring:message code="search.find"/></button>
	</form:form>
</div>