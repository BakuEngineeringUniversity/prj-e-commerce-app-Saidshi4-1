package com.said.palidmarketapp.dataAccess.abstracts;

import com.said.palidmarketapp.entities.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartDao extends JpaRepository<Cart, Integer> {
    void deleteById(int id);
}
