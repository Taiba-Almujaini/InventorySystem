package com.TRA.tra24Springboot.Repositories;

import com.TRA.tra24Springboot.Models.Inventory;
import com.TRA.tra24Springboot.Models.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface InventoryRepository extends JpaRepository <Inventory, Integer> {
    @Query("SELECT i FROM Inventory i WHERE i.id = :inventoryId")
    Inventory getInventoryById(@Param("inventoryId") Integer inventoryId);

    @Query("SELECT i FROM Inventory i WHERE i.location = :inventoryLocation")
    List<Inventory> getInventoryByLocation(@Param("inventoryLocation") String inventoryLocation);

    @Query("SELECT i FROM Inventory i WHERE i.manager = :inventoryManager")
    List<Inventory> getInventoryByManager(@Param("inventoryManager") String inventoryManager);

    @Query("SELECT i FROM Inventory i WHERE i.supplier = :inventorySupplier")
    List<Inventory> getInventoryBySupplier(@Param("inventorySupplier") String inventorySupplier);

    @Query("SELECT i FROM Inventory i WHERE i.phoneNumber = :phoneNumber")
    List<Inventory> getInventoryByPhoneNumber(@Param("phoneNumber") String phoneNumber);

    @Query("SELECT i FROM Inventory i WHERE i.openingHours = :openingHours")
    List<Inventory> getInventoryByOpeningHours(@Param("openingHours") String openingHours);

    @Query("SELECT i FROM Inventory i WHERE i.closingHours = :closingHours")
    List<Inventory> getInventoryByClosingHours(@Param("closingHours") String closingHours);


}
