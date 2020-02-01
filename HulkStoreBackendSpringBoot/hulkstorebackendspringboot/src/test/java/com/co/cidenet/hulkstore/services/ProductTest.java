package com.co.cidenet.hulkstore.services;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import co.com.cidenet.hulkstore.entity.Product;
import co.com.cidenet.hulkstore.repository.ProductRepository;

import co.com.cidenet.hulkstore.service.ProductServiceImplement;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


@RunWith(MockitoJUnitRunner.class)
public class ProductTest {

    @Mock
    ProductRepository productRepository;

    @InjectMocks
    ProductServiceImplement productService ;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void createProductTest() {
        Product productOne = new Product();
        productOne.setId(1L);
        productOne.setName("marvel");
        productOne.setQuantity(30);
        productOne.setPrice(20);
        productOne.setCategory("marvel");

        when(productRepository.save(any(Product.class))).thenReturn(productOne);
        Product productFinal = productService.save(productOne);
        verify(productRepository, times(1)).save(productOne);
        assertNotNull(productFinal);
    }

    @Test
    public void findProductByIdProductExistTest() {
        Product productSaved = new Product();
        productSaved.setName("Testing Product");
        productSaved.setId(1L);
        productSaved.setQuantity(30);
        productSaved.setPrice(50);
        productSaved.setCategory("Marvel");
        when(productRepository.findById(productSaved.getId())).thenReturn(Optional.of(productSaved));
        Product productTest = productService.findOneProduct(productSaved.getId());
        verify(productRepository, times(1)).findById(productSaved.getId());
        assertEquals(productSaved, productTest);
    }

    @Test
    public void findHeroByIdHeroNoExistTest() {
        Product productSaved = new Product();
        productSaved.setName("Testing Product");
        productSaved.setId(1L);
        productSaved.setQuantity(30);
        productSaved.setPrice(50);
        productSaved.setCategory("Marvel");
        when(productRepository.findById(productSaved.getId())).thenReturn(Optional.ofNullable(null));
        Product productTest = productService.findOneProduct(productSaved.getId());
        verify(productRepository, times(1)).findById(productSaved.getId());
        assertNull(productTest);
    }

    @Test
    public void findHeroByIdHeroIdNullTest() {
        Product productSaved = new Product();
        Product productTest = productService.findOneProduct(productSaved.getId());
        assertNull(productTest);
    }

    @Test
    public void findAllProduct(){
        Product productOne = new Product();
        productOne.setId(2L);
        productOne.setName("marvel");
        productOne.setQuantity(30);
        productOne.setPrice(20);
        productOne.setCategory("marvel");

        Product productTwo = new Product();
        productTwo.setId(2L);
        productTwo.setName("marvel");
        productTwo.setQuantity(30);
        productTwo.setPrice(20);
        productTwo.setCategory("marvel");

        List<Product> productList = new ArrayList<>();
        productList.add(productOne);
        productList.add(productTwo);

        when(productRepository.findAll()).thenReturn(productList);
        List<Product> productsTest = productService.findAll();
        verify(productRepository, times(1)).findAll();
        assertEquals(productList, productsTest);
    }

    @Test
    public void updateProductTest(){
        Product productOne = new Product();
        productOne.setId(2L);
        productOne.setName("marvel");
        productOne.setQuantity(30);
        productOne.setPrice(20);
        productOne.setCategory("marvel");

        Product productTwo = new Product();
        productTwo.setId(2L);
        productTwo.setName("marvel");
        productTwo.setQuantity(30);
        productTwo.setPrice(20);
        productTwo.setCategory("marvel");

        Mockito.when(productRepository.save(productOne)).thenReturn(productTwo);
        Product productTest = productService.save(productTwo);
        Assert.assertEquals(productTwo, productTest);
    }

    @Test
    public void deleteProductExistingTest() {
        Product product = new Product();
        product.setName("Test product");
        product.setId(1L);
        doNothing().when(productRepository).deleteById(product.getId());
        productService.deleteProduct(product.getId());
        verify(productRepository, times(1)).deleteById(product.getId());
    }

    @Test (expected = IllegalArgumentException.class)
    public void createProductQuantityInvalidTest(){
        Product productOne = new Product();
        productOne.setId(1L);
        productOne.setName("marvel");
        productOne.setQuantity(-1000);
        productOne.setPrice(20);
        productOne.setCategory("marvel");
        productService.save(productOne);
    }


    public void createProductPriceInvalidTest(){
        Product productTest = new Product();
        productTest.setId(1L);
        productTest.setName("Product");
        productTest.setQuantity(100);
        productTest.setPrice(-1000);
        productTest.setCategory("Marvel");

        productService.save(productTest);
    }
}

