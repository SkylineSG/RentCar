package com.service;

import com.domain.Cart;
import com.repository.CartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CartDbService {

    private final CartRepository repository;

    public List<Cart> getAllCarts() {
        return repository.findAll();
    }

    public Optional<Cart> getCart(final Long cartId) {
        return repository.findById(cartId);
    }

    public Cart saveCart(Cart cart) {
        return repository.save(cart);
    }

    public void deleteCart(final Long cartId) {
        repository.deleteById(cartId);
    }
}
