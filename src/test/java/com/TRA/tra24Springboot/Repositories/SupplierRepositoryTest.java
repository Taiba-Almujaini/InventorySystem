package com.TRA.tra24Springboot.Repositories;

import com.TRA.tra24Springboot.Models.Inventory;
import com.TRA.tra24Springboot.Models.Invoice;
import com.TRA.tra24Springboot.Models.PaymentType;
import com.TRA.tra24Springboot.Models.Supplier;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.assertj.core.api.Assertions.as;
import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;
@DataJpaTest
@ActiveProfiles("test")
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
class SupplierRepositoryTest {
@Autowired SupplierRepository supplierRepository;
    Integer supplierId;
    @BeforeEach
    void setUp() {
        Supplier supplier = Supplier.builder()
                .companyName("Bahwan")
                .country("Oman")
                .paymentMethods(PaymentType.BANK_TRANSFER)
                .shippingMethods("Air Freight")
                .minimumOrderQuantity("100")
                .build();
        supplierId =  supplierRepository.save(supplier).getId();

    }
    @Test
    void getSupplierById() {
        Supplier SupplierById = supplierRepository.findById(supplierId).orElse(null);
        assertThat(SupplierById).isNotNull();
        assertThat(SupplierById.getId()).isEqualTo(supplierId);
    }

    @Test
    void getSupplierByCompanyName() {
        List<Supplier> supplierCompanyName =supplierRepository.getSupplierByCompanyName("Bahwan");
        assertThat(supplierCompanyName).isNotNull();
        assertThat(supplierCompanyName.size()).isEqualTo(1);
        assertThat(supplierCompanyName.get(0).getCompanyName()).isEqualTo("Bahwan");
    }

    @Test
    void getSupplierByCountry() {
        List<Supplier> supplierCountry=supplierRepository.getSupplierByCountry("Oman");
        assertThat(supplierCountry).isNotNull();
        assertThat(supplierCountry.size()).isEqualTo(1);
        assertThat(supplierCountry.get(0).getCountry()).isEqualTo("Oman");

    }

    @Test
    void getSupplierByPaymentMethods() {
        List<Supplier> supplierPaymentMethod =supplierRepository.getSupplierByPaymentMethods(PaymentType.BANK_TRANSFER);
        assertThat(supplierPaymentMethod).isNotNull();
        assertThat(supplierPaymentMethod.size()).isEqualTo(1);
        assertThat(supplierPaymentMethod.get(0).getPaymentMethods()).isEqualTo(PaymentType.BANK_TRANSFER);

    }

    @Test
    void getSupplierByShippingMethods() {
        List<Supplier> supplierShippingMethods =supplierRepository.getSupplierByShippingMethods("Air Freight");
        assertThat(supplierShippingMethods).isNotNull();
        assertThat(supplierShippingMethods.size()).isEqualTo(1);
        assertThat(supplierShippingMethods.get(0).getShippingMethods()).isEqualTo("Air Freight");
    }

    @Test
    void getSupplierByMinimumOrderQuantity() {
        List<Supplier> supplierMinQuantity =supplierRepository.getSupplierByMinimumOrderQuantity("100");
        assertThat(supplierMinQuantity).isNotNull();
        assertThat(supplierMinQuantity.size()).isEqualTo(1);
        assertThat(supplierMinQuantity.get(0).getMinimumOrderQuantity()).isEqualTo("100");
    }
}