<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<c:url value="/css/css.css" var="cssHref" />
<link rel="stylesheet" href="${cssHref}">

</head>

<body>
    <header id="header">
    	<c:url value="/img/logo.png" var="logoSrc" />
        <img src="${logoSrc}" alt="Geek Logo" />
    </header>

<nav class="navBar">

    	<ul class="nav-ul">
    		<li><a href="/m3-java-capstone/homePage"> Home Page</a></li>
    		<li><a href="/m3-java-capstone/survey"> Submit a Survey</a></li>
    		<li><a href="/m3-java-capstone/favoriteParks"> Favorite Parks</a></li>
    	</ul>
</nav>



