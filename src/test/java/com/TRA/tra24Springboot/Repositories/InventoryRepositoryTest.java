package com.TRA.tra24Springboot.Repositories;

import com.TRA.tra24Springboot.Models.*;
import com.TRA.tra24Springboot.Utils.DateHelperUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
@ActiveProfiles("test")
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
class InventoryRepositoryTest {
@Autowired
InventoryRepository inventoryRepository;
    @BeforeEach
    void setUp() {
        Inventory inventory = Inventory.builder()
                .location("Muscat")
                .manager("Ahmed")
                .supplier("ABC")
                .phoneNumber("99999999")
                .openingHours("8 AM")
                .closingHours("8 PM")
                .build();
        inventoryRepository.save(inventory);

    }
    @Test
    void getInventoryByLocation() {
        List<Inventory>inventoryLocation=inventoryRepository.getInventoryByLocation("Muscat");
        assertThat(inventoryLocation).isNotNull();
        assertThat(inventoryLocation.size()).isEqualTo(1);
        assertThat(inventoryLocation.get(0).getLocation()).isEqualTo("Muscat");
    }

    @Test
    void getInventoryByManager() {
        List<Inventory> inventoryManager=inventoryRepository.getInventoryByManager("Ahmed");
        assertThat(inventoryManager).isNotNull();
        assertThat(inventoryManager.size()).isEqualTo(1);
        assertThat(inventoryManager.get(0).getManager()).isEqualTo("Ahmed");

    }

    @Test
    void getInventoryBySupplier() {
        List<Inventory> inventorySupplier =inventoryRepository.getInventoryBySupplier("ABC");
        assertThat(inventorySupplier).isNotNull();
        assertThat(inventorySupplier.size()).isEqualTo(1);
        assertThat(inventorySupplier.get(0).getSupplier()).isEqualTo("ABC");

    }
//
    @Test
    void getInventoryByPhoneNumber() {
        List<Inventory> inventoryPhoneNumber =inventoryRepository.getInventoryByPhoneNumber("99999999");
        assertThat(inventoryPhoneNumber).isNotNull();
        assertThat(inventoryPhoneNumber.size()).isEqualTo(1);
        assertThat(inventoryPhoneNumber.get(0).getPhoneNumber()).isEqualTo("99999999");
    }

    @Test
    void getInventoryByOpeningHours() {
        List<Inventory> inventoryOpeningHours =inventoryRepository.getInventoryByOpeningHours("8 AM");
        assertThat(inventoryOpeningHours).isNotNull();
        assertThat(inventoryOpeningHours.size()).isEqualTo(1);
        assertThat(inventoryOpeningHours.get(0).getOpeningHours()).isEqualTo("8 AM");

    }

    @Test
    void getInventoryByClosingHours() {
        List<Inventory> inventoryClosingHours=inventoryRepository.getInventoryByClosingHours("8 PM");
        assertThat(inventoryClosingHours).isNotNull();
        assertThat(inventoryClosingHours.size()).isEqualTo(1);
        assertThat(inventoryClosingHours.get(0).getClosingHours()).isEqualTo("8 PM");

    }
}