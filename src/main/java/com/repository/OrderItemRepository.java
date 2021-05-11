package com.repository;

import com.domain.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

    OrderItem save(OrderItem orderItem);

    @Override
    List<OrderItem> findAll();

    @Override
    Optional<OrderItem> findById(Long id);

    @Override
    void deleteById(Long id);
}
