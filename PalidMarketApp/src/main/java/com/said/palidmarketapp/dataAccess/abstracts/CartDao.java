package com.said.palidmarketapp.dataAccess.abstracts;

import com.said.palidmarketapp.entities.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CartDao extends JpaRepository<Cart, Integer> {
    @Query("select c from Cart c where c.user.id=:id")
    List<Cart> findCartByUserId(Integer id);
}
