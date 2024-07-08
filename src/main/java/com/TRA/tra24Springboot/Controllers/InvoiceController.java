package com.TRA.tra24Springboot.Controllers;


import com.TRA.tra24Springboot.Models.Inventory;
import com.TRA.tra24Springboot.Models.Invoice;
import com.TRA.tra24Springboot.Repositories.InvoiceRepository;
import com.TRA.tra24Springboot.Services.InvoiceService;
import com.TRA.tra24Springboot.Services.SlackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/invoice")
public class InvoiceController {

    @Autowired
    InvoiceRepository invoiceRepository;
    @Autowired
    InvoiceService invoiceService;
    @Autowired
    SlackService slackService;
    @PostMapping("createInvoice")
    public Invoice createInvoice(Invoice invoice) throws Exception {
        slackService.sendMessage("taiba","new invoice created");
        return invoiceService.createInvoice(invoice);
    }
    //@Scheduled(cron = "0 0 9 * * ?")
    @PostMapping("dueDate")
    public void senDueDateReminder() {
        Integer remainingDays = 1;
        List<Invoice> invoices = invoiceService.getInvoiceDueInNextDays(remainingDays);
        for (Invoice invoice : invoices) {
            StringBuilder message = new StringBuilder();
            message.append("Reminder: Invoice #")
                    .append(invoice.getId())
                    .append(" is due on ")
                    .append(invoice.getDueDate().toString());
            slackService.sendMessage("practice", message.toString());
        }
    }
}
