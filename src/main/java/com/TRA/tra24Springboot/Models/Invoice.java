package com.TRA.tra24Springboot.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.util.Date;
import java.util.List;
@Data
@Entity
public class Invoice extends BaseEntity{

    double totalAmount;
    @OneToMany
    List<Product> products;
    double paidAmount;


}
