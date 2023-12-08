package com.said.palidmarketapp.dataAccess.abstracts;

import com.said.palidmarketapp.entities.Cart;
import com.said.palidmarketapp.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CartDao extends JpaRepository<Cart, Integer> {
    @Query("select c from Cart c where c.user.id=:userId")
    List<Cart> findCartByUserId(Integer userId);
}
