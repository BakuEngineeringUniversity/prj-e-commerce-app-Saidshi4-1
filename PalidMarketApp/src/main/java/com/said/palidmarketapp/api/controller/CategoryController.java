package com.said.palidmarketapp.api.controller;

import com.said.palidmarketapp.business.abstracts.CategoryService;
import com.said.palidmarketapp.core.utilities.results.DataResult;
import com.said.palidmarketapp.entities.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
public class CategoryController   {
    private final CategoryService categoryService;

    @GetMapping("/getAll")
    public List<Category> getAll(){
        return categoryService.getAll();
    }
}
