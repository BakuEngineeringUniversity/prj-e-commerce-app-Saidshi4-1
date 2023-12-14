package com.said.palidmarketapp.business.concretes;

import com.said.palidmarketapp.business.abstracts.CartService;
import com.said.palidmarketapp.dataAccess.abstracts.CartDao;
import com.said.palidmarketapp.entities.Cart;
import com.said.palidmarketapp.entities.Product;
import com.said.palidmarketapp.entities.User;
import com.said.palidmarketapp.mapper.dto.CartDto;
import com.said.palidmarketapp.mapper.dto.CartProductDto;
import com.said.palidmarketapp.mapper.dto.ProductDto;
import com.said.palidmarketapp.mapstruct.CartMapper;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CartManager implements CartService {
    private final CartDao cartDao;
    private final CartMapper cartMapper;

    @Override
    public void add(CartDto cartDto) {
        cartDao.save(cartMapper.mapDtoToEntity(cartDto));
    }

    @Override
    public List<CartProductDto> getCartProducts(Integer phoneNumber) {
        return cartMapper.mapEntityToCartDtos(cartDao.findCartByUserId(phoneNumber));
    }


}
