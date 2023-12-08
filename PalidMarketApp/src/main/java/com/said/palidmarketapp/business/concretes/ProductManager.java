package com.said.palidmarketapp.business.concretes;

import com.said.palidmarketapp.business.abstracts.ProductService;
import com.said.palidmarketapp.core.utilities.results.DataResult;
import com.said.palidmarketapp.core.utilities.results.Result;
import com.said.palidmarketapp.core.utilities.results.SuccessDataResult;
import com.said.palidmarketapp.core.utilities.results.SuccessResult;
import com.said.palidmarketapp.dataAccess.abstracts.ProductDao;
import com.said.palidmarketapp.entities.Product;
import com.said.palidmarketapp.mapper.dto.ProductDto;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductManager implements ProductService {
    private final ProductDao productDao;
    private final ModelMapper modelMapper;

    public Product getProductById(int productId) {
        return productDao.findById(productId).orElse(null);
    }

    @Override
    public Result add(ProductDto productDto) {
        Product product = modelMapper.map(productDto, Product.class);

        modelMapper.map(this.productDao.save(product), ProductDto.class);
        return new SuccessResult("Product added");
    }

    @Override
    public List<ProductDto> getByCategoryId(int id) {
        List<Product> products = productDao.getByCategoryId(id);

        return products.stream().map(product -> modelMapper.map(product, ProductDto.class)).collect(Collectors.toList());
    }

    @Override
    public DataResult<List<ProductDto>> getAll() {
        List <Product> products = productDao.findAll();
        return new SuccessDataResult<List<ProductDto>>(products.stream().map(product ->
                modelMapper.map(product, ProductDto.class)).collect(Collectors.toList()),"Data Listed");
    }


}
