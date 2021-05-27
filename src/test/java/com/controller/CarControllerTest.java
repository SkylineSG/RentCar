//package com.controller;
//
//import com.domain.Car;
//import com.domain.dto.CarDto;
//import com.service.CarDbService;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.hamcrest.Matchers.containsString;
//import static org.hamcrest.Matchers.hasSize;
//import static org.junit.jupiter.api.Assertions.*;
//import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@WebMvcTest(CarController.class)
//class CarControllerTest {
//
//        @Autowired
//        private MockMvc mockMvc;
//
//        @Test
//        public void shouldReturnEmptyCarList() throws Exception {
//            this.mockMvc.perform(get("/v1/car/getCars")).andDo(print()).andExpect(status().isOk())
//                    .andExpect(content().));
//        }
//
//    @Test
//    void getCar() {
//    }
//
//    @Test
//    void createCar() {
//    }
//
//    @Test
//    void updateCar() {
//    }
//
//    @Test
//    void deleteCar() {
//    }
//}