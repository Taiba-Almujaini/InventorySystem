package com.TRA.tra24Springboot.Services;

import com.TRA.tra24Springboot.Models.Invoice;
import com.TRA.tra24Springboot.Models.Product;
import com.TRA.tra24Springboot.Repositories.InvoiceRepository;
import com.TRA.tra24Springboot.Utils.DateHelperUtils;
import com.TRA.tra24Springboot.logging.TrackExecutionTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Service
public class InvoiceService {
    @Autowired
    InvoiceRepository invoiceRepository;
    @Autowired
    ProductService productService;
    @TrackExecutionTime
    public Invoice createInvoice(Invoice invoice) throws Exception {
        Product product=new Product();
        Product products=productService.addProduct(product);
        // invoice.setProducts(Arrays.asList(productService.getProducstById(52)));
        invoice.setProducts(Arrays.asList(products));
        invoice.setCreatedDate(new Date());
        invoice.setTotalAmount(Double.parseDouble("36"));
        invoice.setIsActive(Boolean.TRUE);
        invoice.setPaidAmount(Double.parseDouble("89"));
        Date dueDate=DateHelperUtils.addDays(invoice.getCreatedDate(),1);

        return  invoiceRepository.save(invoice);

    }
    @TrackExecutionTime
    public Invoice getBInvoiceById(Integer id) {
        return invoiceRepository.getInvoiceById(id);
    }
    @TrackExecutionTime
    public List<Invoice> getInvoiceByDueDate(Date dueDate) {
        return invoiceRepository.getInvoiceByDueDate(dueDate);
    }
    // method to get invoices due in next few days
    @TrackExecutionTime
    public List<Invoice> getInvoiceDueInNextDays(Integer days){
        Date today = new Date();
        Date dueDate = DateHelperUtils.addDays(today, days);
        return invoiceRepository.getInvoicesByDueDateBetween(today, dueDate);
    }
    @TrackExecutionTime
    public List<Invoice> getInvoicesCreatedBetween(Date startDate, Date endDate) {
        return invoiceRepository.getInvoicesCreatedBetween(startDate, endDate);
    }
    @TrackExecutionTime
    public List<Invoice> getPaidInvoicesBetween(Date startDate, Date endDate) {
        return invoiceRepository.getPaidInvoicesBetween(startDate, endDate);
    }
    @TrackExecutionTime
    //method to get overdue invoices
    public List<Invoice> getOverDueInvoices(){
        Date today = new Date();
        return invoiceRepository.getOverdueInvoices(today);
    }

}


