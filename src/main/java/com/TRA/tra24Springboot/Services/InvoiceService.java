package com.TRA.tra24Springboot.Services;

import com.TRA.tra24Springboot.Models.Invoice;
import com.TRA.tra24Springboot.Models.Product;
import com.TRA.tra24Springboot.Repositories.InvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Date;

@Service
public class InvoiceService {
    @Autowired
    InvoiceRepository invoiceRepository;
    @Autowired
    ProductService productService;

    public Invoice createInvoice(Invoice invoice) throws Exception {
        Product product=new Product();
        Product products=productService.addProduct(product);
        // invoice.setProducts(Arrays.asList(productService.getProducstById(52)));
        invoice.setProducts(Arrays.asList(products));
        invoice.setCreatedDate(new Date());
        invoice.setTotalAmount(Double.parseDouble("36"));
        invoice.setIsActive(Boolean.TRUE);
        invoice.setPaidAmount(Double.parseDouble("89"));

        return  invoiceRepository.save(invoice);

    }


}
