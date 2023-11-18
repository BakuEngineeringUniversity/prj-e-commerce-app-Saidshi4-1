package com.said.palidmarketapp.api.controller;

import com.said.palidmarketapp.business.abstracts.ProductService;
import com.said.palidmarketapp.entities.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;
    @PostMapping("/save")
    public void save(@RequestBody Product product){
        productService.add(product);
    }

    @GetMapping("/getAll")
    public List<Product> getAll(){
        return productService.getAll();
    }

    @GetMapping("/getByCategoryId")
    public List<Product> getByCategoryId(@RequestParam int id){
        return productService.getByCategoryId(id);
    }

}
