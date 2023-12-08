package com.said.palidmarketapp.business.abstracts;

import com.said.palidmarketapp.entities.Cart;
import com.said.palidmarketapp.entities.Product;
import com.said.palidmarketapp.entities.User;
import com.said.palidmarketapp.mapper.dto.CartDto;
import com.said.palidmarketapp.mapper.dto.CartProductDto;
import com.said.palidmarketapp.mapper.dto.ProductDto;

import java.util.List;


public interface CartService {
    void add(CartDto cartDto);
    List<CartProductDto> getCartProducts(Integer userId);
}
