<%@ include file="/WEB-INF/jsp/common/include.jsp"%>

<tiles:insertAttribute name="header" />

<h2><spring:message code="quote.add" /></h2>

<c:if test="${success}">
	<div class="notrly">
		<spring:message code="quote.success" />
	</div>
</c:if>
<form action="<spring:url value="add"/>" method="post" class="field">
	<form:errors path="quote.text" cssClass="error" element="div"/>
	<form:textarea path="quote.text" rows="20" class="field"/>
	<div class="field">
		<input type="submit" value="<spring:message code="quote.send" />" />
	</div>
</form>

<tiles:insertAttribute name="footer" />