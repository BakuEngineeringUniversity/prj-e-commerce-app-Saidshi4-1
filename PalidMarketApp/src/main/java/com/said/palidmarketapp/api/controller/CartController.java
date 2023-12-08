package com.said.palidmarketapp.api.controller;

import com.said.palidmarketapp.business.abstracts.CartService;
import com.said.palidmarketapp.business.concretes.CartManager;
import com.said.palidmarketapp.entities.Cart;
import com.said.palidmarketapp.entities.Product;
import com.said.palidmarketapp.mapper.dto.CartDto;
import com.said.palidmarketapp.mapper.dto.CartProductDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/carts")
@RequiredArgsConstructor
public class CartController {
    private final CartManager cartManager;

    @PostMapping("/add")
    public void addCart(@RequestBody CartDto cartDto) {
        cartManager.add(cartDto);
    }

    @GetMapping("{userId}")
    public List<CartProductDto> getCartProducts(@PathVariable Integer userId){
        return cartManager.getCartProducts(userId);
    }

}