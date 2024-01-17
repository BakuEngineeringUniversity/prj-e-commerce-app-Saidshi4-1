package com.said.palidmarketapp.mapper.dto;

import com.said.palidmarketapp.entities.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {
    private String name;
    private double unitPrice;
    private CategoryDto categoryDto;
}
