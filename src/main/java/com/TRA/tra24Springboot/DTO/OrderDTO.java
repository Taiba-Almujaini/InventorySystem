package com.TRA.tra24Springboot.DTO;

import com.TRA.tra24Springboot.Models.Order;
import com.TRA.tra24Springboot.Models.OrderStatus;
import com.TRA.tra24Springboot.Models.PaymentStatus;
import com.TRA.tra24Springboot.Models.ProductDetails;
import jdk.jshell.Snippet;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class OrderDTO {

    Integer orderID;
    OrderStatus status;
    PaymentStatus PaymentStatus;

    List<ProductDTO> productsOrdered;
    public static OrderDTO convertToDTO(Order order) {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setOrderID(order.getId());
        orderDTO.setPaymentStatus(order.getPaymentStatus());
        orderDTO.setStatus(order.getStatus());
        orderDTO.setProductsOrdered(ProductDTO.convertToDTO(order.getProductsOrdered()));

        return orderDTO;
    }

    public static List<OrderDTO> convertToDTO(List<Order> orderList) {
      List<OrderDTO> orderDTO = new ArrayList<>();

        for (Order orderDetailsDB : orderList) {
            OrderDTO dto = convertToDTO(orderDetailsDB);
            orderDTO.add(dto);
         }

        return orderDTO;
   }

}
