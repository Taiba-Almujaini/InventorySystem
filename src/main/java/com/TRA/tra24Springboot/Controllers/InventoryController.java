package com.TRA.tra24Springboot.Controllers;

import com.TRA.tra24Springboot.Models.Inventory;
import com.TRA.tra24Springboot.Models.Product;
import com.TRA.tra24Springboot.Models.ProductDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Date;


@RestController
@RequestMapping("/product")
public class InventoryController {

    Inventory newInventory = new Inventory();


    @PostMapping("receive")
    public Inventory receiveNewStock(){
        Inventory receiveInventory = new Inventory();

        Product product = new Product();
        product.setId(1);
        product.setCreatedDate(new Date());

        ProductDetails productDetails = new ProductDetails();
        productDetails.setId(1);
        productDetails.setCreatedDate(new Date());

        product.setProductDetails(productDetails);

        receiveInventory.setProducts(Arrays.asList(product));

        receiveInventory.setLocation("Muscat");
        receiveInventory.setManager("ALi");
        receiveInventory.setPhoneNumber("99999999");
        receiveInventory.setSupplier("Toyota");
        receiveInventory.setOpeningHours("8 AM");
        receiveInventory.setClosingHours("8 PM");

       newInventory=receiveInventory;
        return receiveInventory;
    }

    @GetMapping("return")
    public  Inventory returnStock(){
        return  newInventory;
    }

 

}
