package com.co.cidenet.hulkstore.services;

import co.com.cidenet.hulkstore.entity.ItemOrder;
import co.com.cidenet.hulkstore.entity.Order;
import co.com.cidenet.hulkstore.entity.Product;
import co.com.cidenet.hulkstore.repository.OrderRepository;

import co.com.cidenet.hulkstore.service.OrderServiceImplement;
import org.junit.Before;
import org.junit.Test;
import org.mockito.*;


import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;


public class OrderTest {

    @Mock
    OrderRepository orderRepository;

    @InjectMocks
    OrderServiceImplement orderService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void createOrderTest() {

        Product productOne = new Product();
        productOne.setId(1l);
        productOne.setName("marvel");
        productOne.setQuantity(30);
        productOne.setPrice(20);
        productOne.setCategory("marvel");

        Product productTwo = new Product();
        productTwo.setId(2L);
        productTwo.setName("marvel");
        productTwo.setQuantity(30);
        productTwo.setPrice(50);
        productTwo.setCategory("marvel");

        ItemOrder itemOrder = new ItemOrder();
        ItemOrder itemOrder2 = new ItemOrder();
        itemOrder.setId(1L);
        itemOrder2.setId(2L);
        itemOrder.setProduct(productOne);
        itemOrder2.setProduct(productTwo);
        itemOrder.setQuantity(20);
        itemOrder2.setQuantity(20);
        itemOrder.setId(1L);
        itemOrder2.setId(1L);

        //crear objeto de orden
        Order orderOne = new Order();
        orderOne.setId(1L);
        Date myObj = new Date();
        orderOne.setCreateAt(myObj);
        orderOne.setDescription("se hace efecto de prueba");
        orderOne.setNote("comida");
        ArrayList<ItemOrder> items = new ArrayList<>();
        items.add(itemOrder);
        items.add(itemOrder2);
        orderOne.setItems(items);

        when(orderRepository.save(any(Order.class))).thenReturn(orderOne);
        Order orderTest = orderService.saveOrder(orderOne);
        verify(orderRepository, times(1)).save(orderOne);
        assertNotNull(orderTest);
    }

    public void findOrderByIdOrderNoExistTest() {
        Product productOne = new Product();
        productOne.setId(1l);
        productOne.setName("marvel");
        productOne.setQuantity(30);
        productOne.setPrice(20);
        productOne.setCategory("marvel");

        Product productTwo = new Product();
        productTwo.setId(2L);
        productTwo.setName("marvel");
        productTwo.setQuantity(30);
        productTwo.setPrice(50);
        productTwo.setCategory("marvel");

        ItemOrder itemOrder = new ItemOrder();
        ItemOrder itemOrder2 = new ItemOrder();
        itemOrder.setId(1L);
        itemOrder2.setId(2L);
        itemOrder.setProduct(productOne);
        itemOrder2.setProduct(productTwo);
        itemOrder.setQuantity(20);
        itemOrder2.setQuantity(20);
        itemOrder.setId(1L);
        itemOrder2.setId(1L);

        //crear objeto de orden
        Order orderOne = new Order();
        orderOne.setId(1L);
        Date myObj = new Date();
        orderOne.setCreateAt(myObj);
        orderOne.setDescription("se hace efecto de prueba");
        orderOne.setNote("comida");
        ArrayList<ItemOrder> items = new ArrayList<>();
        items.add(itemOrder);
        items.add(itemOrder2);
        orderOne.setItems(items);

        when(orderRepository.findById(orderOne.getId())).thenReturn(Optional.ofNullable(null));
        Order orderTest= orderService.findOrderById(orderOne.getId());
        verify(orderRepository, times(1)).findById(orderOne.getId());
        assertNull(orderTest);
    }

    @Test
    public void findOrderByIdOrderExistTest() {
        Product productOne = new Product();
        productOne.setId(1l);
        productOne.setName("marvel");
        productOne.setQuantity(30);
        productOne.setPrice(20);
        productOne.setCategory("marvel");

        Product productTwo = new Product();
        productTwo.setId(2L);
        productTwo.setName("marvel");
        productTwo.setQuantity(30);
        productTwo.setPrice(50);
        productTwo.setCategory("marvel");

        ItemOrder itemOrder = new ItemOrder();
        ItemOrder itemOrder2 = new ItemOrder();
        itemOrder.setId(1L);
        itemOrder2.setId(2L);
        itemOrder.setProduct(productOne);
        itemOrder2.setProduct(productTwo);
        itemOrder.setQuantity(20);
        itemOrder2.setQuantity(20);
        itemOrder.setId(1L);
        itemOrder2.setId(1L);

        //crear objeto de orden
        Order orderOne = new Order();
        orderOne.setId(1L);
        Date myObj = new Date();
        orderOne.setCreateAt(myObj);
        orderOne.setDescription("se hace efecto de prueba");
        orderOne.setNote("comida");
        ArrayList<ItemOrder> items = new ArrayList<>();
        items.add(itemOrder);
        items.add(itemOrder2);
        orderOne.setItems(items);
        when(orderRepository.findById(orderOne.getId())).thenReturn(Optional.of(orderOne));
        Order orderTest = orderService.findOrderById(orderOne.getId());
        verify(orderRepository, times(1)).findById(orderOne.getId());
        assertEquals(orderOne, orderTest);
    }

    @Test
    public void totalOrderTest() {
        //crear el resto

        Product productOne = new Product();
        productOne.setName("marvel");
        productOne.setQuantity(30);
        productOne.setPrice(20);
        productOne.setCategory("marvel");

        Product productTwo = new Product();
        productTwo.setName("marvel");
        productTwo.setQuantity(30);
        productTwo.setPrice(50);
        productTwo.setCategory("marvel");

        ItemOrder itemOrder = new ItemOrder();
        ItemOrder itemOrder2 = new ItemOrder();
        itemOrder.setProduct(productOne);
        itemOrder2.setProduct(productTwo);
        itemOrder.setQuantity(20);
        itemOrder2.setQuantity(20);
        itemOrder.setId(1L);
        itemOrder2.setId(1L);

        //crear objeto de orden
        Order orderOne = new Order();
        Date myObj = new Date();
        orderOne.setCreateAt(myObj);
        orderOne.setDescription("se hace efecto de prueba");
        orderOne.setNote("comida");
        ArrayList<ItemOrder> items = new ArrayList<>();
        items.add(itemOrder);
        items.add(itemOrder2);
        orderOne.setItems(items);
        double totalOrders = orderOne.getTotal();
        assertEquals(1400,totalOrders , 0.001);
    }


}
