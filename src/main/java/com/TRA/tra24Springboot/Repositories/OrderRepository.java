package com.TRA.tra24Springboot.Repositories;

import com.TRA.tra24Springboot.Models.*;
import jdk.jshell.Snippet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order,Integer> {

    @Query("SELECT o FROM Order o WHERE o.id = :orderId")
    Order getOrderById(@Param("orderId") Integer orderId);

    @Query("SELECT o FROM Order o WHERE o.categoryName = :categoryName")
    List<Order> getOrderByCategoryName(@Param("categoryName") String categoryName);

    @Query("SELECT o FROM Order o WHERE o.status = :orderStatus")
    List<Order> getOrderByOrderStatus(@Param("orderStatus") OrderStatus orderStatus);

    @Query("SELECT o FROM Order o WHERE o.paymentStatus = :paymentStatus")
    List<Order> getOrderByPaymentStatus(@Param("paymentStatus") PaymentStatus paymentStatus);

    @Query("SELECT o FROM Order o WHERE o.paymentType = :paymentType")
    List<Order> getOrderByPaymentType(@Param("paymentType") PaymentType paymentType);




}
