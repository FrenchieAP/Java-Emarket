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
    <h4 style="margin: 50px 0px 0px 100px" ><a href="/products/categories">Browse by Category</a></h4>
	<h1 class="prodlist">Product List</h1>
	
	<table  style="border-radius: 25px; border: solid grey 2px; height: 75%; width: 75%; margin: 50px 0px 0px 200px"  class="table">
			<thead>
				<tr>
					<th>Name</th>
					<th>User</th>
					<th>Price</th>
					<th>Description</th>
					<th>Image</th>
					<th>Action</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="product" items="${allProducts}">
					<tr>
						<td><a href="/products/${product.id}"><c:out value="${product.name}" /></a></td>
						<td><c:out value="${product.creator.userName}" /></td>
						<td><c:out value="${product.price}" /></td>
						<td><c:out value="${product.description}" /></td>
						<td><img class="img1" src="<c:out value="${product.imageUrl}" />"/></td>
						<td>
							<a href="/products/${product.id}">View</a>
							<c:if test="${user_id == product.creator.id }">
								<a href="/products/${product.id}/edit">Edit</a>
								<a href="/products/${product.id}/delete">Delete</a>							
							</c:if>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>

</body>
</html>