package org.example.cgoc0824m1nguyentrannhatminhmodule3.service.impl;


import org.example.cgoc0824m1nguyentrannhatminhmodule3.model.Product;
import org.example.cgoc0824m1nguyentrannhatminhmodule3.repository.ProductRepository;
import org.example.cgoc0824m1nguyentrannhatminhmodule3.service.ProductService;

import java.util.List;

public class ProductServiceImpl implements ProductService {

    private static ProductRepository productRepository = new ProductRepository();

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public void save(Product product) {
        productRepository.save(product);
    }

    @Override
    public Product findById(int id) {
        return productRepository.findById(id);
    }

    @Override
    public void update(int id, Product product) {
        productRepository.update(id, product);
    }

    @Override
    public void remove(int id) {
        productRepository.remove(id);
    }

    @Override
    public List<Product> searchProducts(String name, String price, String category, String color) {
        return productRepository.searchProducts(name, price, category, color);
    }

}