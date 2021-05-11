package com.repository;

import com.domain.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {
    @Override
    List<Car> findAll();

    @Override
    Optional<Car> findById(Long carId);

    @Override
    Car save(Car car);

    @Override
    void deleteById(Long cartId);

    boolean existsByCarName(String carName);
}
