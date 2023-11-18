package com.said.palidmarketapp.business.concretes;

import com.said.palidmarketapp.business.abstracts.CartService;
import com.said.palidmarketapp.dataAccess.abstracts.CartDao;
import com.said.palidmarketapp.entities.Cart;
import com.said.palidmarketapp.entities.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CartManager implements CartService {
    private final CartDao cartDao;
    @Override
    public void add(Cart cart) {
        cartDao.save(cart);
    }

    @Override
    public void delete(int id) {
        cartDao.deleteById(id);
    }

    @Override
    public List<Cart> getAll() {
        return cartDao.findAll();
    }

}
