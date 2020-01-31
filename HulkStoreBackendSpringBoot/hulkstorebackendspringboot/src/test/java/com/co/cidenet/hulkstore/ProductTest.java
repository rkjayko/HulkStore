package com.co.cidenet.hulkstore;


import static java.lang.Boolean.TRUE;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import co.com.cidenet.hulkstore.entity.Product;
import co.com.cidenet.hulkstore.repository.InterfaceProductRepository;
import co.com.cidenet.hulkstore.service.ProductServiceImplement;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
public class ProductTest {

    @Mock
    InterfaceProductRepository productRepository;

    @InjectMocks
    ProductServiceImplement productService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void findProductByIdExistTest() {
        Product product = new Product();
        product.setName("nuevo producto");
        product.setId(1L);
        Product productSaved = new Product();
        productSaved.setName("nuevo producto");
        productSaved.setId(1L);
        when(productRepository.findById(product.getId())).thenReturn(Optional.of(productSaved));
        Product productFinal = productService.findOne(product.getId());
        verify(productRepository, times(1)).findById(product.getId());
        assertNotSame(product, productFinal);
    }


    @Test
    public void findProductByIdNoExistTest() {
        Product product = new Product();
        product.setId(1L);
        when(productRepository.findById(product.getId())).thenReturn(Optional.empty());
        Product productFinal = productService.findOne(product.getId());
        verify(productRepository, times(1)).findById(product.getId());
        assertNull(productFinal);
    }

    @Test
    public void findAlltest() {
        List<Product> productsList = new ArrayList<>();
        Product product1 = new Product();
        product1.setId(1L);
        product1.setName("Testing hero 1");
        Product product2 = new Product();
        product2.setId(2L);
        product2.setName("Testing hero 2");
        productsList.add(product1);
        productsList.add(product2);
        when(productRepository.findAll()).thenReturn(productsList);
        List<Product> heroesDtoListFinal = productService.findAll();
        verify(productRepository, times(1)).findAll();
        assertEquals(TRUE,productsList.equals(heroesDtoListFinal));
    }

    @Test
    public void getQuantity() {
        Product productOne = new Product();
        productOne.setName("marvel");
        productOne.setQuantity(30);
        productOne.setPrice(20);
        productOne.setCategory("marvel");
        int quantity = 30;
        int getQuantity = productOne.getQuantity();
        assertEquals(quantity,getQuantity);
    }

}

