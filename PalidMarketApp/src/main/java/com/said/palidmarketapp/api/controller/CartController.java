package com.said.palidmarketapp.api.controller;

import com.said.palidmarketapp.business.abstracts.CartService;
import com.said.palidmarketapp.business.concretes.CartManager;
import com.said.palidmarketapp.core.utilities.results.DataResult;
import com.said.palidmarketapp.core.utilities.results.Result;
import com.said.palidmarketapp.core.utilities.results.SuccessResult;
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
    private final CartService cartService;
    private final CartManager cartManager;

    @PostMapping("/user/add")
    public Result addCart(@RequestBody CartDto cartDto) {
        return cartManager.add(cartDto);
    }

    @GetMapping("/user/{userId}")
    public DataResult<List<CartProductDto>> getCartProducts(@PathVariable Integer userId){
        return cartService.getCartProducts(userId);
    }

    @GetMapping("/admin/getAllCart")
    public DataResult<List<CartDto>> getAll(){
        return cartManager.getAll();
    }
}