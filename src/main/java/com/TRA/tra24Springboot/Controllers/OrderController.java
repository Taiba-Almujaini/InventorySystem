package com.TRA.tra24Springboot.Controllers;

import com.TRA.tra24Springboot.Models.*;
import com.TRA.tra24Springboot.Repositories.OrderRepository;
import com.TRA.tra24Springboot.Services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Date;
import java.util.UUID;

@RestController
@RequestMapping("/order")

public class OrderController {

    @Autowired
    OrderRepository orderRepository;
    @Autowired
    OrderService orderService;
    @PostMapping("create")
    public Order createOrder(Order order){
     return  orderService.createOrder(order);
    }


    @PostMapping("cancel")
    public String cancelOrder(@RequestParam Integer id ){

        return orderService.cancelOrder(id);
    }

    @PutMapping("updateOrder")
    public String updateOrder(@RequestParam Integer id){
        return  orderService.updateOrder(id);
    }




}
