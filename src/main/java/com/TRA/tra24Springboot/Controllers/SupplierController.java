package com.TRA.tra24Springboot.Controllers;

import com.TRA.tra24Springboot.DTO.SupplierDTO;
import com.TRA.tra24Springboot.Models.*;
import com.TRA.tra24Springboot.Repositories.SupplierRepository;
import com.TRA.tra24Springboot.Services.SupplierService;
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
    public Supplier addSupplier( Supplier supplier) {
        return  supplierService.addSupplier(supplier);

    }

    @PutMapping("updateSupplier")
    public String updateSupplier(@RequestParam Integer id){
        return  supplierService.updateSupplier(id);
    }



    @PostMapping("delete")
    public <T> ResponseEntity<T> delete(@RequestParam Integer id) throws Exception {
        try {
            String result = supplierService.deleteSupplier(id);
            return (ResponseEntity<T>) new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            return (ResponseEntity<T>) new ResponseEntity<>("Deleting failed! " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("getAll")
    public List<SupplierDTO> getSupplier(){
        return supplierService.getSupplier();

    }

    @GetMapping("getSupplierById")
    public Supplier getSupplierById(@RequestParam Integer id) {
        return supplierService.getSuppliersById(id);

    }
    @GetMapping("getByCompanyName")
    public <T> ResponseEntity<T> getSupplierByCompanyName(@RequestParam String companyName) {
        try {
            List<Supplier> suppliers = supplierService.getSuppliersByCompanyName(companyName);
            return (ResponseEntity<T>) new ResponseEntity<>(suppliers, HttpStatus.OK);
        } catch (Exception e) {
            return (ResponseEntity<T>) new ResponseEntity<>("Retrieving suppliers by company name failed! " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("getByCountry")
    public <T> ResponseEntity<T> getSupplierByCountry(@RequestParam String country) {
        try {
            List<Supplier> suppliers = supplierService.getSuppliersByCountry(country);
            return (ResponseEntity<T>) new ResponseEntity<>(suppliers, HttpStatus.OK);
        } catch (Exception e) {
            return (ResponseEntity<T>) new ResponseEntity<>("Retrieving suppliers by country failed! " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("getByPaymentMethods")
    public <T> ResponseEntity<T> getSupplierByPaymentMethods(@RequestParam PaymentType paymentMethods) {
        try {
            List<Supplier> suppliers = supplierService.getSuppliersByPaymentMethods(paymentMethods);
            return (ResponseEntity<T>) new ResponseEntity<>(suppliers, HttpStatus.OK);
        } catch (Exception e) {
            return (ResponseEntity<T>) new ResponseEntity<>("Retrieving suppliers by payment methods failed! " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("getByShippingMethods")
    public List<Supplier> getSupplierByShippingMethods(@RequestParam String shippingMethods) {
        return supplierService.getSuppliersByShippingMethods(shippingMethods);

    }

    @GetMapping("getByMOQ")
    public List<Supplier> getSupplierByMinimumOrderQuantity(@RequestParam String minimumOrderQuantity) {
        return supplierService.getSuppliersByMinimumOrderQuantity(minimumOrderQuantity);

    }

}