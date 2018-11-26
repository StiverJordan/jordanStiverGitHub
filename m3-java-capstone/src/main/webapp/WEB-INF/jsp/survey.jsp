<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<c:import url="/WEB-INF/jsp/common/header.jsp" />

<c:url var="completedSurvey" value="/survey"/>
<form method="POST" action="${completedSurvey}">
	
<h2>Submit A Survey</h2>
<div class="survey">
	<div>
		<select name="code" id="favParkCode">
			<option value="" disabled selected>Select a National Park</option>
			<option value="CVNP">Cuyahoga Valley National Park</option>
			<option value="ENP">Everglades National Park</option>
			<option value="GCNP">Grand Canyon National Park</option>
			<option value="GNP">Glacier National Park</option>
			<option value="GSMNP">Great Smoky Mountains National Park</option>
			<option value="GTNP">Grand Teton National Park</option>
			<option value="MRNP">Mount Rainier National Park</option>
			<option value="RMNP">Rocky Mountain National Park</option>
			<option value="YNP">Yellowstone National Park</option>
			<option value="YNP2">Yosemite National Park</option>
		</select>
	</div>
		
	<div>
		<input type="text" name="email" id="email" placeholder="email@email.com"/>
	</div>
		
	<div>
		<select name="state" id="state">
			<option value="" disabled selected>Select Your State of Residence</option>
			<option value="Alabama">Alabama</option> 
			<option value="Alaska">Alaska</option> 
			<option value="Arizona">Arizona</option> 
			<option value="Arkansas">Arkansas</option> 
			<option value="California">California</option> 
			<option value="Colorado">Colorado</option> 
			<option value="Connecticut">Connecticut</option> 
			<option value="Delaware">Delaware</option> 
			<option value="Florida">Florida</option> 
			<option value="Georgia">Georgia</option> 
			<option value="Hawaii">Hawaii</option> 
			<option value="Idaho">Idaho</option> 
			<option value="Illinois">Illinois</option> 
			<option value="Indiana">Indiana</option> 
			<option value="Iowa">Iowa</option> 
			<option value="Kansas">Kansas</option> 
			<option value="Kentucky">Kentucky</option> 
			<option value="Louisiana">Louisiana</option> 
			<option value="Maine">Maine</option> 
			<option value="Maryland">Maryland</option> 
			<option value="Massachusetts">Massachusetts</option> 
			<option value="Michigan">Michigan</option> 
			<option value="Minnesota">Minnesota</option> 
			<option value="Mississippi">Mississippi</option> 
			<option value="Missouri">Missouri</option> 
			<option value="Montana">Montana</option> 
			<option value="Nebraska">Nebraska</option> 
			<option value="Nevada">Nevada</option> 
			<option value="New Hampshire">New Hampshire</option> 
			<option value="New Jersey">New Jersey</option> 
			<option value="New Mexico">New Mexico</option> 
			<option value="New York">New York</option> 
			<option value="North Carolina">North Carolina</option> 
			<option value="North Dakota">North Dakota</option> 
			<option value="Ohio">Ohio</option> 
			<option value="Oklahoma">Oklahoma</option> 
			<option value="Oregon">Oregon</option> 
			<option value="Pennsylvania">Pennsylvania</option> 
			<option value="Rhode Island">Rhode Island</option> 
			<option value="South Carolina">South Carolina</option> 
			<option value="South Dakota">South Dakota</option> 
			<option value="Tennessee">Tennessee</option> 
			<option value="Texas">Texas</option> 
			<option value="Utah">Utah</option> 
			<option value="Vermont">Vermont</option> 
			<option value="Virginia">Virginia</option> 
			<option value="Washington">Washington</option> 
			<option value="West Virginia">West Virginia</option> 
			<option value="Wisconsin">Wisconsin</option> 
			<option value="Wyoming">Wyoming</option> 
		</select>
	</div>
		
	<div>
		<input type="radio" name="activityLevel" value="Inactive">Inactive
		<input type="radio" name="activityLevel" value="Sedentary">Sedentary
		<input type="radio" name="activityLevel" value="Active">Active
		<input type="radio" name="activityLevel" value="Extremely Active">Extremely Active
	</div>
	<input type="submit" value="Complete Survey"/>
</div>
</form>

<c:import url="/WEB-INF/jsp/common/footer.jsp" />