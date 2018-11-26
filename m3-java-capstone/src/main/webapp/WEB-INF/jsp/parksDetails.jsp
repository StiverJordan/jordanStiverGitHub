<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<c:import url="/WEB-INF/jsp/common/header.jsp" />

<c:set var="code" value="${fn:toLowerCase(park.code)}" />

<div class="main-content">

<h2>${park.name}</h2>


<div id="data-img-quote">
	<ul id="data-ul">
		<li><strong>Acreage:</strong>  ${park.acreage} </li>
		<li><strong>Elevation:</strong> ${park.elevationFeet} ft.</li>
		<li><strong>Miles of Trail:</strong> ${park.milesOfTrail}</li>
		<li><strong>Number of Campsites:</strong> ${park.numberOfCampsites}</li>
		<li><strong>Climate Type:</strong> ${park.climate}</li>			
		<li><strong>Year Founded:</strong> ${park.yearFounded}</li>
		<li><strong>Annual Visitors:</strong> ${park.annualVisitors}</li>
		<li><strong>Park Entry Fee:</strong> $${park.entryFee}</li>
		<li><strong>Number of Animal Species:</strong> ${park.numberOfAnimalSpecies}</li>
	</ul>

		<img id="park-img" src="img/parks/${code}.jpg" />
	
	<div id="quote-ul">	
		<p>
			<i>"${park.inspQuote}"</i><br>
			-${park.inspQuoteSrc}
		</p>
		<c:url var="weatherUrl" value="/parkWeather">
			<c:param name="codeUrl" value="${park.code}"/>
		</c:url>
		<form action="${weatherUrl}" method="GET">
			<input type="radio" name="degree" value="f" checked>Fahrenheit
			<input type="radio" name="degree" value="c">Celcius
			<input type="submit" value="Get 5-day Weather Forecast"> 
		</form>
	</div>
</div>

	<span id="park-description-home">${park.parkDescription}</span>
	
	
</div>	


<c:import url="/WEB-INF/jsp/common/footer.jsp" />