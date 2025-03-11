package org.example.cgoc0824m1nguyentrannhatminhmodule3.service;



import org.example.cgoc0824m1nguyentrannhatminhmodule3.model.Product;

import java.util.List;

public interface ProductService {
    List<Product> findAll();

    void save(Product product);

    Product findById(int id);

    void update(int id, Product product);

    void remove(int id);
    List<Product> searchProducts(String name, String price, String category, String color);
}