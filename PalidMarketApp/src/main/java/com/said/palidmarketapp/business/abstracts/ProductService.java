package com.said.palidmarketapp.business.abstracts;

import com.said.palidmarketapp.entities.Product;

import java.util.List;

public interface ProductService {
    void add(Product product);
    List<Product> getByCategoryId(int id);
    List <Product> getAll();
}
