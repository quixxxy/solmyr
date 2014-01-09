<%@ include file="/WEB-INF/jsp/common/include.jsp"%>

<tiles:insertAttribute name="header" />

<form:form method="post" action="register" modelAttribute="user">
	<table>
		<tr>
			<td>
				<form:label path="username">
					<spring:message code="registration.username"/>
				</form:label>
			</td>
			<td><form:input path="username" maxlength="16" /></td>
			<td><form:errors path="username"  /></td>
		</tr>
		<tr>
		<tr>
			<td>
				<form:label path="email">
					<spring:message code="registration.email"/>
				</form:label>
			</td>
			<td><form:input path="email" maxlength="16" /></td>
			<td><form:errors path="email" /></td>
		</tr>
		<tr>
			<td>
				<form:label path="password">
					<spring:message code="registration.password"/>
				</form:label>
			</td>
			<td><form:password path="password" maxlength="16" /></td>
			<td><form:errors path="password" /></td>
		</tr>
		<tr>
		<tr>
			<td><input type="submit" value="<spring:message code="registration.create" />" /></td>
		</tr>
	</table>
</form:form>

<tiles:insertAttribute name="footer" />