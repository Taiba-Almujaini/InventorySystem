package com.TRA.tra24Springboot.Controllers;

import com.TRA.tra24Springboot.Models.*;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Date;
import java.util.UUID;

@RestController
@RequestMapping("/product")

public class OrderController {

   Order newOrder=new Order();
    @PostMapping("create")
    public Order createOrder(){
        Order order = new Order();

        Product product = new Product();

        ProductDetails productDetails = new ProductDetails();
        productDetails.setId(1);
        productDetails.setName("Apple");
        productDetails.setColor("Green");
        productDetails.setSize("Small");
        productDetails.setPrice(10d);
        productDetails.setCountryOfOrigin("USA");
        productDetails.setDescription("Apple Product");
        productDetails.setCreatedDate(new Date());

        product.setProductDetails(productDetails);
        product.setSku(UUID.randomUUID());
        product.setCategory("Electronics");
        product.setQuantity(1);
        product.setId(1);
        product.setIsActive(Boolean.TRUE);
        product.setCreatedDate(new Date());

        order.setId(111);
        order.setProductsOrdered(Arrays.asList(product));
        order.setCategoryName("Electronics");
        order.setCreatedDate(new Date());
        order.setOrderDate(new Date());
        order.setStatus(OrderStatus.COMPLETED);
        order.setPaymentStatus(PaymentStatus.PAID);
        order.setPaymentType(PaymentType.BANK_TRANSFER);
        order.setDueDate(new Date());

        newOrder = order;
        return order;
    }

    @PutMapping("updateOrder")
    public Order updateOrder(@RequestBody Order userOrder){
       userOrder.setOrderDate(new Date());

       return userOrder;
    }


    @PostMapping("/cancel/{orderId}")
    public String cancelOrder(@PathVariable("orderId") String orderId,Order order) {
        if (order != null && order.getStatus() == OrderStatus.IN_PROGRESS) {
            order.setStatus(OrderStatus.CANCELED);
            if (order.getPaymentStatus() == PaymentStatus.PAID) {
                order.setPaymentStatus(PaymentStatus.REJECTED);
            }
            return "Order canceled.";
        } else {
            return "Unable to cancel order.";
        }
    }



}
