package com.said.palidmarketapp.mapstruct;

import com.said.palidmarketapp.entities.Product;
import com.said.palidmarketapp.mapper.dto.ProductCartDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    ProductCartDto mapEntityToProductCartDto(Product product);
}
