package com.TRA.tra24Springboot.Controllers;

import com.TRA.tra24Springboot.DTO.ProductDTO;
import com.TRA.tra24Springboot.Repositories.ProductDetailsRepository;
import com.TRA.tra24Springboot.Repositories.ProductRepository;
import com.TRA.tra24Springboot.Models.Product;
import com.TRA.tra24Springboot.Models.ProductDetails;
import com.TRA.tra24Springboot.Services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    ProductRepository productRepository;

    @Autowired
    ProductService productService;
    @Autowired
    ProductDetailsRepository productDetailsRepository;
    Product globalProduct = new Product();

    @PostMapping("add")
    public Product addProduct(Product product){

        return productService.addProduct(product);
    }

    @PostMapping("delete")
    public <T> ResponseEntity<T> delete(@RequestParam Integer id) throws Exception {
        try {
            String result = productService.deleteProduct(id);
            return (ResponseEntity<T>) new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e){
            return (ResponseEntity<T>) new ResponseEntity<>("Deleting failed! " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }




    @PutMapping("update")
    public <T> ResponseEntity<T> updateProduct(@RequestParam Integer id, @RequestParam Integer quantity) throws Exception {
        try {
            String result = productService.updateProduct(id, quantity);
            return (ResponseEntity<T>) new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            return (ResponseEntity<T>) new ResponseEntity<>("Updating failed! " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("getAll")
    public List<ProductDTO> getProducts(){
        return productService.getProducts();
    }


    @GetMapping("getByName")
    public ResponseEntity<?> getProductByName(@RequestParam String name) throws Exception  {

        try {

            List<Product> result=productService.getProductsByName(name);
            return new ResponseEntity<>(result, HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity<>("Retrieving products by name failed! " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("getByColor")
    public ResponseEntity<?> getProductByColor(@RequestParam String color) throws Exception {

        try {
            List<Product> result = productService.getProductsByColor(color);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Retrieving products by color failed! " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("getBySize")
    public ResponseEntity<?> getProductBySize(@RequestParam String size) {
        try {
            List<Product> result = productService.getProductsBySize(size);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Retrieving products by size failed! " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("getById")
    public ResponseEntity<?> getProductById(@RequestParam Integer id) {
        try {
            Product result = productService.getProducstById(id);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Retrieving product by ID failed! " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("getByPrice")
    public List<Product> getProductById(@RequestParam Double price) {
        return productService.getProductsByPrice(price);

    }
    @GetMapping("getByCountry")
    public List<Product> getProductByCountryOfOrigin(@RequestParam String country) {
        return productService.getProductsByCountryOfOrigin(country);

    }
    @GetMapping("getBySKU")
    public Product getProductBySKU(@RequestParam UUID sku) {

        return productService.getProductsBySKU(sku);

    }
    @GetMapping("getByCategory")
    public List<Product> getProductByCategory(@RequestParam String category) {

        return productService.getProductsByCategory(category);

}
    @GetMapping("getByQuantity")
    public List<Product> getProductByQuantity(@RequestParam Integer quantity) {

        return productService.getProductsByQuantity(quantity);

    }
    @GetMapping("getByIsActive")
    public List<Product> getProductByIsActive(@RequestParam Boolean isActive) {

        return productService.getProductsIsActive(isActive);

    }
}
