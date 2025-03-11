<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Product List</title>
</head>
<body>
<h1>Products</h1>
<!-- Form tìm kiếm -->
<form action="/products" method="GET">
    <input type="hidden" name="action" value="search">
    <input type="text" name="name" placeholder="Tìm theo tên" value="${param.name}">
    <input type="text" name="price" placeholder="Tìm theo giá" value="${param.price}">
    <input type="text" name="category" placeholder="Tìm theo danh mục" value="${param.category}">
    <input type="text" name="color" placeholder="Tìm theo màu sắc" value="${param.color}">
    <button type="submit">Tìm kiếm</button>
</form>


<p>
    <a href="/products?action=create">Create new product</a>
</p>
<table border="1">
    <tr>
        <th>ID</th>
        <th>Name</th>
        <th>Price</th>
        <th>Quantity</th>
        <th>Color</th>
        <th>Category</th>
        <th>Description</th>
        <th>Edit</th>
        <th>Delete</th>
    </tr>
    <c:forEach items='${requestScope["products"]}' var="product">
        <tr>
            <td>${product.getId()}</td>
            <td><a href="/products?action=view&id=${product.getId()}">${product.getName()}</a></td>
            <td><fmt:formatNumber value="${product.getPrice()}" pattern="#,###.### VND"/></td>
            <td>${product.getQuantity()}</td>
            <td>${product.getColor()}</td>
            <td>${product.getCategory().getName()}</td>
            <td>${product.getDescription()}</td>
            <td><a href="/products?action=edit&id=${product.getId()}">Edit</a></td>
            <td><a href="/products?action=delete&id=${product.getId()}" onclick="return confirm('Are you sure?')">Delete</a></td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
