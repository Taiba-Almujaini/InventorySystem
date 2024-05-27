package com.TRA.tra24Springboot.Controllers;

import com.TRA.tra24Springboot.DTO.OrderDTO;
import com.TRA.tra24Springboot.Models.*;
import com.TRA.tra24Springboot.Repositories.OrderRepository;
import com.TRA.tra24Springboot.Services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public String updateOrder(@RequestParam Integer id){
        return  orderService.updateOrder(id);
    }


    @GetMapping("getAll")
    public List<OrderDTO> getOrder(){
        return orderService.getOrders();

    }

    @GetMapping("getByOrderId")
    public Order getOrderById(@RequestParam Integer id) {
        return orderService.getOrdersById(id);

    }
    @GetMapping("getByCategoryName")
    public List<Order> getOrderByCategoryName(@RequestParam String categoryName) {
        return orderService.getOrdersCategoryName(categoryName);

    }
    @GetMapping("getByOrderStatus")
    public List<Order> getOrderByOrderStatus(@RequestParam OrderStatus status) {
        return orderService.getOrdersByOrderStatus(status);

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
