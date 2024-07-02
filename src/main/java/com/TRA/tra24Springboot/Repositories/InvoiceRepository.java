package com.TRA.tra24Springboot.Repositories;

import com.TRA.tra24Springboot.Models.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvoiceRepository extends JpaRepository<Invoice, Integer> {
}
