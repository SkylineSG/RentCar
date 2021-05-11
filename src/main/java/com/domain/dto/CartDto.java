package com.domain.dto;

import com.domain.Car;
import com.domain.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CartDto {
    private Long id;
    private User user ;
    private List<Car> car = new ArrayList<>();

//    public CartDto(Long id, User user, List<Product> products) {
//
//    }
}
