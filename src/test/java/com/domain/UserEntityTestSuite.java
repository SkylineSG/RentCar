package com.domain;

import com.RentalApplication;
import com.repository.CartRepository;
import com.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest(classes = RentalApplication.class)
public class UserEntityTestSuite {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CartRepository cartRepository;

    @Test
    public void testUserEntityConnections() {
        //Given

        Car car1 = new Car(1L,
                "name of first car",
                "description of first car",
                100.0);

        Car car2 = new Car(2L,
                "name of second car",
                "description of second car",
                200.0);

        List<Car> carList = new ArrayList<>();
        carList.add(car1);
        carList.add(car2);

        User user = new User("Wojtek");
        user.setUserKey(12345L);
        user.setAddress("Somewhere in the world");
        user.setEmail("SomeMail@mail");
        user.setPhoneNumber("118913");
        userRepository.save(user);

        Cart cart = new Cart(1L,user, carList);
        cartRepository.save(cart);

        List<Cart> cartList = new ArrayList<>();
        cartList.add(cart);

        user.setCarts(cartList);

        User resultUser = userRepository.findAll().get(0);

        Assertions.assertEquals(resultUser.getCarts().get(0).getCars().get(0).getCarName(),"name of first car");
        Assertions.assertEquals(resultUser.getUserName(),"Wojtek");
        Assertions.assertEquals(resultUser.getCarts().size(),1);
        Assertions.assertEquals(resultUser.getCarts().get(0).getCars().size(),2);
        Assertions.assertEquals(resultUser.getUserKey(),12345L,0);
    }

    @Test
    public void testFindById() {

        //Given
        User user = new User("Wojtek");
        user.setUserKey(12345L);
        user.setAddress("Somewhere in the world");
        user.setEmail("SomeMail@mail");
        user.setPhoneNumber("118913");
        userRepository.save(user);

        Cart cart = new Cart(1L,user, new ArrayList<>());
        cartRepository.save(cart);

        List<Cart> cartList = new ArrayList<>();
        cartList.add(cart);

        user.setCarts(cartList);

        //When
        long id = userRepository.findAll().get(0).getId();

        //Then
        Assertions.assertTrue(userRepository.findById(id).isPresent());
    }

    @Test
    public void testDeleteById() {

        //Given
        User user = new User("Wojtek");
        user.setUserKey(12345L);
        user.setAddress("Somewhere in the world");
        user.setEmail("SomeMail@mail");
        user.setPhoneNumber("118913");
        userRepository.save(user);

        Cart cart = new Cart(1L,user, new ArrayList<>());
        cartRepository.save(cart);

        List<Cart> cartList = new ArrayList<>();
        cartList.add(cart);

        user.setCarts(cartList);

        //When
        long id = userRepository.findAll().get(0).getId();
        userRepository.deleteById(id);

        //Then
        Assertions.assertFalse(userRepository.findById(id).isPresent());
    }
}
