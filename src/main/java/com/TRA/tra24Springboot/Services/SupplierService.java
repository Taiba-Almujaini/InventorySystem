package com.TRA.tra24Springboot.Services;

import com.TRA.tra24Springboot.DTO.OrderDTO;
import com.TRA.tra24Springboot.DTO.SupplierDTO;
import com.TRA.tra24Springboot.Models.*;
import com.TRA.tra24Springboot.Repositories.ContactDetailsRepository;
import com.TRA.tra24Springboot.Repositories.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class SupplierService {
    @Autowired
    SupplierRepository supplierRepository;
    @Autowired
    ContactDetailsRepository contactDetailsRepository;



    public Supplier addSupplier(Supplier supplier) {
     ContactDetails contactDetails = new ContactDetails();

        contactDetails.setAddress("Muscat");
        contactDetails.setEmail("bahwan@gmail.com");
        contactDetails.setPhoneNumber("99999999");
        contactDetails.setFaxNumber("22222");
        contactDetails.setPostalCode("11111");

        contactDetails=contactDetailsRepository.save(contactDetails);
        supplier.setContactDetails(contactDetails);


        supplier.setCompanyName("Bahwan");
        supplier.setCountry("Oman");
        supplier.setNextDeliveryTime(new Date());
        supplier.setComplaints("no complaints");
        supplier.setPaymentMethods(PaymentType.BANK_TRANSFER);
        supplier.setShippingMethods("Air Freight ");
        supplier.setMinimumOrderQuantity("100");
        supplier.setIsActive(Boolean.TRUE);

        return supplierRepository.save(supplier);

    }
    public String deleteSupplier(Integer id){
        Supplier supplier= supplierRepository.getById(id);
        supplier.setIsActive(Boolean.FALSE);
        supplierRepository.save(supplier);
        return "Success";
    }
    public String updateSupplier(Integer id) {
        Supplier supplier = supplierRepository.getById(id);
        supplier.setUpdatedDate(new Date());

        supplierRepository.save(supplier);
        return "Updated Successfully";
    }

    public List<SupplierDTO> getSupplier(){
        List <Supplier> suppliers = supplierRepository.findAll();

        return SupplierDTO.convertToDTO(suppliers);
    }


    public Supplier getSuppliersById(Integer supplierId) {
        return supplierRepository.getSupplierById(supplierId);
    }

    public List<Supplier> getSuppliersByCompanyName(String companyName) {
        return supplierRepository.getOrderByCompanyName(companyName);
    }
}
