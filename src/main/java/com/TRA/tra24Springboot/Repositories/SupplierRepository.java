package com.TRA.tra24Springboot.Repositories;

import com.TRA.tra24Springboot.Models.Order;
import com.TRA.tra24Springboot.Models.PaymentType;
import com.TRA.tra24Springboot.Models.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SupplierRepository extends JpaRepository<Supplier,Integer> {

    @Query("SELECT s FROM Supplier s WHERE s.id = :supplierId")
    Supplier getSupplierById(@Param("supplierId") Integer supplierId);

    @Query("SELECT s FROM Supplier s WHERE s.companyName = :companyName")
    List<Supplier> getSupplierByCompanyName(@Param("companyName") String companyName);

    @Query("SELECT s FROM Supplier s WHERE s.country = :supplierCountry")
    List<Supplier> getSupplierByCountry(@Param("supplierCountry") String supplierCountry);

    @Query("SELECT s FROM Supplier s WHERE s.paymentMethods = :paymentMethods")
    List<Supplier> getSupplierByPaymentMethods(@Param("paymentMethods") PaymentType paymentMethods);

    @Query("SELECT s FROM Supplier s WHERE s.shippingMethods = :shippingMethods")
    List<Supplier> getSupplierByShippingMethods(@Param("shippingMethods") String shippingMethods);



}
