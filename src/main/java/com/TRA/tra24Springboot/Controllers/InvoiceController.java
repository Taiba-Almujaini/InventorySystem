package com.TRA.tra24Springboot.Controllers;


import com.TRA.tra24Springboot.Models.Inventory;
import com.TRA.tra24Springboot.Models.Invoice;
import com.TRA.tra24Springboot.Repositories.InvoiceRepository;
import com.TRA.tra24Springboot.Services.InvoiceService;
import com.TRA.tra24Springboot.Services.SlackService;
import com.TRA.tra24Springboot.Utils.DateHelperUtils;
import com.TRA.tra24Springboot.logging.TrackExecutionTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
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
    @TrackExecutionTime
    public Invoice createInvoice(Invoice invoice) throws Exception {
        slackService.sendMessage("taiba","new invoice created");
        return invoiceService.createInvoice(invoice);
    }
    //@Scheduled(cron = "0 0 9 * * ?")
    @PostMapping("dueDate")
    @TrackExecutionTime
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
    //@Scheduled(cron = "0 0 9 * * 0") //runs every Sunday
    @PostMapping("weeklyReport")
    @TrackExecutionTime
    public void weeklyInvoiceReport(){
        Date today = new Date();
        Date startDate = DateHelperUtils.subtractDays(today, 6); //during the last 7 days

        List<Invoice> createdInvoices = invoiceService.getInvoicesCreatedBetween(startDate, today);
        List<Invoice> paidInvoices = invoiceService.getPaidInvoicesBetween(startDate, today);
        List<Invoice> overdueInvoices = invoiceService.getOverDueInvoices();

        StringBuilder report = new StringBuilder();
        report.append("Weekly Summary Report:\n")
                .append("Invoices Created:\n");
        appendInvoicesToReport(report, createdInvoices);
        report.append("\nInvoices Paid:\n");
        appendInvoicesToReport(report, paidInvoices);
        report.append("\nOverdue Invoices:\n");
        appendInvoicesToReport(report, overdueInvoices);

        slackService.sendMessage("taiba", report.toString());
    }

    private void appendInvoicesToReport(StringBuilder report, List<Invoice> invoices){
        for (Invoice invoice : invoices) {
            report.append("Invoice #")
                    .append(invoice.getId())
                    .append(" - Due on ")
                    .append(invoice.getDueDate().toString())
                    .append("\n");
        }
    }


    //@Scheduled(cron = "0 0 9 1 * ?") //runs on the first day of every month at 9:00 AM
    @PostMapping("monthlyReport")
    @TrackExecutionTime
    public void monthlyInvoiceReport(){

        //calculating start and end dates for the current month
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, 1); //setting to the first day of the month
        Date startDate = calendar.getTime();

        calendar.add(Calendar.MONTH, 1); //moving to the next month
        calendar.add(Calendar.DAY_OF_MONTH, -1); //setting to the last day of the current month
        Date endDate = calendar.getTime();

        List<Invoice> createdInvoices = invoiceService.getInvoicesCreatedBetween(startDate, endDate);
        List<Invoice> paidInvoices = invoiceService.getPaidInvoicesBetween(startDate, endDate);
        List<Invoice> overdueInvoices = invoiceService.getOverDueInvoices();

        StringBuilder report = new StringBuilder();
        SimpleDateFormat dateFormat = new SimpleDateFormat("MMMM yyyy");
        report.append("Monthly Invoices Report - ")
                .append(dateFormat.format(startDate))
                .append(":\n")
                .append("Invoices Created:\n");
        appendInvoicesToReport(report, createdInvoices);
        report.append("\nInvoices Paid:\n");
        appendInvoicesToReport(report, paidInvoices);
        report.append("\nOverdue Invoices:\n");
        appendInvoicesToReport(report, overdueInvoices);

        slackService.sendMessage("practice", report.toString());
    }

}
