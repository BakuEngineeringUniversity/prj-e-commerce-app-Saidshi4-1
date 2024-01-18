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
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
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
        log.info("productManager.getByCategoryId.start");
        List<Product> products = productDao.getByCategoryId(id);
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
            log.info("productManager.getByCategoryId.end.successfully");
            return new SuccessDataResult<>(productDtos, "Data Listed");
        } else {
            log.info("productManager.getByCategoryId.end.unsuccessfully");
            return new ErrorDataResult<>(null,"ModelMapper or CategoryMapper is null");
        }
    }

    @Override
    public DataResult<List<ProductDto>> getAll() {
        log.info("productManager.getAll.start");
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
            log.info("productManager.getAll.end.successfully");
            return new SuccessDataResult<>(productDtos, "Data Listed");
        } else {
            log.info("productManager.getByCategoryId.end.unsuccessfully");
            return new ErrorDataResult<>(null,"ModelMapper or CategoryMapper is null");
        }
    }

    @Override
    public Result deleteProduct(int id) {
        log.info("deleteProduct.start");
        if (productDao.existsById(id)){
            productDao.deleteById(id);
            log.info("deleteProduct.end.successfully");
            return new SuccessResult("Product is deleted successfully");
        }
        else {
            log.info("deleteProduct.end.unsuccessfully");
            return new ErrorResult("Product is not exist by id");
        }
    }

    @Override
    public Result updatePrice(int id, double price) {
        log.info("updatePrice.start");

        Optional<Product> optionalProduct = productDao.findById(id);

        if (optionalProduct.isPresent()) {
            Product product = optionalProduct.get();
            product.setUnitPrice(price);
            productDao.saveAndFlush(product);
            log.info("updatePrice.end.successfully");
            return new SuccessResult("Price update successfully");
        } else {
            log.info("updateProduct.end.unsuccessfully");
            return new ErrorResult("Product not found with id: " + id);
        }
    }
}
