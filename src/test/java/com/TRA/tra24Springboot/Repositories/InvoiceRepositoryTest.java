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

class InvoiceRepositoryTest {
@Autowired
InvoiceRepository invoiceRepository;
Date date= new Date();
    Integer invoiceId;
    Date dueDate=DateHelperUtils.addDays(date, 5);
    @BeforeEach
    void setUp() {
        Invoice invoice = Invoice.builder()
                .dueDate(dueDate)
                .build();
        invoice.setCreatedDate(date);
        invoiceId =  invoiceRepository.save(invoice).getId();


    }
    @Test
    void getInvoiceById() {
        Invoice invoiceById = invoiceRepository.findById(invoiceId).orElse(null);
        assertThat(invoiceById).isNotNull();
        assertThat(invoiceById.getId()).isEqualTo(invoiceId);
    }
    @Test
    void getInvoiceByCreatedDate() {
        List<Invoice> invoiceCretedDate =invoiceRepository.getInvoiceByCreatedDate(date);
        assertThat(invoiceCretedDate.size()).isEqualTo(1);
        assertThat(invoiceCretedDate.get(0).getCreatedDate()).isEqualTo(date);
    }

    @Test
    void getInvoiceByDueDate() {
        List<Invoice> invoiceDueDate=invoiceRepository.getInvoiceByDueDate(dueDate);
        assertThat(invoiceDueDate).isNotNull();
        assertThat(invoiceDueDate.size()).isEqualTo(1);
        assertThat(invoiceDueDate.get(0).getDueDate()).isEqualTo(dueDate);
    }

    @Test
    void getInvoicesByDueDateBetween() {
        List<Invoice> invoicesDueDateBetween = invoiceRepository.getInvoicesByDueDateBetween(date, dueDate);
        assertThat(invoicesDueDateBetween).isNotNull();
        assertThat(invoicesDueDateBetween.size()).isEqualTo(1);
    }

    @Test
    void getOverdueInvoices() {
        List<Invoice> overDueInvoices = invoiceRepository.getOverdueInvoices(date);
        assertThat(overDueInvoices).isNotNull();
        assertThat(overDueInvoices.size()).isEqualTo(0);
    }

    @Test
    void getInvoicesCreatedBetween() {
        List<Invoice> invoicesCreatedBetweenDates = invoiceRepository.getInvoicesCreatedBetween(date, DateHelperUtils.addDays(date,5));
        assertThat(invoicesCreatedBetweenDates).isNotNull();
        assertThat(invoicesCreatedBetweenDates.size()).isEqualTo(1);
    }

    @Test
    void getPaidInvoicesBetween() {
        List<Invoice> invoicesPaidBetweenDates = invoiceRepository.getPaidInvoicesBetween(date, DateHelperUtils.addDays(date,5));
        assertThat(invoicesPaidBetweenDates).isNotNull();
        assertThat(invoicesPaidBetweenDates.size()).isEqualTo(0);
    }
}