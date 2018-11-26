<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<c:import url="/WEB-INF/jsp/common/header.jsp" />



<h2>Most Surveyed Parks</h2>
<ol>
	<c:forEach var="topPark" items="${topParks}">
		<c:url var="formAction" value="/parksDetails">
			<c:param name="codeUrl" value="${topPark.code}" />
		</c:url>
		<c:set var="code" value="${fn:toLowerCase(topPark.code)}" />
		<li style="display: flex; align-items: center;">
			<div>
				<strong style="font-size: 1rem;">${topPark.name}</strong><br> 
				<a href="${formAction}"><img id="park-img"src="img/parks/${code}.jpg" /> </a>
			</div>
			<div style="font-size: 1.5rem;">
				<i>${topPark.inspQuote}</i><br>-${topPark.inspQuoteSrc}
			</div>
		</li>
	</c:forEach>
</ol>
<c:import url="/WEB-INF/jsp/common/footer.jsp" />