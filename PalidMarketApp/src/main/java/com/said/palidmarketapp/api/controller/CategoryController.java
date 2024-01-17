package com.said.palidmarketapp.api.controller;

import com.said.palidmarketapp.business.abstracts.CategoryService;
import com.said.palidmarketapp.core.utilities.results.DataResult;
import com.said.palidmarketapp.core.utilities.results.Result;
import com.said.palidmarketapp.entities.Category;
import com.said.palidmarketapp.mapper.dto.UserDto;
import com.said.palidmarketapp.mapper.dto.UserSaveDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
public class CategoryController   {
    private final CategoryService categoryService;

    @PostMapping("/save")
    public ResponseEntity<DataResult<Category>> saveUser(@RequestBody Category category) {
        try {
            DataResult<Category> result = categoryService.saveCategory(category);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/getAll")
    public ResponseEntity<DataResult<List<Category>>> getAllCategories(){
        try {
            DataResult<List<Category>> result = categoryService.getAllCategory();
            if (result.isSuccess()) {
                return ResponseEntity.ok(result);
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(result);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PatchMapping("/{categoryId}/{img}")
    public ResponseEntity<Result> updateImage(@PathVariable int categoryId, @PathVariable String img) {
        try {
            Result result = categoryService.updateImg(categoryId, img);
            if (result.isSuccess()) {
                return ResponseEntity.ok(result);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(result);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Result> deleteCategory(@PathVariable int id) {
        try {
            Result result = categoryService.deleteCategory(id);
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
