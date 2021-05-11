package com.service;

import com.domain.OrderItem;
import com.repository.OrderItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderItemDbService {

    private final OrderItemRepository repository;

    public List<OrderItem> getAllOrderItems() {
        return repository.findAll();
    }

    public Optional<OrderItem> getOrderItems(final Long orderItemId) {
        return repository.findById(orderItemId);
    }

    public OrderItem saveOrderItems(OrderItem orderItem) {
        return repository.save(orderItem);
    }

    public void deleteOrderItem(final Long orderItemId) { repository.deleteById(orderItemId);
    }
}
