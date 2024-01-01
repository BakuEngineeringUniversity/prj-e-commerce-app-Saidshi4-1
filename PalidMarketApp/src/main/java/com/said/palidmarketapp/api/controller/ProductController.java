package com.said.palidmarketapp.api.controller;

import com.said.palidmarketapp.business.abstracts.ProductService;
import com.said.palidmarketapp.core.utilities.results.DataResult;
import com.said.palidmarketapp.core.utilities.results.Result;
import com.said.palidmarketapp.core.utilities.results.SuccessDataResult;
import com.said.palidmarketapp.core.utilities.results.SuccessResult;
import com.said.palidmarketapp.entities.Product;
import com.said.palidmarketapp.mapper.dto.ProductDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @PostMapping("/add")
    public Result add(@RequestBody ProductDto productDto){
        return this.productService.add(productDto);
    }

    @GetMapping("/getAll")
    public DataResult<List<ProductDto>> getAll(){
        return productService.getAll();
    }

    @GetMapping("/{getByCategoryId}")
    public List<ProductDto> getByCategoryId(@PathVariable int getByCategoryId){
        return productService.getByCategoryId(getByCategoryId);
    }

}
