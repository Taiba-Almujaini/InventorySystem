package com.TRA.tra24Springboot.Services;

import com.TRA.tra24Springboot.DTO.ProductDTO;
import com.TRA.tra24Springboot.Models.Product;
import com.TRA.tra24Springboot.Models.ProductDetails;
import com.TRA.tra24Springboot.Repositories.ProductDetailsRepository;
import com.TRA.tra24Springboot.Repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class ProductService {
   @Autowired
    ProductRepository productRepository;
   @Autowired
   ProductDetailsRepository productDetailsRepository;


    public Product addProduct(Product product){

       ProductDetails productDetails = new ProductDetails();
       productDetails.setName("Orange");
       productDetails.setColor("Green");
       productDetails.setSize("Small");
       productDetails.setPrice(10d);
       productDetails.setCountryOfOrigin("USA");
       productDetails.setDescription("Apple Product");

       productDetails=productDetailsRepository.save(productDetails);
       product.setProductDetails(productDetails);
       product.setSku(UUID.randomUUID());
       product.setCategory("Electronics");
       product.setQuantity(1);
       product.setIsActive(Boolean.TRUE);
       product.setCreatedDate(new Date());

       return productRepository.save(product);


   }
    public String deleteProduct(Integer id){
      Product product=productRepository.getById(id);
       product.setIsActive(Boolean.FALSE);
       productRepository.save(product);
       return "Success";
    }


    public String updateProduct(Integer id, Integer quantity) {
        Product product = productRepository.getById(id);
        product.setQuantity(quantity);
        product.setUpdatedDate(new Date());

        productRepository.save(product);
        return "Updated Successfully";
    }


    public List<ProductDTO> getProducts(){
        List <Product> products = productRepository.findAll();

        return ProductDTO.convertToDTO(products);
    }


    public List<Product> getProductsByName(String productName) {
        return productRepository.getProductByName(productName);
    }
    public List<Product> getProductsByColor(String productColor) {
        return productRepository.getProductByColor(productColor);
    }

    public List<Product> getProductsBySize(String productSize) {
        return productRepository.getProductBySize(productSize);
    }
    public Product getProductsById(Integer productId) {
        return productRepository.getProductById(productId);
    }
    public List<Product>getProductsByPrice(Double productPrice) {
        return productRepository.getProductByPrice(productPrice);
    }
    public List<Product>getProductsByCountryOfOrigin(String productCountry) {
        return productRepository.getProductByCountryOfOrigin(productCountry);
    }


}
