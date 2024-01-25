package com.said.palidmarketapp.api.controller;

import com.said.palidmarketapp.business.abstracts.ProductService;
import com.said.palidmarketapp.core.utilities.results.DataResult;
import com.said.palidmarketapp.core.utilities.results.Result;
import com.said.palidmarketapp.core.utilities.results.SuccessDataResult;
import com.said.palidmarketapp.core.utilities.results.SuccessResult;
import com.said.palidmarketapp.entities.Product;
import com.said.palidmarketapp.mapper.dto.CategoryDto;
import com.said.palidmarketapp.mapper.dto.ProductDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @PostMapping("/admin/add")
    public Result add(@RequestBody ProductDto productDto){
        return this.productService.add(productDto);
    }

    @GetMapping("/admin/getAll")
    public DataResult<List<ProductDto>> getAll(){
        return productService.getAll();
    }

    @GetMapping("/user/{getByCategoryId}")
    public DataResult<List<ProductDto>> getByCategoryId(@PathVariable int getByCategoryId){
        return productService.getByCategoryId(getByCategoryId);
    }

    @PatchMapping("/admin/{productId}/{price}")
    public ResponseEntity<Result> updateName(@PathVariable int productId, @PathVariable Double price) {
        try {
            Result result = productService.updatePrice(productId, price);
            if (result.isSuccess()) {
                return ResponseEntity.ok(result);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(result);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @DeleteMapping("/admin/{id}")
    public ResponseEntity<Result> deleteProduct(@PathVariable int id) {
        try {
            Result result = productService.deleteProduct(id);
            if (result.isSuccess()) {
                return ResponseEntity.ok(result);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(result);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
