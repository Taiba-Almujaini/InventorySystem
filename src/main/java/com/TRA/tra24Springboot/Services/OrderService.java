package com.TRA.tra24Springboot.Services;


import com.TRA.tra24Springboot.DTO.OrderDTO;
import com.TRA.tra24Springboot.Models.*;
import com.TRA.tra24Springboot.Repositories.OrderRepository;
import com.TRA.tra24Springboot.Repositories.ProductDetailsRepository;
import com.TRA.tra24Springboot.Repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class OrderService {
    @Autowired
    OrderRepository orderRepository;

    @Autowired
    ProductDetailsRepository productDetailsRepository;

    @Autowired
    ProductRepository productRepository;

    public Order createOrder(Order order) {
        Product product = new Product();
        ProductDetails productDetails = new ProductDetails();
        productDetails.setName("Apple");
        productDetails.setColor("Green");
        productDetails.setSize("Small");
        productDetails.setPrice(10d);
        productDetails.setCountryOfOrigin("USA");
        productDetails.setDescription("Apple Product");
        productDetails.setCreatedDate(new Date());
        productDetails = productDetailsRepository.save(productDetails);
        product.setProductDetails(productDetails);
        product.setSku(UUID.randomUUID());
        product.setCategory("Electronics");
        product.setQuantity(1);
        product.setIsActive(Boolean.TRUE);
        product.setCreatedDate(new Date());

        product = productRepository.save(product);
        order.setProductsOrdered(Arrays.asList(product));
        order.setCategoryName("Electronics");
        order.setCreatedDate(new Date());
        order.setOrderDate(new Date());
        order.setIsActive(Boolean.TRUE);
        order.setStatus(OrderStatus.IN_PROGRESS);
        order.setPaymentStatus(PaymentStatus.PAID);
        order.setPaymentType(PaymentType.BANK_TRANSFER);
        order.setDueDate(new Date());

        return orderRepository.save(order);
    }


    public String cancelOrder(Integer id) {
        Order order = orderRepository.getById(id);
        if (order != null && order.getStatus() == OrderStatus.IN_PROGRESS) {
            order.setStatus(OrderStatus.CANCELED);
            if (order.getPaymentStatus() == PaymentStatus.PAID) {
                order.setPaymentStatus(PaymentStatus.REJECTED);
            }
            orderRepository.save(order);
            return "Order canceled.";
        } else {
            return "Unable to cancel order.";
        }
    }

    public String updateOrder(Integer id) throws Exception {
        Order order = orderRepository.findById(id).orElse(null);
        if (order == null) {
            throw new Exception("Order not found with ID: " + id);
        }

        orderRepository.save(order);
        return "Success";
    }
    public List<OrderDTO> getOrders() {
        List<Order> orders = orderRepository.findAll();

        return OrderDTO.convertToDTO(orders);
    }


    public Order getOrdersById(Integer orderId) {
        return orderRepository.getOrderById(orderId);
    }
    public List<Order> getOrdersCategoryName(String categoryName) {
        return orderRepository.getOrderByCategoryName(categoryName);
    }
    public List<Order> getOrdersByOrderStatus(OrderStatus orderStatus) {
        return orderRepository.getOrderByOrderStatus(orderStatus);
    }

    public List<Order> getOrdersByPaymentStatus(PaymentStatus paymentStatus) {
        return orderRepository.getOrderByPaymentStatus(paymentStatus);
    }

    public List<Order> getOrdersByPaymentType(PaymentType paymentType) {
        return orderRepository.getOrderByPaymentType(paymentType);
    }

}