<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GB18030">
<title>viewCart</title>
</head>
<body>
	<h1>View Cart</h1>
	<h2>Items in Your Cart</h2>
	<c:choose>
		<c:when test="${empty cart.items}">
			<p>Your cart is empty.</p>
		</c:when>
		<c:otherwise>
			<table border="1" cellspacing="0">
				<tr>
					<th>Item</th>
					<th>Quantity</th>
					<th>Unit Price</th>
					<th>Total</th>
				</tr>
				<c:forEach var="item" items="${cart.items}">
					<tr>
						<td>${item.product.description}</td>
						<td>${item.quantity}</td>
						<td>${item.product.price}</td>
						<td>${item.totalPrice}</td>
					</tr>
				</c:forEach>
				<tr>
					<td>TOTAL:</td>
					<td></td>
					<td></td>
					<td>${cart.totalPrice}</td>
				</tr>
			</table>
		</c:otherwise>
	</c:choose>
	<a href="${flowExecutionUrl}&_eventId=submit">Submit</a>
	<h2>Products for Your Choice</h2>
	<table>
		<c:forEach var="product" items="${products}">
			<tr>
				<td>${product.description}</td>
				<td>${product.price}</td>
				<td><a
					href="${flowExecutionUrl}&_eventId=addToCart&productId=${product.id}">[add
						to cart]</a></td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>