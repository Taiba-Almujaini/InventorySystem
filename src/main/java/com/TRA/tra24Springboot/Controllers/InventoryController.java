package com.TRA.tra24Springboot.Controllers;

import com.TRA.tra24Springboot.DTO.InventoryDTO;
import com.TRA.tra24Springboot.DTO.ProductDTO;
import com.TRA.tra24Springboot.Models.Inventory;
import com.TRA.tra24Springboot.Models.Order;
import com.TRA.tra24Springboot.Models.Product;
import com.TRA.tra24Springboot.Repositories.InventoryRepository;
import com.TRA.tra24Springboot.Services.InventoryService;
import com.TRA.tra24Springboot.Services.SlackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/Inventory")
public class InventoryController {
   @Autowired
   InventoryRepository inventoryRepositories;
    @Autowired
    InventoryService inventoryService;

    @Autowired
    SlackService slackService;
      Inventory globalInventory= new Inventory(); //instance of Inventory Class
      Product globalProduct = new Product();

    //method for receiving new stock
    @PostMapping("receive")
    public Inventory receiveStock( Inventory inventory) {
        return inventoryService.receiveStock(inventory);
    }

    //method of write-offs
    @PutMapping("write")
    public String writeOff(Integer id){
       return inventoryService.writeOff(id);
    }
    @GetMapping("getAll")
    public List<InventoryDTO> getInventory(){
        return inventoryService.getInventory();
    }

    @GetMapping("getByInventoryId")
    public Inventory getInventoryById(@RequestParam Integer id) {
        return inventoryService.getInventoriesById(id);

    }

    @GetMapping("getByLocation")
    public List<Inventory> getInventoryByLocation(@RequestParam String location) {
        return inventoryService.getInventoriesByLocation(location);

    }
    @GetMapping("getByManager")
    public List<Inventory> getInventoryByManager(@RequestParam String manager) {
        return inventoryService.getInventoriesByManager(manager);

    }

    @GetMapping("getBySupplier")
    public List<Inventory> getInventoryBySupplier(@RequestParam String Supplier) {
        return inventoryService.getInventoriesBySupplier(Supplier);

    }

    @GetMapping("getByPhoneNumber")
    public List<Inventory> getInventoryByPhoneNumber(@RequestParam String phoneNumber) {
        return inventoryService.getInventoriesByPhoneNumber(phoneNumber);

    }
    @GetMapping("getByOpeningHours")
    public List<Inventory> getInventoryByOpeningHours(@RequestParam String openingHours) {
        return inventoryService.getInventoriesByOpeningHours(openingHours);

    }
    @GetMapping("getByClosingHours")
    public List<Inventory> getInventoryByClosingHours(@RequestParam String closingHours) {
        return inventoryService.getInventoriesByClosingHours(closingHours);

    }

    @GetMapping("messages")
    public void sendMessage(){
        slackService.sendMessage("", "");
    }

}