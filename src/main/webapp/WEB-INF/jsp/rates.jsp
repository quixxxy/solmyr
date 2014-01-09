<%@ include file="/WEB-INF/jsp/common/include.jsp" %>

<tiles:insertAttribute name="header" />
<br/>
<table>
<c:forEach items="${rateList}" var="rate">
	<tr>
		<td><spring:message code="rates.id" /> ${rate.id}</td>
		<td><spring:message code="rates.rate" /> ${rate.rate}</td>
		<sec:authorize access="hasRole('ROLE_ADMIN')">
			<form name="search" method="post" action='<spring:url value="rates/delete/${rate.id}"/>'>
				<td><button type="submit"><spring:message code="rates.delete" /></button></td>
			</form>
		</sec:authorize>
	</tr>
</c:forEach>
</table>
<sec:authorize access="hasRole('ROLE_ADMIN')">
	<form:form method="post" action="rates" modelAttribute="rate">
		<table>
			<tr>
				<td><form:label path="rate"><spring:message code="rates.rate" /></form:label></td>
				<td><form:input path="rate" maxlength="5"/></td>
				<td><form:errors path="rate"/></td>  
			</tr>
			<tr>
				<td><input type="submit" value="<spring:message code="rates.add" />" /></td>
			</tr>
		</table>
	</form:form>
</sec:authorize>
<tiles:insertAttribute name="footer"/>