<%@ include file="/WEB-INF/jsp/common/include.jsp" %>

<tiles:insertAttribute name="header" />

<c:if test="${not empty param.error}">
	<spring:message code="login.error" /> ${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}
</c:if>
<form method="POST" action="<spring:url value="/j_spring_security_check" />">
	<table>
		<tr>
			<td align="right"><spring:message code="login.login" /></td>
			<td><input type="text" name="j_username" maxlength="16" /></td>
		</tr>
		<tr>
			<td align="right"><spring:message code="login.password" /></td>
			<td><input type="password" name="j_password" maxlength="16" /></td>
		</tr>
		<tr>
			<td colspan="2" align="right"><input type="submit" value="<spring:message code="login.button.login" />" />
			<input type="reset" value="<spring:message code="login.button.reset" />" /></td>
		</tr>
	</table>
</form>

<tiles:insertAttribute name="footer"/>