package com.said.palidmarketapp.business.concretes;

import com.said.palidmarketapp.business.abstracts.CartService;
import com.said.palidmarketapp.core.utilities.results.DataResult;
import com.said.palidmarketapp.core.utilities.results.Result;
import com.said.palidmarketapp.core.utilities.results.SuccessDataResult;
import com.said.palidmarketapp.core.utilities.results.SuccessResult;
import com.said.palidmarketapp.dataAccess.abstracts.CartDao;
import com.said.palidmarketapp.mapper.dto.CartDto;
import com.said.palidmarketapp.mapper.dto.CartProductDto;
import com.said.palidmarketapp.mapstruct.CartMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CartManager implements CartService {
    private final CartDao cartDao;
    private final CartMapper cartMapper;

    @Override
    public Result add(CartDto cartDto) {
        cartDao.save(cartMapper.mapDtoToEntity(cartDto));
        return new SuccessResult("Cart add successfully");
    }

    @Override
    public DataResult<List<CartProductDto>> getCartProducts(Integer phoneNumber) {
        return new SuccessDataResult<>(cartMapper.mapEntityToCartDtos(cartDao.findCartByUserId(phoneNumber)), "Getting products successful");
    }


}
