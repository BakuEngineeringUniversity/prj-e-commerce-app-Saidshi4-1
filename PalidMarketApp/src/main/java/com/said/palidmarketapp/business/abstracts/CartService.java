package com.said.palidmarketapp.business.abstracts;

import com.said.palidmarketapp.core.utilities.results.DataResult;
import com.said.palidmarketapp.core.utilities.results.Result;
import com.said.palidmarketapp.mapper.dto.CartDto;
import com.said.palidmarketapp.mapper.dto.CartProductDto;

import java.util.List;


public interface CartService {
    Result add(CartDto cartDto);
    DataResult<List<CartProductDto>> getCartProducts(Integer phoneNumber);
}
