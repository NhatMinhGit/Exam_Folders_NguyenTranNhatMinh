<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit Product</title>
</head>
<body>
<h1>Edit Product</h1>
<c:if test="${not empty requestScope.errors}">
    <ul style="color: red;">
        <c:forEach var="error" items="${requestScope.errors}">
            <li>${error}</li>
        </c:forEach>
    </ul>
</c:if>
<p>
    <c:if test='${requestScope["message"] != null}'>
        <span class="message">${requestScope["message"]}</span>
    </c:if>
</p>
<p>
    <a href="/products">Back to product list</a>
</p>
<form method="post">
    <fieldset>
        <legend>Product Information</legend>
        <table>
            <tr>
                <td>Name: </td>
                <td><input type="text" name="name" id="name" value="${requestScope["product"].getName()}" required></td>
            </tr>
            <tr>
                <td>Price: </td>
                <td><input type="number" name="price" id="price" value="${requestScope["product"].getPrice()}" required></td>
            </tr>
            <tr>
                <td>Quantity: </td>
                <td><input type="number" name="quantity" id="quantity" value="${requestScope["product"].getQuantity()}" required></td>
            </tr>
            <tr>
                <td>Color: </td>
                <td><input type="text" name="color" id="color" value="${requestScope["product"].getColor()}"></td>
            </tr>
            <tr>
                <td>Description: </td>
                <td><textarea name="description" id="description">${requestScope["product"].getDescription()}</textarea></td>
            </tr>
            <tr>
                <td>Category: </td>
                <td>
                    <select name="category" id="category">
                        <c:forEach var="cat" items="${requestScope['categories']}">
                            <option value="${cat.id}" ${cat.id == requestScope['product'].category.id ? 'selected' : ''}>
                                    ${cat.name}
                            </option>
                        </c:forEach>
                    </select>
                </td>
            </tr>
            <tr>
                <td></td>
                <td><input type="submit" value="Update Product"></td>
            </tr>
        </table>
    </fieldset>
</form>
</body>
</html>
