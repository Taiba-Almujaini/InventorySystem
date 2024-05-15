package com.TRA.tra24Springboot.Controllers;

import com.TRA.tra24Springboot.DTO.InventoryDTO;
import com.TRA.tra24Springboot.DTO.ProductDTO;
import com.TRA.tra24Springboot.Models.Inventory;
import com.TRA.tra24Springboot.Models.Product;
import com.TRA.tra24Springboot.Repositories.InventoryRepository;
import com.TRA.tra24Springboot.Services.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
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
      Inventory globalInventory= new Inventory(); //instance of Inventory Class
      Product globalProduct = new Product();

    //method for receiving new stock
    @PostMapping("receive")
    public Inventory receiveStock( Inventory inventory) {
        return inventoryService.receiveStock(inventory);
    }

    //method for returns
//    @PutMapping("return")
//    //method to returns
//    public Inventory returns( Inventory inventory) {
//        return  inventoryService.returns(inventory);
//    }

    //method of write-offs
    @PutMapping("write")
    public String writeOff(Integer id){
       return inventoryService.writeOff(id);
    }
    @GetMapping("getAll")
    public List<InventoryDTO> getInventory(){
        return inventoryService.getInventory();
    }
}