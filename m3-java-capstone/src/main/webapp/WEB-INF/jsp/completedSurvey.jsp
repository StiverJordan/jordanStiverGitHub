<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<c:import url="/WEB-INF/jsp/common/header.jsp" />


<c:set var="code" value="${fn:toLowerCase(topPark.code)}" />

<h1>You Did It! You beat completed the survey!</h1>

<div>
	<strong>${topPark.name}</strong>
</div>

<div style="display:flex"> 
	<img id="park-img" src="img/parks/${code}.jpg" />  
	<p>${topPark.parkDescription}</p>
</div>

<p>
	<i>"This is the most bestest park we got yalllllllll" </i><br> 
	-The j-dog css master
</p>

<c:import url="/WEB-INF/jsp/common/footer.jsp" />