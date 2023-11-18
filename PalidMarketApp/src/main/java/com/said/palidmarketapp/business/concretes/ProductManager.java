package com.said.palidmarketapp.business.concretes;

import com.said.palidmarketapp.business.abstracts.ProductService;
import com.said.palidmarketapp.dataAccess.abstracts.ProductDao;
import com.said.palidmarketapp.entities.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class ProductManager implements ProductService {
    private final ProductDao productDao;


    @Override
    public void add(Product product) {
        productDao.save(product);
    }

    @Override
    public List<Product> getByCategoryId(int id) {
        return productDao.getByCategory_Id(id);
    }

    @Override
    public List<Product> getAll() {
        return productDao.findAll();
    }


}
