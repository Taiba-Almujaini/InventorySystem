package com.TRA.tra24Springboot.Repositories;

import com.TRA.tra24Springboot.Models.*;
import com.TRA.tra24Springboot.Utils.DateHelperUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import static org.assertj.core.api.Assertions.assertThat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ActiveProfiles("test")
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)

class OrderRepositoryTest {
    @Autowired
    private OrderRepository orderRepository;
    private UUID sku;
    private Date date= new Date();
    @BeforeEach
    void setUp() {
        Order order = Order.builder()
                .categoryName("Electronics")
                .paymentType(PaymentType.BANK_TRANSFER)
                .status(OrderStatus.COMPLETED)
                .paymentStatus(PaymentStatus.PAID)
                .orderDate(new Date())
                .paymentType(PaymentType.PAY_ORDER)
                .dueDate(DateHelperUtils.addDays(date, 5))
                .build();
        orderRepository.save(order);

    }
    @Test
    void getOrderByCategoryName() {
        List<Order> orderCategory = orderRepository.getOrderByCategoryName("Electronics");
        assertThat(orderCategory).isNotNull();
        assertThat(orderCategory.size()).isEqualTo(1);
        assertThat(orderCategory.get(0).getCategoryName()).isEqualTo("Electronics");

    }

    @Test
    void getOrderByOrderStatus() {
        List<Order> orderStatus=orderRepository.getOrderByOrderStatus(OrderStatus.COMPLETED);
        assertThat(orderStatus).isNotNull();
        assertThat(orderStatus.size()).isEqualTo(1);
        assertThat(orderStatus.get(0).getStatus()).isEqualTo(OrderStatus.COMPLETED);
    }

    @Test
    void getOrderByPaymentStatus() {
        List<Order> orderPayment=orderRepository.getOrderByPaymentStatus(PaymentStatus.PAID);
        assertThat(orderPayment).isNotNull();
        assertThat(orderPayment.size()).isEqualTo(1);
        assertThat(orderPayment.get(0).getPaymentStatus()).isEqualTo(PaymentStatus.PAID);
    }

    @Test
    void getOrderByPaymentType() {
        List<Order>orderType = orderRepository.getOrderByPaymentType(PaymentType.PAY_ORDER);
        assertThat(orderType).isNotNull();
        assertThat(orderType.size()).isEqualTo(1);
        assertThat(orderType.get(0).getPaymentType()).isEqualTo(PaymentType.PAY_ORDER);
    }
}