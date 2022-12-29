<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- New line below to use the JSP Standard Tag Library -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page isErrorPage="true" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" type="text/css" href="/css/style.css"/>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
<title>Insert title here</title>
</head>
<body class="background-image">
	<div class="nav">
        <h1 ><a href="/products" class="orange zbay">E-Market</a></h1>
        <h4 style="margin: 15px 0px 0px 50px" class="white">Hello, <span class="orange"><c:out value="${userName.userName}"/></span></h4>
        <form action='/searching' method="POST">
            <input type="search" name="find"class="grey input search3">    
            <button class="search_btn2"><img src="/images/search.png"></button>
        </form>
        <div class="navtabs">
	        <h4><a href="/products/browse" class="navtab">Browse</a></h4>
	        <h4><a href="/account" class="navtab">Account</a></h4>
	        <h4><a href="/orders" class="navtab">Orders</a></h4>
	        <h6 ><a href="/logout" class="navtab">Log out</a></h6>
	    </div>

    </div>
</body>
</html>