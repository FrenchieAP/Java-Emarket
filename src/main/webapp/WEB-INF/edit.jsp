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
<body>
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
	<div class="container">
	<a href="/products">Back to dashboard</a>
		<h1>Edit product</h1>
		<form:form method="POST" action="/products/${productObj.id}/edit" modelAttribute="productObj">
			<input type="hidden" name="_method" value="PUT" />
			<form:input type="hidden" path="creator" value="${user_id}" />
			<p>
				Name:
				<form:input path="name" />
				<form:errors path="name" />
			</p>
			<p>
				Price:
				<form:input path="price"/>
				<form:errors path="price" />
			</p>
			<p>
				Description
				<form:textarea path="description"/>
				<form:errors path="description" />
			</p>
			<p>
				Image URL
				<form:input path="imageUrl"/>
				<form:errors path="imageUrl" />
			</p>
			<button class="btn btn-primary">Edit</button>
			
		</form:form>
		
		<a href="/products/${productObj.id}/delete"><button class="btn btn-danger" style="margin-top: 10px" >Delete</button></a>					

		
	</div>

</body>
</html>