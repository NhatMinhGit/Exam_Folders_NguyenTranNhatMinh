<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create new product</title>
    <style>
        .message {
            color: green;
        }
    </style>
</head>
<body>
<h1>Create new product</h1>
<c:if test="${not empty requestScope.errors}">
    <ul style="color: red;">
        <c:forEach var="error" items="${requestScope.errors}">
            <li>${error}</li>
        </c:forEach>
    </ul>
</c:if>

<p>
    <c:if test="${requestScope['message'] != null}">
        <span class="message">${requestScope['message']}</span>
    </c:if>
</p>

    <a href="/products">Back to product list</a>
</p>
<form method="post">
    <fieldset>
        <legend>Product information</legend>
        <table>
            <tr>
                <td>Name: </td>
                <td>
                    <input type="text" name="name" id="name" value="${requestScope.name}" required>
                </td>
            </tr>
            <tr>
                <td>Price: </td>
                <td>
                    <input type="number" name="price" id="price" step="0.01" value="${requestScope.price}" required>
                </td>
            </tr>
            <tr>
                <td>Quantity: </td>
                <td>
                    <input type="number" name="quantity" id="quantity" value="${requestScope.quantity}" required>
                </td>
            </tr>
            <tr>
                <td>Color: </td>
                <td>
                    <select name="color" id="color">
                        <option value="Đỏ" ${requestScope.color == 'Đỏ' ? 'selected' : ''}>Đỏ</option>
                        <option value="Xanh" ${requestScope.color == 'Xanh' ? 'selected' : ''}>Xanh</option>
                        <option value="Đen" ${requestScope.color == 'Đen' ? 'selected' : ''}>Đen</option>
                        <option value="Trắng" ${requestScope.color == 'Trắng' ? 'selected' : ''}>Trắng</option>
                        <option value="Vàng" ${requestScope.color == 'Vàng' ? 'selected' : ''}>Vàng</option>
                    </select>
                </td>
            </tr>
            <tr>
                <td>Category: </td>
                <td>
                    <select name="category" id="category">
                        <c:forEach var="cat" items="${requestScope['categories']}">
                            <option value="${cat.id}" ${cat.id == requestScope.categoryId ? 'selected' : ''}>${cat.name}</option>
                        </c:forEach>
                    </select>
                </td>
            </tr>
            <tr>
                <td>Description: </td>
                <td>
                    <textarea name="description" id="description" rows="4">
                        ${requestScope.description}
                    </textarea>

            </tr>
            <tr>
                <td></td>
                <td><input type="submit" value="Create product"></td>
            </tr>
        </table>
    </fieldset>
</form>
</body>
</html>
