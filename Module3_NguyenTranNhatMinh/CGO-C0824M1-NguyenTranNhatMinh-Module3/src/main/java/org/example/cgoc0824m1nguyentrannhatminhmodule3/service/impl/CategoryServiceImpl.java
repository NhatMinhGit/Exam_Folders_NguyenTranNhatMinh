package org.example.cgoc0824m1nguyentrannhatminhmodule3.service.impl;

import org.example.cgoc0824m1nguyentrannhatminhmodule3.model.Category;
import org.example.cgoc0824m1nguyentrannhatminhmodule3.model.Product;
import org.example.cgoc0824m1nguyentrannhatminhmodule3.repository.CategoryRepository;
import org.example.cgoc0824m1nguyentrannhatminhmodule3.service.CategoryService;

import java.util.List;

public class CategoryServiceImpl implements CategoryService {

    private static CategoryRepository categoryRepository = new CategoryRepository();

    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public void save(Category category) {
        categoryRepository.save(category);
    }

    @Override
    public Category findById(int id) {
        return categoryRepository.findById(id);
    }

    @Override
    public Category findCategoryByName(String name) {
        return categoryRepository.findCategoryByName(name);
    }

    @Override
    public void update(int id, Category category) {
        categoryRepository.update(id, category);
    }

    @Override
    public void remove(int id) {
        categoryRepository.remove(id);
    }


}
