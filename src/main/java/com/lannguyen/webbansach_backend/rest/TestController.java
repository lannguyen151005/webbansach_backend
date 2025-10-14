package com.lannguyen.webbansach_backend.rest;

import com.lannguyen.webbansach_backend.dao.OrderDetailRepository;
import com.lannguyen.webbansach_backend.entity.OrderDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class TestController {

    @Autowired
    OrderDetailRepository orderDetailRepository;

    @GetMapping("/")
    public Optional<OrderDetail> test(){
        return orderDetailRepository.findById(1L);
    }
}
