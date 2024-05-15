package com.TRA.tra24Springboot.DTO;

import com.TRA.tra24Springboot.Models.Order;
import com.TRA.tra24Springboot.Models.Supplier;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class SupplierDTO {
    
    Integer supplierId;
    String companyName;
    List<OrderDTO>orders;

    public static SupplierDTO convertToDTO(Supplier supplier) {
        SupplierDTO supplierDTO = new SupplierDTO();
        supplierDTO.setSupplierId(supplier.getId());
        supplierDTO.setCompanyName(supplier.getCompanyName());
        supplierDTO.setOrders(OrderDTO.convertToDTO(supplier.getOrders()));

        return supplierDTO;
    }

    public static List<SupplierDTO> convertToDTO(List<Supplier> supplierList) {
        List<SupplierDTO> supplierDTOS = new ArrayList<>();

        for (Supplier supplierDetailsDB : supplierList) {
            SupplierDTO dto = convertToDTO(supplierDetailsDB);
            supplierDTOS.add(dto);
        }

        return supplierDTOS;
    }
}
