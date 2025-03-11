package org.example.cgoc0824m1nguyentrannhatminhmodule3.repository;

import org.example.cgoc0824m1nguyentrannhatminhmodule3.model.Category;
import org.example.cgoc0824m1nguyentrannhatminhmodule3.model.Product;
import org.example.cgoc0824m1nguyentrannhatminhmodule3.service.CategoryService;
import org.example.cgoc0824m1nguyentrannhatminhmodule3.service.impl.CategoryServiceImpl;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ProductRepository {
    private final CategoryServiceImpl categoryService;

    public ProductRepository() {
        this.categoryService = new CategoryServiceImpl();
    }
    public List<Product> findAll() {
        List<Product> products = new ArrayList<>();
        try {
            Statement statement = BaseRepository.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT p.*, c.id as category_id, c.name as category_name FROM products p JOIN categories c ON p.category_id = c.id");
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                double price = resultSet.getDouble("price");
                int quantity = resultSet.getInt("quantity");
                String color = resultSet.getString("color");
                String description = resultSet.getString("description");
                Category category = new Category(resultSet.getInt("category_id"), resultSet.getString("category_name"));
                products.add(new Product(id, name, price, quantity, color, description, category));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return products;
    }

    public void save(Product product) {
        try {
            PreparedStatement preparedStatement = BaseRepository.getConnection()
                    .prepareStatement("INSERT INTO products (name, price, quantity, color, description, category_id) VALUES (?, ?, ?, ?, ?, ?)");
            preparedStatement.setString(1, product.getName());
            preparedStatement.setDouble(2, product.getPrice());
            preparedStatement.setInt(3, product.getQuantity());
            preparedStatement.setString(4, product.getColor());
            preparedStatement.setString(5, product.getDescription());
            preparedStatement.setInt(6, product.getCategory().getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Product findById(int id) {
        Product product = null;
        try {
            PreparedStatement preparedStatement = BaseRepository.getConnection()
                    .prepareStatement("SELECT p.*, c.id as category_id, c.name as category_name FROM products p JOIN categories c ON p.category_id = c.id WHERE p.id = ?");
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                String name = resultSet.getString("name");
                double price = resultSet.getDouble("price");
                int quantity = resultSet.getInt("quantity");
                String color = resultSet.getString("color");
                String description = resultSet.getString("description");
                Category category = new Category(resultSet.getInt("category_id"), resultSet.getString("category_name"));
                product = new Product(id, name, price, quantity, color, description, category);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return product;
    }

    public void update(int id, Product product) {
        try {
            PreparedStatement preparedStatement = BaseRepository.getConnection()
                    .prepareStatement("UPDATE products SET name = ?, price = ?, quantity = ?, color = ?, description = ?, category_id = ? WHERE id = ?");
            preparedStatement.setString(1, product.getName());
            preparedStatement.setDouble(2, product.getPrice());
            preparedStatement.setInt(3, product.getQuantity());
            preparedStatement.setString(4, product.getColor());
            preparedStatement.setString(5, product.getDescription());
            preparedStatement.setInt(6, product.getCategory().getId());
            preparedStatement.setInt(7, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void remove(int id) {
        try {
            PreparedStatement preparedStatement = BaseRepository.getConnection()
                    .prepareStatement("DELETE FROM products WHERE id = ?");
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public List<Product> searchByName(String keyword) {
        List<Product> productList = new ArrayList<>();
        String query = "SELECT * FROM products WHERE name LIKE ?";

        try (Connection connection = BaseRepository.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, "%" + keyword + "%"); // Sử dụng LIKE để tìm kiếm chuỗi chứa từ khóa
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                double price = resultSet.getDouble("price");
                int quantity = resultSet.getInt("quantity");
                String color = resultSet.getString("color");
                String description = resultSet.getString("description");
                int categoryId = resultSet.getInt("category_id");

                // Tìm category dựa vào categoryId
                Category category = categoryService.findById(categoryId);

                Product product = new Product(id, name, price, quantity, color, description, category);
                productList.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return productList;
    }
    public List<Product> searchProductsByName(String keyword) {
        return findAll().stream()
                .filter(p -> p.getName().toLowerCase().contains(keyword.toLowerCase()))
                .collect(Collectors.toList());
    }

    public List<Product> searchProductsByPrice(String keyword) {
        try {
            double price = Double.parseDouble(keyword);
            return findAll().stream()
                    .filter(p -> p.getPrice() == price)
                    .collect(Collectors.toList());
        } catch (NumberFormatException e) {
            return new ArrayList<>(); // Trả về danh sách rỗng nếu nhập sai định dạng số
        }
    }

    public List<Product> searchProducts(String name, String price, String categoryName, String color) {
        List<Product> result = new ArrayList<>();
        List<String> conditions = new ArrayList<>();
        List<Object> parameters = new ArrayList<>();

        // Truy vấn JOIN để lấy thông tin danh mục từ bảng categories
        String baseQuery = "SELECT p.*, c.id AS category_id, c.name AS category_name " +
                "FROM products p " +
                "JOIN categories c ON p.category_id = c.id " +
                "WHERE 1=1";

        if (name != null && !name.trim().isEmpty()) {
            conditions.add("p.name LIKE ?");
            parameters.add("%" + name.trim() + "%");
        }
        if (price != null && !price.trim().isEmpty()) {
            if (price.matches("\\d+(\\.\\d+)?")) { // Kiểm tra price có phải số hợp lệ không
                conditions.add("p.price = ?");
                parameters.add(Double.parseDouble(price));
            } else {
                return result; // Nếu price không hợp lệ, trả về danh sách rỗng
            }
        }
        if (categoryName != null && !categoryName.trim().isEmpty()) {
            conditions.add("c.name LIKE ?");
            parameters.add("%" + categoryName.trim() + "%");
        }
        if (color != null && !color.trim().isEmpty()) {
            conditions.add("p.color LIKE ?");
            parameters.add("%" + color.trim() + "%");
        }

        // Kết hợp điều kiện vào câu SQL
        String sql = baseQuery + (conditions.isEmpty() ? "" : " AND " + String.join(" AND ", conditions));

        try (Connection conn = BaseRepository.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            // Gán giá trị cho PreparedStatement
            for (int i = 0; i < parameters.size(); i++) {
                if (parameters.get(i) instanceof String) {
                    stmt.setString(i + 1, (String) parameters.get(i));
                } else if (parameters.get(i) instanceof Double) {
                    stmt.setDouble(i + 1, (Double) parameters.get(i));
                }
            }

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    // Tạo đối tượng Category
                    Category category = new Category(rs.getInt("category_id"), rs.getString("category_name"));

                    // Tạo đối tượng Product
                    result.add(new Product(
                            rs.getInt("id"),
                            rs.getString("name"),
                            rs.getDouble("price"),
                            rs.getInt("quantity"),
                            rs.getString("color"),
                            rs.getString("description"),
                            category
                    ));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }



}