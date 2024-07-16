package com.TRA.tra24Springboot.Repositories;

import com.TRA.tra24Springboot.Models.Product;
import com.TRA.tra24Springboot.Models.ProductDetails;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;


@DataJpaTest
@ActiveProfiles("test")
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)

class ProductRepositoryTest {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductDetailsRepository productDetailsRepository;
    private UUID sku;
    @BeforeEach
    void setUp() {
        ProductDetails productDetails = ProductDetails.builder()
                .name("phone")
                .color("White")
                .countryOfOrigin("USA")
                .price(500.0)
                .size("small")
                .build();

        productDetailsRepository.save(productDetails);

        sku = UUID.randomUUID();

        Product product = Product.builder()
                .productDetails(productDetails)
                .category("Electronics")
                .quantity(10)
                .sku(sku)
                .build();
        product.setIsActive(Boolean.TRUE);
        productRepository.save(product);
    }

    @Test
    void getProductByName() {
        List<Product> product = productRepository.getProductByName("phone");
        assertThat(product).isNotNull();
        assertThat(product.size()).isEqualTo(1);
    }

    @Test
    void getProductByColor() {
        List<Product> productsOfColor = productRepository.getProductByColor("White");
        assertThat(productsOfColor).isNotNull();
        assertThat(productsOfColor.size()).isEqualTo(1);
        assertThat(productsOfColor.get(0).getProductDetails().getColor()).isEqualTo("White");
    }

    @Test
    void getProductBySize() {
        List<Product> productsSize=productRepository.getProductBySize("small");
        assertThat(productsSize).isNotNull();
        assertThat(productsSize.size()).isEqualTo(1);
        assertThat(productsSize.get(0).getProductDetails().getSize()).isEqualTo("small");

    }

    @Test
    void getProductByPrice() {
        List<Product> productPrice =productRepository.getProductByPrice(500.0);
        assertThat(productPrice).isNotEmpty();
        assertThat(productPrice.size()).isEqualTo(1);
        assertThat(productPrice.get(0).getProductDetails().getPrice()).isEqualTo(500.0);

    }

    @Test
    void getProductByCountryOfOrigin() {
        List<Product> productsFromCountry = productRepository.getProductByCountryOfOrigin("USA");
        assertThat(productsFromCountry).isNotNull();
        assertThat(productsFromCountry.size()).isEqualTo(1);
        assertThat(productsFromCountry.get(0).getProductDetails().getCountryOfOrigin()).isEqualTo("USA");

    }

//    @Test
    void getProductBySKU() {
        Product productSku = productRepository.getProductBySKU(sku);
        assertThat(productSku).isNotNull();
        assertThat(productSku.getSku()).isEqualTo(sku);
        assertThat(productSku.getProductDetails().getName()).isEqualTo("phone");

    }

    @Test
    void getProductByCategory() {
        List<Product> productsCategory = productRepository.getProductByCategory("Electronics");
        assertThat(productsCategory).isNotNull();
        assertThat(productsCategory.size()).isEqualTo(1);
        assertThat(productsCategory.get(0).getCategory()).isEqualTo("Electronics");
    }

    @Test
    void getProductByQuantity() {
        List<Product> productQuantity = productRepository.getProductByQuantity(10);
        assertThat(productQuantity).isNotNull();
        assertThat(productQuantity.size()).isEqualTo(1);
        assertThat(productQuantity.get(0).getQuantity()).isEqualTo(10);
    }

    @Test
    void getProductIsActive() {
    List<Product> productsIsActive =productRepository.getProductIsActive(Boolean.TRUE);
    assertThat(productsIsActive).isNotNull();
    assertThat(productsIsActive.size()).isEqualTo(1);
    assertThat(productsIsActive.get(0).getIsActive()).isEqualTo(Boolean.TRUE);

    }
}