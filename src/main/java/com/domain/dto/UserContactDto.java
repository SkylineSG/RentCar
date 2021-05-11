package com.domain.dto;

import com.domain.Order;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Getter
public class UserContactDto {
    private Order order;
    private String email;
    private String phoneNumber;
    private String address;
}
