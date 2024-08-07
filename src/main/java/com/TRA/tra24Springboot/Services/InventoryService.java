package com.TRA.tra24Springboot.Services;

import com.TRA.tra24Springboot.DTO.InventoryDTO;
import com.TRA.tra24Springboot.DTO.ProductDTO;
import com.TRA.tra24Springboot.Models.Inventory;
import com.TRA.tra24Springboot.Models.Order;
import com.TRA.tra24Springboot.Models.Product;
import com.TRA.tra24Springboot.Models.ProductDetails;
import com.TRA.tra24Springboot.Repositories.InventoryRepository;
import com.TRA.tra24Springboot.Repositories.ProductDetailsRepository;
import com.TRA.tra24Springboot.Repositories.ProductRepository;
import com.TRA.tra24Springboot.logging.TrackExecutionTime;
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

    @TrackExecutionTime
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

    @TrackExecutionTime
    public String writeOff(Integer id){
        Inventory inventory=inventoryRepository.getById(id);
        inventory.setIsActive(Boolean.FALSE);
        inventory.setUpdatedDate(new Date());

       inventoryRepository.save(inventory);
       return  "success";
    }
    @TrackExecutionTime
    public List<InventoryDTO> getInventory(){
        List <Inventory> inventory = inventoryRepository.findAll();

        return InventoryDTO.convertToDTO(inventory);
    }
    @TrackExecutionTime
    public Inventory getInventoriesById(Integer inventoryId)
    {
        return inventoryRepository.getInventoryById(inventoryId);
    }
    @TrackExecutionTime
    public List<Inventory> getInventoriesByLocation(String inventoryLocation) {
        return inventoryRepository.getInventoryByLocation(inventoryLocation);
    }
    @TrackExecutionTime
    public List<Inventory> getInventoriesByManager(String InventoryManger) {
        return inventoryRepository.getInventoryByManager(InventoryManger);
    }
    @TrackExecutionTime
    public List<Inventory> getInventoriesBySupplier(String InventorySupplier) {
        return inventoryRepository.getInventoryBySupplier(InventorySupplier);
    }
    @TrackExecutionTime
    public List<Inventory> getInventoriesByPhoneNumber(String phoneNumber) {
        return inventoryRepository.getInventoryByPhoneNumber(phoneNumber);
    }
    @TrackExecutionTime

    public List<Inventory> getInventoriesByOpeningHours(String openingHours) {
        return inventoryRepository.getInventoryByOpeningHours(openingHours);
    }
    @TrackExecutionTime

    public List<Inventory> getInventoriesByClosingHours(String closingHours) {
        return inventoryRepository.getInventoryByClosingHours(closingHours);
    }


}
