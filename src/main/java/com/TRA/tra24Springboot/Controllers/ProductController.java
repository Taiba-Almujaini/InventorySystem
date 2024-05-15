package com.TRA.tra24Springboot.Controllers;

import com.TRA.tra24Springboot.DTO.ProductDTO;
import com.TRA.tra24Springboot.Repositories.ProductDetailsRepository;
import com.TRA.tra24Springboot.Repositories.ProductRepository;
import com.TRA.tra24Springboot.Models.Product;
import com.TRA.tra24Springboot.Models.ProductDetails;
import com.TRA.tra24Springboot.Services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public String deleteProduct(@RequestParam Integer id){

        return productService.deleteProduct(id);
    }

    @PutMapping("update")
    public String updateProduct(@RequestParam Integer id, @RequestParam Integer quantity){
        return  productService.updateProduct(id,quantity);
    }
    @GetMapping("getAll")
    public List<ProductDTO> getProducts(){
        return productService.getProducts();
    }
}
