<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<c:import url="/WEB-INF/jsp/common/header.jsp" />

<c:set var="code" value="${fn:toLowerCase(park.code)}" />

<div class="weather-page">
	<div id="name-climate">
		<span id="park-name-home">${park.name}</span><br>
		<span id="park-climate"><strong> Climate: </strong>${park.climate}</span><br>
	</div>
	<div id="weather-forecase-img">
		<img src="img/parks/${code}.jpg"/>
	</div>	
	<div class="forecast">
		<c:forEach var="weather" items="${weathers}">
			<div>
				<img src="img/weather/${weather.forecast}.png" />
					<p>Day ${weather.fivedayforecast}
					<strong>Low:</strong> ${weather.low}°
					<strong>High:</strong> ${weather.high}°<br>
					<strong>Weather Advisory:</strong> ${weather.advisory}</p>
			</div>
		</c:forEach>
	</div>
</div>

<c:import url="/WEB-INF/jsp/common/footer.jsp" />