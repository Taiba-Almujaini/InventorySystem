package com.TRA.tra24Springboot.Controllers;

import com.TRA.tra24Springboot.DTO.SupplierDTO;
import com.TRA.tra24Springboot.Models.*;
import com.TRA.tra24Springboot.Repositories.SupplierRepository;
import com.TRA.tra24Springboot.Services.SupplierService;
import com.TRA.tra24Springboot.logging.TrackExecutionTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/supplier")

public class SupplierController {

    @Autowired
    SupplierRepository supplierRepository;
    @Autowired
    SupplierService supplierService;

    Supplier newSupplier = new Supplier();

    @PostMapping("add")
    @TrackExecutionTime
    public Supplier addSupplier( Supplier supplier) {
        return  supplierService.addSupplier(supplier);

    }

    @PutMapping("updateSupplier")
    @TrackExecutionTime
    public <T> ResponseEntity<T> updateSupplier(@RequestParam Integer id) {
        try {
            String result = supplierService.updateSupplier(id);
            return (ResponseEntity<T>) new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            return (ResponseEntity<T>) new ResponseEntity<>("Updating supplier failed! " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("delete")
    @TrackExecutionTime
    public <T> ResponseEntity<T> delete(@RequestParam Integer id) throws Exception {
        try {
            String result = supplierService.deleteSupplier(id);
            return (ResponseEntity<T>) new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            return (ResponseEntity<T>) new ResponseEntity<>("Deleting failed! " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("getAll")
    @TrackExecutionTime
    public List<SupplierDTO> getSupplier(){
        return supplierService.getSupplier();

    }

    @GetMapping("getSupplierById")
    @TrackExecutionTime
    public <T> ResponseEntity<T> getSupplierById(@RequestParam Integer id) {
        try {
            Supplier supplier = supplierService.getSuppliersById(id);
            return (ResponseEntity<T>) new ResponseEntity<>(supplier, HttpStatus.OK);
        } catch (Exception e) {
            return (ResponseEntity<T>) new ResponseEntity<>("Retrieving supplier failed! " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("getByCompanyName")
    @TrackExecutionTime
    public <T> ResponseEntity<T> getSupplierByCompanyName(@RequestParam String companyName) {
        try {
            List<Supplier> suppliers = supplierService.getSuppliersByCompanyName(companyName);
            return (ResponseEntity<T>) new ResponseEntity<>(suppliers, HttpStatus.OK);
        } catch (Exception e) {
            return (ResponseEntity<T>) new ResponseEntity<>("Retrieving suppliers by company name failed! " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("getByCountry")
    @TrackExecutionTime
    public <T> ResponseEntity<T> getSupplierByCountry(@RequestParam String country) {
        try {
            List<Supplier> suppliers = supplierService.getSuppliersByCountry(country);
            return (ResponseEntity<T>) new ResponseEntity<>(suppliers, HttpStatus.OK);
        } catch (Exception e) {
            return (ResponseEntity<T>) new ResponseEntity<>("Retrieving suppliers by country failed! " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("getByPaymentMethods")
    @TrackExecutionTime
    public <T> ResponseEntity<T> getSupplierByPaymentMethods(@RequestParam PaymentType paymentMethods) {
        try {
            List<Supplier> suppliers = supplierService.getSuppliersByPaymentMethods(paymentMethods);
            return (ResponseEntity<T>) new ResponseEntity<>(suppliers, HttpStatus.OK);
        } catch (Exception e) {
            return (ResponseEntity<T>) new ResponseEntity<>("Retrieving suppliers by payment methods failed! " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("getByShippingMethods")
    @TrackExecutionTime
    public <T> ResponseEntity<T> getSupplierByShippingMethods(@RequestParam String shippingMethods) {
        try {
            List<Supplier> suppliers = supplierService.getSuppliersByShippingMethods(shippingMethods);
            return (ResponseEntity<T>) new ResponseEntity<>(suppliers, HttpStatus.OK);
        } catch (Exception e) {
            return (ResponseEntity<T>) new ResponseEntity<>("Retrieving suppliers by shipping methods failed! " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("getByMOQ")
    @TrackExecutionTime
    public <T> ResponseEntity<T> getSupplierByMinimumOrderQuantity(@RequestParam String minimumOrderQuantity) {
        try {
            List<Supplier> suppliers = supplierService.getSuppliersByMinimumOrderQuantity(minimumOrderQuantity);
            return (ResponseEntity<T>) new ResponseEntity<>(suppliers, HttpStatus.OK);
        } catch (Exception e) {
            return (ResponseEntity<T>) new ResponseEntity<>("Retrieving suppliers by minimum order quantity failed! " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}