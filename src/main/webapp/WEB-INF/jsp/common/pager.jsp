<%@ include file="/WEB-INF/jsp/common/include.jsp"%>
<div class="pager">
	<c:forEach begin="1" end="${pagination.pages}" step="1" var="i">
		<a href="<spring:url value="/page/${i}"/>">
			<c:choose>
				<c:when test="${pagination.currentPage eq i}">
					<span class="current">${i}</span>
				</c:when>
				<c:otherwise>
					<span>${i}</span>
				</c:otherwise>
			</c:choose>
		</a>
	</c:forEach>
</div>