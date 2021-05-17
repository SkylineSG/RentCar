package com.domain;

import com.RentalApplication;
import com.repository.CartRepository;
import com.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import static org.hamcrest.MatcherAssert.assertThat;

import java.util.ArrayList;
import java.util.List;


@SpringBootTest(classes = RentalApplication.class)
class UserEntityTestSuite {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CartRepository cartRepository;

    private static final String USER_NAME = "Piotr";
    private static final int CART_SIZE = 1;
    private static final Long USER_KEY = 1L;

    private Cart createCart(List<Car> carList, User user) {
        Cart cart = new Cart(1L, user, carList);
        this.cartRepository.save(cart);
        return cart;
    }

    private User createTestUser() {
        User user = new User(USER_NAME);
        user.setUserKey(12345L);
        user.setAddress("Somewhere in the world");
        user.setEmail("SomeMail@mail");
        user.setPhoneNumber("118913");
        this.userRepository.save(user);
        return user;
    }

    private List<Car> createTestCarList() {
        return List.of(
                Car.builder().id(1L).carName("name of first car").carDescription("description of first car").price(100.0).build(),
                Car.builder().id(2L).carName("name of second car").carDescription("description of second car").price(200.0).build()
        );
    }

    @Test
   private void testUserEntityAndCartRelation() {
// given list of cars
        List<Car> carList = createTestCarList();
// and user
        User user = createTestUser();
// and cart with user and cars
        Cart cart = createCart(carList, user);
        user.setCarts(List.of(cart));
// when:
        User resultUser = this.userRepository.findAll().get(0);
// then:
        assertThat(resultUser.getCarts().get(0).getCars(), equalTo(carList));
        assertThat(resultUser.getUserName(), is(USER_NAME));
        assertThat(resultUser.getCarts().size(), is(CART_SIZE));
        assertThat(resultUser.getUserKey(), is(USER_KEY));
    }








    @Test
    private void testUserEntityConnections() {
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
    private void testFindById() {

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
    private void testDeleteById() {

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
