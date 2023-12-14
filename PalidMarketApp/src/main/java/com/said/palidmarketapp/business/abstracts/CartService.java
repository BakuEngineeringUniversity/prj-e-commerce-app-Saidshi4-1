package com.said.palidmarketapp.business.abstracts;

import com.said.palidmarketapp.mapper.dto.CartDto;
import com.said.palidmarketapp.mapper.dto.CartProductDto;

import java.util.List;


public interface CartService {
    void add(CartDto cartDto);
    List<CartProductDto> getCartProducts(Integer phoneNumber);
}
