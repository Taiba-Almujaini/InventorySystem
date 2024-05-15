package com.TRA.tra24Springboot.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.util.List;

@Data
@Entity

public class Inventory extends BaseEntity {

    @OneToMany
    List<Product> products;
    String location;
    String manager;
    @OneToMany
    List<Employee> workers;
    String supplier;
    String phoneNumber;
    String openingHours;
    String closingHours;



}
