package com.TRA.tra24Springboot.Controllers;

import com.TRA.tra24Springboot.DTO.InventoryDTO;
import com.TRA.tra24Springboot.DTO.ProductDTO;
import com.TRA.tra24Springboot.Models.Inventory;
import com.TRA.tra24Springboot.Repositories.InventoryRepository;
import com.TRA.tra24Springboot.Services.InventoryService;
import com.TRA.tra24Springboot.Services.MailingService;
import com.TRA.tra24Springboot.Services.ReportService;
import com.TRA.tra24Springboot.Services.SlackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Inventory")
public class InventoryController {
    @Autowired
    InventoryRepository inventoryRepositories;
    @Autowired
    InventoryService inventoryService;
    @Autowired
    MailingService mailingService;
    @Autowired
    SlackService slackService;
    @Autowired
    ReportService reportService;
    Inventory globalInventory = new Inventory(); //instance of Inventory Class

    //method for receiving new stock
    @PostMapping("receive")

    public Inventory receiveStock(Inventory inventory) {
        return inventoryService.receiveStock(inventory);
    }

    //method of write-offs
    @PutMapping("write")
    public String writeOff(Integer id) {
        return inventoryService.writeOff(id);
    }

    @Scheduled(cron = "0 0 * * * ?")
    @GetMapping("getAll")
    public List<InventoryDTO> getInventory() throws Exception {
        reportService.createInventoryReport();
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
    public void sendMessage() {
        slackService.sendMessage("", "");
    }


    @GetMapping("sendEmail")
    public void sendEmail() {
        mailingService.sendSimpleMail();
    }

    @Scheduled(cron = "0 0 * * * ?")
    @GetMapping("sendSlackReport")
    public ResponseEntity<?> sendReportToSlack() {
        try {
            List<InventoryDTO> inventories = inventoryService.getInventory();
            StringBuilder message = new StringBuilder();

            // Start the message with a header
            message.append("*Inventory Report* :memo:\n");

            // Iterate over each inventory and format the message
            for (InventoryDTO inventory : inventories) {
                message.append("\n---------------------\n");
                message.append("*Inventory ID:* ").append(inventory.getInventoryId()).append("\n");
                message.append("*Location:* ").append(inventory.getLocation()).append("\n");
                message.append("*Products:*\n");

                // Iterate over each product in the inventory
                for (ProductDTO product : inventory.getProducts()) {
                    if (product.getProductDetailsDTO() != null) {
                        message.append("  - *Product Name:* ")
                                .append(product.getProductDetailsDTO().getProductName()).append("\n");

                    }
                }

                // Send the formatted message to Slack
                slackService.sendMessage("taiba", message.toString());

                // Clear the message buffer for the next inventory
                message.setLength(0);
            }

            // Generate the inventory report
            reportService.createInventoryReport();

            // Return the response with the inventories
            return new ResponseEntity<>(inventories, HttpStatus.OK);
        } catch (Exception e) {
            // Return an error response in case of failure
            return new ResponseEntity<>("Retrieving inventories failed! " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
