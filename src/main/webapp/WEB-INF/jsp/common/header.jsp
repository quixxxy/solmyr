<%@ include file="/WEB-INF/jsp/common/include.jsp"%>
<html>
<head>
	<title><spring:message code="header.title" /></title>
	<link rel="icon" type="image/ico" href="<spring:url value="/static/images/favicon.ico" />" />
	<link rel="stylesheet" href="/solmyr-cms/<spring:theme code="css"/>" type="text/css"/>
	<script type="text/javascript" src="<spring:url value="/static/js/jquery-1.7.2.js" />"></script>
	<script type="text/javascript" src="<spring:url value="/static/js/main.js" />"></script>
</head>
<body>

<div class="title">
	<div class="logo"><a href="<spring:url value="/" />"><img src="<spring:url value="/static/images/solmyr_large.png" />"></a></div>
	<div class="name"><h1><spring:message code="header.title" /></h1></div>
</div>

<div class="header">
	<div class="left">
		<sec:authorize ifAnyGranted="ROLE_USER">
			<a href="<spring:url value="/logout" />"><spring:message code="header.logout" /></a>
			<a href="<spring:url value="/quote/add" />"><spring:message code="quote.add"/></a>
		</sec:authorize>
		<sec:authorize ifAnyGranted="ROLE_ADMIN">
			<a href="<spring:url value="/admin" />">admin</a>
		</sec:authorize>
		<sec:authorize ifNotGranted="ROLE_USER">
			<a href="<spring:url value="/login" />"><spring:message code="header.login" /></a>
			<a href="<spring:url value="/register" />"><spring:message code="header.register" /></a>
		</sec:authorize>
	</div>
	<div class="right">
		<a href="<spring:url value="?locale=en" />"><spring:message code="language.en"/></a>
		<a href="<spring:url value="?locale=ru" />"><spring:message code="language.ru"/></a>
		<a href="<spring:url value="?theme=default" />"><spring:message code="theme.default"/></a>
		<a href="<spring:url value="?theme=dark" />"><spring:message code="theme.dark"/></a>
	</div>
</div>