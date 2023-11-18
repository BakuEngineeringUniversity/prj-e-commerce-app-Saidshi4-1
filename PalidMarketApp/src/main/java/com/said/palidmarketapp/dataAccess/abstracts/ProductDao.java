package com.said.palidmarketapp.dataAccess.abstracts;

import com.said.palidmarketapp.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductDao extends JpaRepository <Product, Integer> {
    List <Product> getByCategory_Id(int id);
}
