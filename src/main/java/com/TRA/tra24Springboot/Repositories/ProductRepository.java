package com.TRA.tra24Springboot.Repositories;

import com.TRA.tra24Springboot.Models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

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

    @Query("SELECT p FROM Product p WHERE p.sku = :productSKU")
    Product getProductBySKU (@Param("productSKU") UUID productSKU);

    @Query("SELECT p FROM Product p WHERE p.category = :productCategory")
    List<Product> getProductByCategory(@Param("productCategory") String productCategory);

    @Query("SELECT p FROM Product p WHERE p.quantity = :productQuantity")
    List<Product> getProductByQuantity(@Param("productQuantity") Integer productQuantity);


    @Query("SELECT p FROM Product p WHERE p.isActive = :productIsActive")
    List<Product> getProductIsActive(@Param("productIsActive") Boolean productIsActive);


}
