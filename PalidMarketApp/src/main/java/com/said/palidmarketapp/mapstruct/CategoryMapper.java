package com.said.palidmarketapp.mapstruct;

import com.said.palidmarketapp.entities.Category;
import com.said.palidmarketapp.mapper.dto.CategoryDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")

public interface CategoryMapper {
    Category mapDtoToEntity(CategoryDto categoryDto);
    CategoryDto mapEntityToDto(Category category);
}
