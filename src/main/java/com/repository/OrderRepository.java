package com.repository;

import com.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    @Override
    Order save(Order order);

    @Override
    Optional<Order> findById(Long id);

    @Override
    List<Order> findAll();

    @Override
    void deleteById(Long id);
}
