package com.mapper;

import com.domain.Car;

import com.domain.dto.CarDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CarMapper {

    public Car mapToCar(CarDto carDto) {
        return new Car (carDto.getId(), carDto.getCarName(), carDto.getDescription(), carDto.getPrice());
    }

    public CarDto mapCarDto(Car car) {
        return new CarDto(car.getId(), car.getCarName(), car.getCarDescription(), car.getPrice());
    }

    public List<CarDto> carDtoList(final List<Car> carList) {
        return carList.stream().map(this::mapCarDto).collect(Collectors.toList());
    }

    public List<Car> carList(final List<CarDto> carDtoList) {
        return carDtoList.stream().map(this::mapToCar).collect(Collectors.toList());
    }
}

