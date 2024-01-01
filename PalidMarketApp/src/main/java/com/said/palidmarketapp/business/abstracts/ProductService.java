package com.said.palidmarketapp.business.abstracts;

import com.said.palidmarketapp.core.utilities.results.DataResult;
import com.said.palidmarketapp.core.utilities.results.Result;
import com.said.palidmarketapp.entities.Product;
import com.said.palidmarketapp.mapper.dto.ProductDto;

import java.util.List;

public interface ProductService {
    Result add(ProductDto productDto);
    List<ProductDto> getByCategoryId(int id);
    DataResult<List<ProductDto>> getAll();
}
