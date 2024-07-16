package com.TRA.tra24Springboot.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;
@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Invoice extends BaseEntity{

    double totalAmount;
    @OneToMany
    List<Product> products;
    double paidAmount;
    Date dueDate;
    Date paymentDate;


}
