package com.TRA.tra24Springboot.Controllers;

import com.TRA.tra24Springboot.DTO.OrderDTO;
import com.TRA.tra24Springboot.DTO.SupplierDTO;
import com.TRA.tra24Springboot.Models.ContactDetails;
import com.TRA.tra24Springboot.Models.Product;
import com.TRA.tra24Springboot.Models.Supplier;
import com.TRA.tra24Springboot.Repositories.SupplierRepository;
import com.TRA.tra24Springboot.Services.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/supplier")

public class SupplierController {

    @Autowired
    SupplierRepository supSipplierRepository;
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
    public String deleteSupplier(@RequestParam Integer id){

        return supplierService.deleteSupplier(id);
    }
    @GetMapping("getAll")
    public List<SupplierDTO> getSupplier(){
        return supplierService.getSupplier();

    }

    @GetMapping("getSupplierById")
    public Supplier getSupplierById(@RequestParam Integer id) {
        return supplierService.getSuppliersById(id);

    }

}