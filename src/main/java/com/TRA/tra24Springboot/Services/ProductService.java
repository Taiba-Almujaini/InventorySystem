package com.TRA.tra24Springboot.Services;

import com.TRA.tra24Springboot.DTO.ProductDTO;
import com.TRA.tra24Springboot.Models.Product;
import com.TRA.tra24Springboot.Models.ProductDetails;
import com.TRA.tra24Springboot.Repositories.ProductDetailsRepository;
import com.TRA.tra24Springboot.Repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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
        Product product = productRepository.getProductById(id);
        product.setQuantity(quantity);
        product.setUpdatedDate(new Date());

        productRepository.save(product);
        return "Updated Successfully";
    }


    public List<ProductDTO> getProducts(){
        List <Product> products = productRepository.findAll();

        return ProductDTO.convertToDTO(products);
    }


    public List<Product> getProductsByName(String name) throws Exception {
        List<Product> products = productRepository.getProductByName(name);
        if (products.isEmpty()) {
            throw new Exception("No products found with the name: " + name);
        }
        return products;
    }
    public List<Product> getProductsByColor(String color) throws Exception {
        List<Product> products = productRepository.getProductByColor(color);
        if (products.isEmpty()) {
            throw new Exception("No products found with the color: " + color);
        }
        return products;
    }

    public List<Product> getProductsBySize(String size) throws Exception {
        List<Product> products = productRepository.getProductBySize(size);
        if (products.isEmpty()) {
            throw new Exception("No products found with the size: " + size);
        }
        return products;
    }
    public Product getProducstById(Integer id) throws Exception {
        Product product = productRepository.getProductById(id);
        if (product == null) {
            throw new Exception("No product found with ID: " + id);
        }
        return product;
    }
    public List<Product> getProductsByPrice(Double price) throws Exception {
        List<Product> products = productRepository.getProductByPrice(price);
        if (products.isEmpty()) {
            throw new Exception("No products found with the price: " + price);
        }
        return products;
    }
    public List<Product> getProductsByCountryOfOrigin(String country) throws Exception {
        List<Product> products = productRepository.getProductByCountryOfOrigin(country);
        if (products.isEmpty()) {
            throw new Exception("No products found from the country: " + country);
        }
        return products;
    }

    public Product getProductsBySKU(UUID sku) throws Exception {
        Product product = productRepository.getProductBySKU(sku);
        if (product == null) {
            throw new Exception("No product found with SKU: " + sku);
        }
        return product;
    }

    public List<Product> getProductsByCategory(String category) throws Exception {
        List<Product> products = productRepository.getProductByCategory(category);
        if (products.isEmpty()) {
            throw new Exception("No products found with the category: " + category);
        }
        return products;
    }
    public List<Product> getProductsByQuantity(Integer quantity) throws Exception {
        List<Product> products = productRepository.getProductByQuantity(quantity);
        if (products.isEmpty()) {
            throw new Exception("No products found with the quantity: " + quantity);
        }
        return products;
    }
    public List<Product> getProductsByIsActive(Boolean isActive) throws Exception {
        List<Product> products = productRepository.getProductIsActive(isActive);
        if (products.isEmpty()) {
            throw new Exception("No products found with isActive: " + isActive);
        }
        return products;
    }
    /**public List<Product> getLowStockProducts() {
        List<Product> products = productRepository.findAll();
        List<Product> lowStockProducts = new ArrayList<>();
        for (Product product : products) {
            if (product.getQuantity() <1) {
                lowStockProducts.add(product);
            }
        }
        return lowStockProducts;
    }**/
    public List<Product> getLowStockProducts() {
        List<Product> products = productRepository.findAll();
        List<Product> lowStockProducts = new ArrayList<>();
        for (Product product : products) {
            if (product.getQuantity() < 50) {
                lowStockProducts.add(product);
            }
        }
        return lowStockProducts;
    }
}
