package com.said.palidmarketapp.business.concretes;

import com.said.palidmarketapp.business.abstracts.ProductService;
import com.said.palidmarketapp.core.utilities.results.*;
import com.said.palidmarketapp.dataAccess.abstracts.ProductDao;
import com.said.palidmarketapp.entities.Category;
import com.said.palidmarketapp.entities.Product;
import com.said.palidmarketapp.mapper.dto.CategoryDto;
import com.said.palidmarketapp.mapper.dto.ProductDto;
import com.said.palidmarketapp.mapstruct.CategoryMapper;
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
    private final CategoryMapper categoryMapper;

    @Override
    public Result add(ProductDto productDto) {
        Product product = modelMapper.map(productDto, Product.class);
        CategoryDto categoryDto = productDto.getCategoryDto();
        product.setCategory(categoryMapper.mapDtoToEntity(categoryDto));
        modelMapper.map(this.productDao.save(product), ProductDto.class);
        return new SuccessResult("Product added");
    }

    @Override
    public DataResult<List<ProductDto>> getByCategoryId(int id) {
        List<Product> products = productDao.getByCategoryId(id);
        if (products != null){
            return new SuccessDataResult<>(products.stream().map(product -> modelMapper.map(product, ProductDto.class)).collect(Collectors.toList()), "Getting categories is successfully");
        }

        return new ErrorDataResult<>(null, "Getting categories is unsuccessfully");
    }

    @Override
    public DataResult<List<ProductDto>> getAll() {
        List<Product> products = productDao.findAll();
        List<ProductDto> productDtos = products.stream()
                .map(product -> modelMapper.map(product, ProductDto.class))
                .collect(Collectors.toList());

        for (int i = 0; i < products.size(); i++) {
            Product product = products.get(i);
            ProductDto productDto = productDtos.get(i);

            if (product.getCategory() != null) {
                Category category = product.getCategory();
                productDto.setCategoryDto(categoryMapper.mapEntityToDto(category));
            }
        }
        if (modelMapper != null && categoryMapper != null) {
            return new SuccessDataResult<>(productDtos, "Data Listed");
        } else {
            return new ErrorDataResult<>(null,"ModelMapper or CategoryMapper is null");
        }
    }
}
