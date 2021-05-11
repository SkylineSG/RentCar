package com.service;

import com.domain.Order;
import com.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderDbService {


    private final OrderRepository repository;

    public List<Order> getAllOrders() {
        return repository.findAll();
    }

    public Optional<Order> getOrder(final Long orderId) {
        return repository.findById(orderId);
    }

    public void deleteOrder(final Long orderId) {
        repository.deleteById(orderId);
    }

    public Order saveOrder(Order order) {
        return repository.save(order);
    }
}