package com.domain;

import com.RentalApplication;
import com.repository.CarRepository;
import com.repository.CartRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest(classes = RentalApplication.class)
public class CartEntityTestSuite {

    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private CarRepository carRepository;

    @Test
    public void testCartEntityConnections() {

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

        User user = new User("Wojtek");
        user.setUserKey(12345L);
        user.setAddress("Somewhere in the world");
        user.setEmail("SomeMail@mail");
        user.setPhoneNumber("118913");

        Cart cart = new Cart(1L, user, carList);
        cartRepository.save(cart);

        //Then
        Cart resultCart = cartRepository.findAll().get(0);
        Car resultFirstCar = resultCart.getCars().get(0);
        Car resultSecondCar = resultCart.getCars().get(1);

        Assertions.assertEquals("Wojtek",resultCart.getUser().getUserName());
        Assertions.assertEquals(2,resultCart.getCars().size());

        Assertions.assertEquals("name of first car",resultFirstCar.getCarName());
        Assertions.assertEquals("description of second car",resultSecondCar.getCarDescription());
        Assertions.assertEquals(300.0, resultFirstCar.getPrice()+resultSecondCar.getPrice(),0);
    }

    @Test
    public void testFindById() {

        //Given
        User user = new User("Wojtek");
        user.setUserKey(12345L);
        user.setAddress("Somewhere in the world");
        user.setEmail("SomeMail@mail");
        user.setPhoneNumber("118913");
        Cart cart = new Cart(1L,user, new ArrayList<>());

        //When
        cartRepository.save(cart);
        long id = cartRepository.findAll().get(0).getId();

        //Then
        Assertions.assertTrue(cartRepository.findById(id).isPresent());
    }

    @Test
    public void testDeleteById() {

        //Given
        User user = new User("Wojtek");
        user.setUserKey(12345L);
        user.setAddress("Somewhere in the world");
        user.setEmail("SomeMail@mail");
        user.setPhoneNumber("118913");
        Cart cart = new Cart(1L,user, new ArrayList<>());

        //When
        cartRepository.save(cart);
        long id = cartRepository.findAll().get(0).getId();
        cartRepository.deleteById(id);

        //Then
        Assertions.assertFalse(cartRepository.findById(id).isPresent());
    }

}
