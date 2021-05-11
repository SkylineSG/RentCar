package com.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.lang.Nullable;

import javax.persistence.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "CARS")
public class Car {
    @Id
    @GeneratedValue
    @Column(nullable = false, name = "id")
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

    public Car(@Nullable Long id, @Nullable String carName, String carDescription, @Nullable Double price) {
        this.id = id;
        this.carName = carName;
        this.carDescription = carDescription;
        this.price = price;
    }
}