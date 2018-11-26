<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!DOCTYPE html>

<c:import url="/WEB-INF/jsp/common/header.jsp" />


<form method="GET" action="${formAction}">
	<ul id="description">
		<c:forEach var="park" items="${parks}">
			<c:url var="formAction" value="/parksDetails">
				<c:param name="codeUrl" value="${park.code}" />
			</c:url>
			<c:set var="code" value="${fn:toLowerCase(park.code)}" />
			<li id="description-li">
				<a href="${formAction}"><imgsrc="img/parks/${code}.jpg" /></a>
				<div id="name-description">
					<div id="park-name-home">
						<strong>${park.name}</strong>
					</div>
					<div id="park-description-home">${park.parkDescription}</div>
				</div>
			</li>
		</c:forEach>
	</ul>
</form>


<c:import url="/WEB-INF/jsp/common/footer.jsp" />





