package com.TRA.tra24Springboot.Repositories;

import com.TRA.tra24Springboot.Models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {


    @Query("SELECT p FROM Product p WHERE p.productDetails.name = :productName")
    List<Product> getProductByName(@Param("productName") String productName);

    @Query("SELECT p FROM Product p WHERE p.id = :productId")
    Product getProductById(@Param("productId") Integer productId);

    @Query("SELECT p FROM Product p WHERE p.productDetails.color = :productColor")
    List<Product> getProductByColor(@Param("productColor") String productColor);

    @Query("SELECT p FROM Product p WHERE p.productDetails.size = :productSize")
    List<Product> getProductBySize(@Param("productSize") String productSize);

    @Query("SELECT p FROM Product p WHERE p.productDetails.price = :productPrice")
    List<Product> getProductByPrice(@Param("productPrice") Double productPrice);

    @Query("SELECT p FROM Product p WHERE p.productDetails.countryOfOrigin = :productCountry")
    List<Product> getProductByCountryOfOrigin (@Param("productCountry") String productCountry);



}
