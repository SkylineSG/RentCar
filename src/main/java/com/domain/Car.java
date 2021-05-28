package com.domain;

import lombok.*;
import org.springframework.lang.Nullable;

import javax.persistence.*;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "CARS")
public class Car {
    @Id
    @GeneratedValue
    @Column(nullable = false, name = "Car_id")
    private Long id;

    @Column(nullable = false, name = "name")
    private String carName;

    @Column(name = "description")
    private String carDescription;

    @Column(name = "price")
    @Nullable
    private Double price;

    @ManyToMany(mappedBy = "cars")
    private List<Cart> cartList;

//    @ManyToOne
//    @JoinColumn(name = "groupId")
//    private Group group;

    @OneToMany(
            targetEntity = OrderItem.class,
            mappedBy = "car",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    private List<OrderItem> orderItems;

    @OneToMany(mappedBy = "car")
    private List<OrderItem>items;

    public Car(@Nullable String carName) {
        this.carName = carName;
    }

    public Car(@Nullable Long id, @Nullable String carName, String carDescription, @Nullable Double price) {
        this.id = id;
        this.carName = carName;
        this.carDescription = carDescription;
        this.price = price;
    }
}