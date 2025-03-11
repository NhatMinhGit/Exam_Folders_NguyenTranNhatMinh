<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Delete Product</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 20px;
            padding: 20px;
        }
        h1 {
            color: red;
        }
        fieldset {
            border: 1px solid #ccc;
            padding: 15px;
        }
        table {
            width: 100%;
            border-collapse: collapse;
        }
        td {
            padding: 8px;
        }
        .button-container {
            margin-top: 15px;
        }
        input[type="submit"], .cancel-button {
            padding: 8px 15px;
            font-size: 14px;
            cursor: pointer;
            text-decoration: none;
            border: none;
            border-radius: 5px;
        }
        input[type="submit"] {
            background-color: red;
            color: white;
        }
        .cancel-button {
            background-color: gray;
            color: white;
        }
    </style>
</head>
<body>
<h1>Delete Product</h1>
<p><a href="/products">Back to Product List</a></p>

<form method="post">
    <h3>Are you sure you want to delete this product?</h3>
    <fieldset>
        <legend>Product Information</legend>
        <table>
            <tr>
                <td><strong>ID:</strong></td>
                <td>${requestScope["product"].getId()}</td>
            </tr>
            <tr>
                <td><strong>Name:</strong></td>
                <td>${requestScope["product"].getName()}</td>
            </tr>
            <tr>
                <td><strong>Price:</strong></td>
                <td>${requestScope["product"].getPrice()}</td>
            </tr>
            <tr>
                <td><strong>Quantity:</strong></td>
                <td>${requestScope["product"].getQuantity()}</td>
            </tr>
            <tr>
                <td><strong>Category:</strong></td>
                <td>${requestScope["product"].getCategory().getName()}</td>
            </tr>
        </table>
    </fieldset>

    <div class="button-container">
        <input type="hidden" name="id" value="${requestScope["product"].getId()}">
        <input type="submit" value="Delete Product">
        <a href="/products" class="cancel-button">Cancel</a>
    </div>
</form>

</body>
</html>