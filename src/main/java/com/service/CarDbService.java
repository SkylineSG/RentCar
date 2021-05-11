package com.service;

import com.controller.exceptions.CarConflictException;
import com.domain.Car;
import com.repository.CarRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CarDbService {
    private final CarRepository carRepository;

    public List<Car> getAllCars() {
        return carRepository.findAll();
    }

    public Optional<Car> getCarById(final Long carId) {
        return carRepository.findById(carId);
    }

    public Car saveCar(final Car car) throws CarConflictException {
        if (!carRepository.existsByCarName(car.getCarName())) {
            return carRepository.save(car);
        } else {
            throw new CarConflictException();
        }
    }

    public void deleteCar(final Long carId) {
        carRepository.deleteById(carId);
    }
}

