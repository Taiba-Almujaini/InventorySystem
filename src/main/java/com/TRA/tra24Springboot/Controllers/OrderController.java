package com.TRA.tra24Springboot.Controllers;

import com.TRA.tra24Springboot.DTO.OrderDTO;
import com.TRA.tra24Springboot.Models.*;
import com.TRA.tra24Springboot.Repositories.OrderRepository;
import com.TRA.tra24Springboot.Services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public <T> ResponseEntity<T> updateOrder(@RequestParam Integer id) {
        try {
            String result = orderService.updateOrder(id);
            return (ResponseEntity<T>) new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            return (ResponseEntity<T>) new ResponseEntity<>("Updating order failed! " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("getAll")
    public List<OrderDTO> getOrder(){
        return orderService.getOrders();

    }

    @GetMapping("getByOrderId")
    public <T> ResponseEntity<T> getOrderById(@RequestParam Integer id) {
        try {
            Order order = orderService.getOrdersById(id);
            return (ResponseEntity<T>) new ResponseEntity<>(order, HttpStatus.OK);
        } catch (Exception e) {
            return (ResponseEntity<T>) new ResponseEntity<>("Retrieving order failed! " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("getByCategoryName")
    public <T> ResponseEntity<T> getOrderByCategoryName(@RequestParam String categoryName) {
        try {
            List<Order> orders = orderService.getOrdersByCategoryName(categoryName);
            return (ResponseEntity<T>) new ResponseEntity<>(orders, HttpStatus.OK);
        } catch (Exception e) {
            return (ResponseEntity<T>) new ResponseEntity<>("Retrieving orders by category name failed! " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("getByOrderStatus")
    public <T> ResponseEntity<T> getOrderByOrderStatus(@RequestParam OrderStatus status) {
        try {
            List<Order> orders = orderService.getOrdersByOrderStatus(status);
            return (ResponseEntity<T>) new ResponseEntity<>(orders, HttpStatus.OK);
        } catch (Exception e) {
            return (ResponseEntity<T>) new ResponseEntity<>("Retrieving orders by order status failed! " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("getByPaymentStatus")
    public List<Order> getOrderByPaymentStatus(@RequestParam PaymentStatus status) {
        return orderService.getOrdersByPaymentStatus(status);

    }
    @GetMapping("getByPaymentType")
    public List<Order> getOrderByPaymentType(@RequestParam PaymentType type) {
        return orderService.getOrdersByPaymentType(type);

    }

}
