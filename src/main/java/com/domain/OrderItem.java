package com.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "orderItems")
public class OrderItem {

    @Id
    @GeneratedValue
    @Column(nullable = false,name = "id", unique = true)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "carId")
    private Car car;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "orderId")
    private Order order;

    public OrderItem(Car car, Order order) {
        this.car = car;
        this.order = order;
    }
}