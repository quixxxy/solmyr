<%@ include file="/WEB-INF/jsp/common/include.jsp" %>

<tiles:insertAttribute name="header" />

<tiles:insertAttribute name="search" />

<tiles:insertAttribute name="pager" />

<c:forEach items="${pagination.source}" var="quote" >
	<c:set var="quote" value="${quote}" scope="request"/>
 	<tiles:insertAttribute name="quote" />
</c:forEach>

<tiles:insertAttribute name="pager" />

<tiles:insertAttribute name="footer"/>