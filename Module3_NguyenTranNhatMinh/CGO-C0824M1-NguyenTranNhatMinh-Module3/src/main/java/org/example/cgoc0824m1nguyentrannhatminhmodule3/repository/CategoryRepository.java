package org.example.cgoc0824m1nguyentrannhatminhmodule3.repository;

import org.example.cgoc0824m1nguyentrannhatminhmodule3.model.Category;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoryRepository {

    public List<Category> findAll() {
        List<Category> categories = new ArrayList<>();
        try {
            Statement statement = BaseRepository.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM categories");
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                categories.add(new Category(id, name));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return categories;
    }

    public void save(Category category) {
        try {
            PreparedStatement preparedStatement = BaseRepository.getConnection().prepareStatement("INSERT INTO categories (name) VALUES (?)");
            preparedStatement.setString(1, category.getName());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Category findById(int id) {
        Category category = null;
        try {
            PreparedStatement preparedStatement = BaseRepository.getConnection().prepareStatement("SELECT * FROM categories WHERE id = ?");
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                String name = resultSet.getString("name");
                category = new Category(id, name);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return category;
    }

    public Category findCategoryByName(String name) {
        Category category = null;
        try {
            PreparedStatement preparedStatement = BaseRepository.getConnection().prepareStatement("SELECT * FROM categories WHERE name = ?");
            preparedStatement.setString(1, name);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                int id = resultSet.getInt("id");
                category = new Category(id, name);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return category;
    }

    public void update(int id, Category category) {
        try {
            PreparedStatement preparedStatement = BaseRepository.getConnection().prepareStatement("UPDATE categories SET name = ? WHERE id = ?");
            preparedStatement.setString(1, category.getName());
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void remove(int id) {
        try {
            PreparedStatement preparedStatement = BaseRepository.getConnection().prepareStatement("DELETE FROM categories WHERE id = ?");
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
