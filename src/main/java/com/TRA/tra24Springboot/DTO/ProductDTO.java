package com.TRA.tra24Springboot.DTO;

import com.TRA.tra24Springboot.Models.Product;
import com.TRA.tra24Springboot.Models.ProductDetails;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ProductDTO {

    ProductDetailsDTO productDetailsDTO;


    public static ProductDTO convertToDTO(Product product) {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setProductDetailsDTO(ProductDetailsDTO.convertToDTO(product.getProductDetails()));

        return productDTO;
    }

    public static List<ProductDTO> convertToDTO(List<Product> productslList) {
        List<ProductDTO> productDTOS = new ArrayList<>();

        for (Product product : productslList) {
            ProductDTO dto = convertToDTO(product);
            productDTOS.add(dto);
        }

        return productDTOS;
    }

}
