package com.TRA.tra24Springboot.Controllers;

import com.TRA.tra24Springboot.Models.ContactDetails;
import com.TRA.tra24Springboot.Models.PaymentType;
import com.TRA.tra24Springboot.Models.Supplier;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/supplier")

public class SupplierController {

    Supplier newSupplier = new Supplier();

    @PostMapping("add")
    public Supplier addSupplier() {
        Supplier supplier = new Supplier();
        ContactDetails contactDetails = new ContactDetails();

        contactDetails.setAddress("Muscat");
        contactDetails.setEmail("bahwan@gmail.com");
        contactDetails.setPhoneNumber("99999999");
        contactDetails.setFaxNumber("22222");
        contactDetails.setPostalCode("11111");
        supplier.setContactDetails(contactDetails);


        supplier.setCompanyName("Bahwan");
        supplier.setCountry("Oman");
        supplier.setNextDeliveryTime(new Date());
        supplier.setComplaints("no complaints");
        supplier.setPaymentMethods(PaymentType.BANK_TRANSFER);
        supplier.setShippingMethods("Air Freight ");
        supplier.setMinimumOrderQuantity("100");


        newSupplier = supplier;
        return supplier;

    }

    @PutMapping("update")
    public Supplier updateSupplier(@RequestBody Supplier supplierUpdating) {

        ContactDetails pd = supplierUpdating.getContactDetails();
        pd.setUpdatedDate(new Date());

        supplierUpdating.setContactDetails(pd);
        supplierUpdating.setUpdatedDate(new Date());

        newSupplier = supplierUpdating;
        return supplierUpdating;
    }


    @PostMapping("delete/{id}")
    public String deleteSupplier(@PathVariable Integer id){

        if(newSupplier.getId().equals(id)){
            newSupplier.setIsActive(Boolean.FALSE);
            System.out.println(newSupplier.toString());

        }
        return "Success!";
    }


}
