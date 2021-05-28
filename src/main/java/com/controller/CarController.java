package com.controller;

import com.controller.exceptions.CarConflictException;
import com.controller.exceptions.CarNotFoundException;
import com.domain.dto.CarDto;
import com.mapper.CarMapper;
import com.mapper.UserMapper;
import com.service.CarDbService;
import com.service.UserDbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/car")
public class CarController {

   private final CarDbService carDbService;
   private final CarMapper carMapper;

    @Autowired
    public CarController(CarMapper carMapper, CarDbService carDbService) {
        this.carMapper = carMapper;
        this.carDbService = carDbService;
    }

    @GetMapping(value = "getCars")
    public List<CarDto> getCars() {
        return carMapper.carDtoList(carDbService.getAllCars());
    }

    @GetMapping(value = "getCar")
    public CarDto getCar(@RequestParam Long carId) throws CarNotFoundException {
        return carMapper.mapCarDto(carDbService.getCarById(carId).orElseThrow(CarNotFoundException::new));
    }

    @PostMapping(value = "createCar")
    public void createCar(@RequestBody CarDto carDto) throws CarConflictException {
        carDbService.saveCar(carMapper.mapToCar(carDto));

    }

    @PutMapping(value = "updateCar")
    public CarDto updateCar(@RequestBody CarDto carDto) throws CarConflictException {
        return carMapper.mapCarDto(carDbService.saveCar(carMapper.mapToCar(carDto)));
    }

    @DeleteMapping(value = "deleteCar")
    public void deleteCar(@RequestParam Long carId) {
        carDbService.deleteCar(carId);

    }
}
