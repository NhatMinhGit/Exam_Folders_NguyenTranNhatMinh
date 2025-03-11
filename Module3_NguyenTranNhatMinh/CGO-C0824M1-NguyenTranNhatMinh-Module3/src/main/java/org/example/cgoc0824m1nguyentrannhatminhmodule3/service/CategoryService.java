package org.example.cgoc0824m1nguyentrannhatminhmodule3.service;

import org.example.cgoc0824m1nguyentrannhatminhmodule3.model.Category;
import org.example.cgoc0824m1nguyentrannhatminhmodule3.model.Product;

import java.util.List;

public interface CategoryService {
    List<Category> findAll();

    void save(Category category);

    Category findById(int id);
    Category findCategoryByName(String name);

    void update(int id, Category category);

    void remove(int id);

}
