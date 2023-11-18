package com.said.palidmarketapp.business.abstracts;

import com.said.palidmarketapp.entities.Cart;
import com.said.palidmarketapp.entities.Product;

import java.util.List;

public interface CartService {
    void add(Cart cart);
    void delete(int id);
    List<Cart> getAll();

}
