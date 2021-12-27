package com.geekbrains.backend.test.miniMarket;

import com.geekbrains.backend.test.miniMarket.model.CategoryResponse;
import com.geekbrains.backend.test.miniMarket.model.Product;
import com.geekbrains.backend.test.miniMarket.model.ProductResponse;
import com.geekbrains.backend.test.miniMarket.model.ProductsResponse;
import org.junit.jupiter.api.*;

import java.io.IOException;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class MiniMarketServiceTest {

    private final MiniMarketService miniMarketService = new MiniMarketService();
    private static Long PRODUCT_ID;

    @Test
    @Order(1)
    void getUnexistingProductTest() throws IOException {
        ProductResponse expected = new ProductResponse();
        expected.setProduct(null);
        ProductResponse result = miniMarketService.getProduct(-1L);
        Assertions.assertEquals(expected.getProduct(), result.getProduct());
    }

    @Test
    @Order(2)
    void createProductTest() throws IOException {
        Product product = new Product();
        product.setId(null);
        product.setTitle("Potato");
        product.setPrice(100);
        product.setCategoryTitle("Food");
        ProductResponse result = miniMarketService.createProduct(product);
        Product actualProduct = result.getProduct();
        Assertions.assertEquals(product.getTitle(), actualProduct.getTitle());
        Assertions.assertEquals(product.getPrice(), actualProduct.getPrice());
        Assertions.assertEquals(product.getCategoryTitle(), actualProduct.getCategoryTitle());
        Assertions.assertNotNull(actualProduct.getId());
        PRODUCT_ID = actualProduct.getId();
    }

    @Test
    @Order(3)
    void updateProductTest() throws IOException {
        Product product = new Product();
        product.setId(PRODUCT_ID);
        product.setTitle("Potato");
        product.setPrice(200);
        product.setCategoryTitle("Food");
        boolean result = miniMarketService.updateProduct(product);
        Assertions.assertTrue(result);
    }

    @Test
    @Order(4)
    void getProductTest() throws IOException {
        Product expected = new Product();
        expected.setId(PRODUCT_ID);
        expected.setTitle("Potato");
        expected.setPrice(200);
        expected.setCategoryTitle("Food");
        ProductResponse result = miniMarketService.getProduct(PRODUCT_ID);
        Product actualProduct = result.getProduct();
        Assertions.assertEquals(expected.getTitle(), actualProduct.getTitle());
        Assertions.assertEquals(expected.getPrice(), actualProduct.getPrice());
        Assertions.assertEquals(expected.getCategoryTitle(), actualProduct.getCategoryTitle());
        Assertions.assertEquals(expected.getId(), actualProduct.getId());
    }

    @Test
    @Order(5)
    void deleteProductTest() throws IOException {
        boolean response = miniMarketService.deleteProduct(PRODUCT_ID);
        Assertions.assertTrue(response);
    }

    @Test
    @Order(6)
    void getProductsTest() throws IOException {
        ProductsResponse result = miniMarketService.getProducts();
        Assertions.assertNotNull(result.getProducts());
    }

    @Test
    @Order(7)
    void getUnexistingCategoryTest() throws IOException {
        CategoryResponse expected = new CategoryResponse();
        expected.setCategory(null);
        CategoryResponse result = miniMarketService.getCategory(-1L);
        Assertions.assertEquals(expected.getCategory(), result.getCategory());
    }

    @Test
    @Order(8)
    void getCategoryTest() throws IOException {
        CategoryResponse result = miniMarketService.getCategory(1L);
        Assertions.assertNotNull(result.getCategory());
    }
}

