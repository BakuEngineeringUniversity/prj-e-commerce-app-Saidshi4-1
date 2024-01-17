package com.said.palidmarketapp.business.concretes;

import com.said.palidmarketapp.business.abstracts.CartService;
import com.said.palidmarketapp.core.utilities.results.*;
import com.said.palidmarketapp.dataAccess.abstracts.CartDao;
import com.said.palidmarketapp.entities.Cart;
import com.said.palidmarketapp.mapper.dto.CartDto;
import com.said.palidmarketapp.mapper.dto.CartProductDto;
import com.said.palidmarketapp.mapstruct.CartMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Slf4j
@Service
@RequiredArgsConstructor
public class CartManager implements CartService {
    private final CartDao cartDao;
    private final CartMapper cartMapper;
    private final ModelMapper modelMapper;

    @Override
    public Result add(CartDto cartDto) {
        log.info("application.addCart.start");
        cartDao.save(cartMapper.mapDtoToEntity(cartDto));
        log.info("application.addCart.end");
        return new SuccessResult("Cart add successfully");
    }
    @Override
    public DataResult<List<CartProductDto>> getCartProducts(Integer phoneNumber) {
        log.info("application.getCartProducts.start");
        List<Cart> carts = cartDao.findCartByUserId(phoneNumber);
        if(carts != null){
            log.info("application.getCartProducts.start.successfully");
            return new SuccessDataResult<>(cartMapper.mapEntityToCartDtos(carts), "Updating is successfully");
        }
        else{
            log.info("application.getCartProducts.start.unsuccessfully");
            return new ErrorDataResult<>(null,"Cart not found!!!");
        }
    }

    @Override
    public DataResult<List<CartDto>> getAll() {
        log.info("application.getAllCarts.start");
        List<Cart> carts = cartDao.findAll();
        if(carts != null){
            log.info("application.getAllCarts.end.successfully");
            return new SuccessDataResult<>(carts.stream().map(cart -> modelMapper.map(cart, CartDto.class)).collect(Collectors.toList()), "Getting carts is successfully");
        }
        else {
            log.info("application.getAllCarts.end.unsuccessfully");
            return new ErrorDataResult<>(null, "Cart not found");
        }
    }
}
