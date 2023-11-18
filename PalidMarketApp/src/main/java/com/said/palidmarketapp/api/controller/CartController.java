package com.said.palidmarketapp.api.controller;

import com.said.palidmarketapp.business.abstracts.CartService;
import com.said.palidmarketapp.entities.Cart;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/carts")
@RequiredArgsConstructor
public class CartController {
    private final CartService cartService;
    @PostMapping("/add")
    public void add(@RequestBody Cart cart){
        cartService.add(cart);
    }
    @DeleteMapping("/delete")
    public void delete(@RequestParam int id){
        cartService.delete(id);
    }
    @GetMapping("/getAll")
    public List<Cart> getAll() {
        return cartService.getAll();
    }

}
