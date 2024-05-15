package com.TRA.tra24Springboot.Services;

import com.TRA.tra24Springboot.DTO.InventoryDTO;
import com.TRA.tra24Springboot.DTO.ProductDTO;
import com.TRA.tra24Springboot.Models.Inventory;
import com.TRA.tra24Springboot.Models.Product;
import com.TRA.tra24Springboot.Models.ProductDetails;
import com.TRA.tra24Springboot.Repositories.InventoryRepository;
import com.TRA.tra24Springboot.Repositories.ProductDetailsRepository;
import com.TRA.tra24Springboot.Repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class InventoryService {
    @Autowired
    InventoryRepository inventoryRepository;
    @Autowired
    ProductDetailsRepository productDetailsRepository;
    @Autowired
    ProductRepository productRepository;


    public Inventory receiveStock(Inventory inventory){

        ProductDetails productDetails = new ProductDetails();
        productDetails.setName("TV");
        productDetails.setColor("Black");
        productDetails.setPrice(350d);
        productDetails.setCountryOfOrigin("UK");
        productDetails.setCreatedDate(new Date());
       productDetails=productDetailsRepository.save(productDetails);
        Product product = new Product();
        product.setProductDetails(productDetails);
        product.setSku(UUID.randomUUID());
        product.setQuantity(100);
        product.setIsActive(Boolean.TRUE);
        product.setCreatedDate(new Date());
        product=productRepository.save(product);

        inventory.setProducts(Arrays.asList(product));
        inventory.setLocation("Muscat");
        inventory.setManager("Ahmed");
        inventory.setPhoneNumber("99999999");
        inventory.setSupplier("ABC");
        inventory.setOpeningHours("8 AM");
        inventory.setClosingHours("8 PM");
        inventory.setCreatedDate(new Date());
        inventory.setIsActive(Boolean.TRUE);
        return  inventoryRepository.save(inventory);
    }


//    public Inventory returns(Inventory inventory) {
//
//        inventory.setId(9);
//        inventory.setUpdatedDate(new Date());
//
//      return inventoryRepository.save(inventory);
//
//    }


    public String writeOff(Integer id){
        Inventory inventory=inventoryRepository.getById(id);
        inventory.setIsActive(Boolean.FALSE);
        inventory.setUpdatedDate(new Date());

       inventoryRepository.save(inventory);
       return  "success";
    }

    public List<InventoryDTO> getInventory(){
        List <Inventory> inventory = inventoryRepository.findAll();

        return InventoryDTO.convertToDTO(inventory);
    }
}
