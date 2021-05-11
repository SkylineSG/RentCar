package com.controller;

import com.controller.exceptions.CarNotFoundException;
import com.domain.*;
import com.controller.exceptions.CartNotFoundException;
import com.controller.exceptions.UserNotFoundException;
import com.domain.dto.CarDto;
import com.mapper.CarMapper;
import com.mapper.CartMapper;
import com.mapper.OrderMapper;
import com.service.CarDbService;
import com.service.CartDbService;
import com.service.OrderItemDbService;
import com.service.UserDbService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping("/v1/cart")
public class CartController {
    private CartMapper cartMapper;
    private CartDbService cartDbService;
    private CarDbService carDbService;
    private CarMapper carMapper;
    private OrderController orderController;
    private OrderMapper orderMapper;
    private OrderItemDbService orderItemDbService;
    private UserDbService userDbService;

    @PostMapping(value = "createNewCart")
    public void createNewCart() {
        cartDbService.saveCart(new Cart());
    }

    @GetMapping(value = "getCars")
    public List<CarDto> getCars(@RequestParam Long cartId) {
        return carMapper.carDtoList(cartDbService.getCart(cartId).get().getCars());
    }

    @PutMapping(value = "addToCart")
    public void addCarToCart(@RequestParam Long cartId, @RequestParam Long carId) throws CartNotFoundException {
        Cart cart = cartDbService.getCart(cartId).orElseThrow(CartNotFoundException::new);
        Car car = carDbService.getCarById(carId).get();
        cart.getCars().add(car);
        cartDbService.saveCart(cart);
    }

    @PostMapping(value = "createOrder")
    public void createOrder(@RequestParam Long userId, @RequestParam Long cartId) throws Exception {
        Order theOrder = new Order();
        Cart cart = cartDbService.getCart(cartId).orElseThrow(CartNotFoundException::new);
        User user = userDbService.getUser(userId).orElseThrow(UserNotFoundException::new);

        List<OrderItem> orderItemList = cart.getCars().stream()
                .map(car -> new OrderItem(car,theOrder)).collect(toList());
        theOrder.setOrderItems(orderItemList);
        theOrder.setUser(user);
        orderController.createOrder(orderMapper.mapToOrderDto(theOrder));
        orderItemList.forEach(orderItem -> {orderItemDbService.saveOrderItems(orderItem);});
    }

    @DeleteMapping(value = "deleteCar")
    public void deleteCar(@RequestParam Long cartId, @RequestParam Long carId) throws Exception{
        Car car = carDbService.getCarById(carId).orElseThrow(CarNotFoundException::new);
        cartDbService.getCart(cartId).orElseThrow(CartNotFoundException::new).getCars().remove(car);
    }
}