package org.example.cgoc0824m1nguyentrannhatminhmodule3.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.example.cgoc0824m1nguyentrannhatminhmodule3.model.Category;
import org.example.cgoc0824m1nguyentrannhatminhmodule3.model.Product;
import org.example.cgoc0824m1nguyentrannhatminhmodule3.service.CategoryService;
import org.example.cgoc0824m1nguyentrannhatminhmodule3.service.ProductService;
import org.example.cgoc0824m1nguyentrannhatminhmodule3.service.impl.CategoryServiceImpl;
import org.example.cgoc0824m1nguyentrannhatminhmodule3.service.impl.ProductServiceImpl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@WebServlet(name = "ProductServlet", urlPatterns = "/products")
public class ProductServlet extends HttpServlet {
    private final ProductService productService = new ProductServiceImpl();
    private final CategoryService categoryService = new CategoryServiceImpl();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "create":
                createProduct(request, response);
                break;
            case "edit":
                updateProduct(request, response);
                break;
            case "delete":
                deleteProduct(request, response);
                break;
            case "search":
                searchProduct(request, response);
                break;
            default:
                break;
        }
    }

    private void deleteProduct(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Product product = productService.findById(id);
        if (product == null) {
            response.sendRedirect("error-404.jsp");
        } else {
            productService.remove(id);
            HttpSession session = request.getSession();
            session.setAttribute("message", "Product deleted successfully!");
            response.sendRedirect("/products");
        }
    }

    private void updateProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String priceStr = request.getParameter("price");
        String quantityStr = request.getParameter("quantity");
        String color = request.getParameter("color");
        String description = request.getParameter("description");
        String categoryIdStr = request.getParameter("category");

        List<String> errors = new ArrayList<>();

        // Validate
        if (name == null || name.trim().isEmpty()) {
            errors.add("Tên sản phẩm không được để trống.");
        }

        double price = 0;
        try {
            price = Double.parseDouble(priceStr);
            if (price < 10000000) {
                errors.add("Giá sản phẩm phải lớn hơn 10.000.000 VNĐ.");
            }
        } catch (NumberFormatException e) {
            errors.add("Giá phải là một số hợp lệ.");
        }

        int quantity = 0;
        try {
            quantity = Integer.parseInt(quantityStr);
            if (quantity <= 0) {
                errors.add("Số lượng phải là số nguyên dương.");
            }
        } catch (NumberFormatException e) {
            errors.add("Số lượng phải là một số nguyên hợp lệ.");
        }

        List<String> validColors = Arrays.asList("Đỏ", "Xanh", "Đen", "Trắng", "Vàng");
        if (!validColors.contains(color)) {
            errors.add("Màu sắc không hợp lệ.");
        }

        int categoryId = 0;
        Category category = null;
        try {
            categoryId = Integer.parseInt(categoryIdStr);
            category = categoryService.findById(categoryId);
            if (category == null) {
                errors.add("Danh mục không hợp lệ.");
            }
        } catch (NumberFormatException e) {
            errors.add("Danh mục không hợp lệ.");
        }

        // Kiểm tra sản phẩm có tồn tại không
        Product product = productService.findById(id);
        if (product == null) {
            response.sendRedirect("error-404.jsp");
            return;
        }

        // Nếu có lỗi, quay lại form edit và hiển thị lỗi
        if (!errors.isEmpty()) {
            request.setAttribute("errors", errors);
            request.setAttribute("product", product);
            request.setAttribute("categories", categoryService.findAll());
            request.getRequestDispatcher("product/edit.jsp").forward(request, response);
            return;
        }

        // Nếu không có lỗi, cập nhật sản phẩm
        product.setName(name);
        product.setPrice(price);
        product.setQuantity(quantity);
        product.setColor(color);
        product.setDescription(description);
        product.setCategory(category);
        productService.update(id, product);

        request.setAttribute("product", product);
        request.setAttribute("message", "Thông tin sản phẩm đã được cập nhật.");
        request.getRequestDispatcher("product/edit.jsp").forward(request, response);
    }


    private void createProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String priceStr = request.getParameter("price");
        String quantityStr = request.getParameter("quantity");
        String color = request.getParameter("color");
        String description = request.getParameter("description");
        String categoryIdStr = request.getParameter("category");

        List<String> errors = new ArrayList<>();

        // Validate
        if (name == null || name.trim().isEmpty()) {
            errors.add("Tên sản phẩm không được để trống.");
        }

        double price = 0;
        try {
            price = Double.parseDouble(priceStr);
            if (price < 10000000) {
                errors.add("Giá sản phẩm phải lớn hơn 10.000.000 VNĐ.");
            }
        } catch (NumberFormatException e) {
            errors.add("Giá phải là một số hợp lệ.");
        }

        int quantity = 0;
        try {
            quantity = Integer.parseInt(quantityStr);
            if (quantity <= 0) {
                errors.add("Số lượng phải là số nguyên dương.");
            }
        } catch (NumberFormatException e) {
            errors.add("Số lượng phải là một số nguyên hợp lệ.");
        }

        List<String> validColors = Arrays.asList("Đỏ", "Xanh", "Đen", "Trắng", "Vàng");
        if (!validColors.contains(color)) {
            errors.add("Màu sắc không hợp lệ.");
        }

        int categoryId = 0;
        Category category = null;
        try {
            categoryId = Integer.parseInt(categoryIdStr);
            category = categoryService.findById(categoryId);
            if (category == null) {
                errors.add("Danh mục không hợp lệ.");
            }
        } catch (NumberFormatException e) {
            errors.add("Danh mục không hợp lệ.");
        }


        // Nếu có lỗi, quay lại form và hiển thị thông báo
        if (!errors.isEmpty()) {
            request.setAttribute("errors", errors);
            request.setAttribute("name", name);
            request.setAttribute("price", priceStr);
            request.setAttribute("quantity", quantityStr);
            request.setAttribute("color", color);
            request.setAttribute("description", description);
            request.setAttribute("categoryId", categoryIdStr);
            request.setAttribute("categories", categoryService.findAll());
            request.getRequestDispatcher("product/create.jsp").forward(request, response);
            return;
        }

        // Nếu không có lỗi, lưu sản phẩm
        Product product = new Product(name, price, quantity, color, description, category);
        productService.save(product);

        request.setAttribute("message", "Sản phẩm mới đã được tạo.");
        request.getRequestDispatcher("product/create.jsp").forward(request, response);
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "create":
                showCreateForm(request, response);
                break;
            case "edit":
                showEditForm(request, response);
                break;
            case "delete":
                showDeleteForm(request, response);
                break;
            case "search":
                searchProduct(request, response);
                break;
            default:
                listProducts(request, response);
                break;
        }
    }

    private void viewProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Product product = productService.findById(id);
        if (product == null) {
            response.sendRedirect("error-404.jsp");
        } else {
            request.setAttribute("product", product);
            request.getRequestDispatcher("product/view.jsp").forward(request, response);
        }
    }

    private void showDeleteForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Product product = productService.findById(id);
        if (product == null) {
            response.sendRedirect("error-404.jsp");
        } else {
            request.setAttribute("product", product);
            request.getRequestDispatcher("product/delete.jsp").forward(request, response);
        }
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Product product = productService.findById(id);
        List<Category> categories = categoryService.findAll(); // Lấy danh sách danh mục

        if (product == null) {
            response.sendRedirect("error-404.jsp");
        } else {
            request.setAttribute("product", product);
            request.setAttribute("categories", categories); // Thêm danh sách categories vào requestScope
            request.getRequestDispatcher("product/edit.jsp").forward(request, response);
        }
    }

    private void showCreateForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Category> categories = categoryService.findAll();
        request.setAttribute("categories", categories);
        request.getRequestDispatcher("product/create.jsp").forward(request, response);
    }

    private void listProducts(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Product> products = productService.findAll();
        request.setAttribute("products", products);
        request.getRequestDispatcher("product/list.jsp").forward(request, response);
    }

    private void searchProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String price = request.getParameter("price");
        String category = request.getParameter("category");
        String color = request.getParameter("color");

        // Gọi phương thức tìm kiếm với các tham số
        List<Product> filteredProducts = productService.searchProducts(name, price, category, color);

        // Đưa dữ liệu vào request để hiển thị trên JSP
        request.setAttribute("products", filteredProducts);
        request.setAttribute("name", name);
        request.setAttribute("price", price);
        request.setAttribute("category", category);
        request.setAttribute("color", color);

        // Chuyển hướng đến trang danh sách sản phẩm
        RequestDispatcher dispatcher = request.getRequestDispatcher("product/list.jsp");
        dispatcher.forward(request, response);
    }



}