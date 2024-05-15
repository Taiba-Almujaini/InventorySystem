package com.TRA.tra24Springboot.DTO;

import com.TRA.tra24Springboot.Models.ProductDetails;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ProductDetailsDTO {
    Integer productId;
    String productName;
    Double productPrice;

    public static ProductDetailsDTO convertToDTO(ProductDetails productDetails) {
        ProductDetailsDTO productDetailsDTO = new ProductDetailsDTO();
        productDetailsDTO.setProductId(productDetails.getId());
        productDetailsDTO.setProductName(productDetails.getName());
        productDetailsDTO.setProductPrice(productDetails.getPrice());

        return productDetailsDTO;
    }

    public static List<ProductDetailsDTO> convertToDTO(List<ProductDetails> productsDetailslList) {
        List<ProductDetailsDTO> productDetailsDTO = new ArrayList<>();

        for (ProductDetails productDetailsDB : productsDetailslList) {
            ProductDetailsDTO dto = convertToDTO(productDetailsDB);
            productDetailsDTO.add(dto);
        }

        return productDetailsDTO;
    }

}


