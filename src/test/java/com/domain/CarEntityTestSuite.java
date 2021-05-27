package com.domain;

import com.RentalApplication;
import com.repository.CarRepository;
import com.repository.CartRepository;
import com.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = RentalApplication.class)
public class CarEntityTestSuite {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private CartRepository cartRepository;

    private static final String CAR_NAME = "Bmw";
    private static final int CART_SIZE = 1;
    private static final Long USER_KEY = 1L;

    private Car createCar() {
        Car car = new Car(CAR_NAME);
        car.setCarDescription("Suv");
        car.setPrice(250.0);
        this.carRepository.save(car);
        return car;
    }

    private User createUser() {
        User user = new User("Wojtek");
        user.setUserKey(12345L);
        user.setAddress("Somewhere in the world");
        user.setEmail("SomeMail@mail");
        user.setPhoneNumber("118913");
        userRepository.save(user);
        return user;
    }



    @Test
    void testFindById() {
        //Given
        Car car = new Car(CAR_NAME);
        car.setCarDescription("Suv");
        car.setPrice(250.0);
        this.carRepository.save(car);

        Cart cart = new Cart(1L,createUser(), new ArrayList<>());
        cartRepository.save(cart);

        List<Cart> cartList = new ArrayList<>();
        cartList.add(cart);

        car.setCartList(cartList);

        //When
        long id = carRepository.findAll().get(0).getId();
        Car resultCar = carRepository.findAll().get(2);

        //Then
        assertTrue(carRepository.findById(id).isPresent());
        assertEquals(resultCar.getCarName(),"Bmw");
    }

    @Test
    void testDeleteById() {
        //Given
        Car car = new Car(CAR_NAME);
        car.setCarDescription("Suv");
        car.setPrice(250.0);
        carRepository.save(car);

        Cart cart = new Cart(1L,createUser(), new ArrayList<>());
        cartRepository.save(cart);

        List<Cart> cartList = new ArrayList<>();
        cartList.add(cart);

        car.setCartList(cartList);

        //When
        long id = carRepository.findAll().get(0).getId();
        carRepository.deleteById(id);
        //Then
        assertFalse(carRepository.findById(id).isPresent());
    }
}

