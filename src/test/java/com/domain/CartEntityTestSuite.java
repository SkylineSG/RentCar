package com.domain;

import com.RentalApplication;
import com.repository.CarRepository;
import com.repository.CartRepository;
import com.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = RentalApplication.class)
 class CartEntityTestSuite {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private CarRepository carRepository;

    private static final String USER_NAME = "Piotr";

    private User createUser() {
        User user = new User(USER_NAME);
        user.setUserKey(12345L);
        user.setAddress("Somewhere in the world");
        user.setEmail("SomeMail@mail");
        user.setPhoneNumber("118913");
        this.userRepository.save(user);
        return user;
    }
    @Test
     void testCartEntityConnections() {

        //Given
        Car car1 = new Car(1L,
                "name of first car",
                "description of first car",
                100.0);

        Car car2 = new Car(2L,
                "name of second car",
                "description of second car",
                200.0);

        carRepository.save(car1);
        carRepository.save(car2);

        //When
        List<Car> carList = new ArrayList<>();
        carList.add(car1);
        carList.add(car2);

        Cart cart = new Cart(1L, createUser(), carList);
        cartRepository.save(cart);

        //When
       long id = userRepository.findAll().get(0).getId(); ///

        //Then
        Cart resultCart = cartRepository.findAll().get(0);
        Car resultFirstCar = resultCart.getCars().get(0);
        Car resultSecondCar = resultCart.getCars().get(1);

        assertEquals("Piotr",resultCart.getUser().getUserName());
       // Assertions.assertEquals(2,resultCart.getCars().size());

      //  Assertions.assertEquals("name of first car",resultFirstCar.getCarName());
      //  Assertions.assertEquals("description of second car",resultSecondCar.getCarDescription());
      //  Assertions.assertEquals(300.0, resultFirstCar.getPrice()+resultSecondCar.getPrice(),0);
    }

    @Test
     void testFindById() {
        Cart cart = new Cart(1L,createUser(), new ArrayList<>());
        cartRepository.save(cart);

        List<Cart> cartList = new ArrayList<>();
        cartList.add(cart);
        createUser().setCarts(cartList);
        //When
        long id = cartRepository.findAll().get(0).getId();
        //Then
        assertTrue(cartRepository.findById(id).isPresent());
    }

    @Test
     void testDeleteById() {
        //Given
        Cart cart = new Cart(1L,createUser(), new ArrayList<>());
        cartRepository.save(cart);
        //When
        long id = cartRepository.findAll().get(0).getId();
        cartRepository.deleteById(id);
        //Then
        assertFalse(cartRepository.findById(id).isPresent());
    }

}
