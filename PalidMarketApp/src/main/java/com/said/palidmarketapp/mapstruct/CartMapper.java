package com.said.palidmarketapp.mapstruct;

import com.said.palidmarketapp.entities.Cart;
import com.said.palidmarketapp.mapper.dto.CartDto;
import com.said.palidmarketapp.mapper.dto.CartProductDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CartMapper {
    @Mapping(target="user.id",source="userId")
    @Mapping(target="product.id",source="productId")
    Cart mapDtoToEntity(CartDto cartDto);
    CartProductDto mapEntityToCartDto(Cart cart);
    List<CartProductDto> mapEntityToCartDtos(List<Cart> cart);
}
