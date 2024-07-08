package com.TRA.tra24Springboot.Controllers;

import com.TRA.tra24Springboot.DTO.ProductDTO;
import com.TRA.tra24Springboot.Repositories.ProductDetailsRepository;
import com.TRA.tra24Springboot.Repositories.ProductRepository;
import com.TRA.tra24Springboot.Models.Product;
import com.TRA.tra24Springboot.Models.ProductDetails;
import com.TRA.tra24Springboot.Services.ProductService;
import com.TRA.tra24Springboot.Services.SlackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
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
    @Autowired
    SlackService slackService;

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
    public ResponseEntity<?> getProductByPrice(@RequestParam Double price) {
        try {
            List<Product> result = productService.getProductsByPrice(price);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Retrieving products by price failed! " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("getByCountry")
    public ResponseEntity<?> getProductByCountryOfOrigin(@RequestParam String country) {
        try {
            List<Product> result = productService.getProductsByCountryOfOrigin(country);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Retrieving products by country of origin failed! " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }}

    @GetMapping("getBySKU")
    public ResponseEntity<?> getProductBySKU(@RequestParam UUID sku) {
        try {
            Product result = productService.getProductsBySKU(sku);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Retrieving product by SKU failed! " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("getByCategory")
    public ResponseEntity<?> getProductByCategory(@RequestParam String category) {
        try {
            List<Product> result = productService.getProductsByCategory(category);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Retrieving products by category failed! " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("getByQuantity")
    public ResponseEntity<?> getProductByQuantity(@RequestParam Integer quantity) {
        try {
            List<Product> result = productService.getProductsByQuantity(quantity);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Retrieving products by quantity failed! " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("getByIsActive")
    public ResponseEntity<?> getProductByIsActive(@RequestParam Boolean isActive) {
        try {
            List<Product> result = productService.getProductsByIsActive(isActive);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Retrieving products by isActive failed! " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
  /**  @GetMapping("checkStock")
    public List<Product> getLowStockReport() {
        // Fetch low stock products
        slackService.sendMessage("taiba","low stock");
        return productService.getLowStockProducts();

    }**/
  @Scheduled(cron = "0 0 0/6 * * *")
  @GetMapping("/checkStock")
  public List<Product> getLowStockReport() {

      List<Product> lowStockProducts = productService.getLowStockProducts();
      if (!lowStockProducts.isEmpty()) {
          StringBuilder messageBuilder = new StringBuilder();
          messageBuilder.append("Low stock alert:\n");
          for (Product product : lowStockProducts) {
              messageBuilder.append("Product ID: ").append(product.getId())
                      .append(", Product: ").append(product.getProductDetails().getName())
                      .append(", Quantity: ").append(product.getQuantity()).append("\n");
          }
          slackService.sendMessage("taiba", messageBuilder.toString());
      }
      return lowStockProducts;
  }

}
