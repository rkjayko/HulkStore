package com.co.cidenet.hulkstore.services;

import co.com.cidenet.hulkstore.entity.ItemOrder;
import co.com.cidenet.hulkstore.entity.Order;
import co.com.cidenet.hulkstore.entity.Product;
import co.com.cidenet.hulkstore.repository.OrderRepository;

import co.com.cidenet.hulkstore.service.OrderServiceImplement;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Date;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;


public class OrderTest {

    private Order order;

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

        Order orderTwo = new Order();
        Date myObj1 = new Date();
        orderTwo.setCreateAt(myObj1);
        orderTwo.setDescription("se hace efecto de prueba");
        orderTwo.setNote("comida");
        ArrayList<ItemOrder> itemsTwo = new ArrayList<>();
        itemsTwo.add(itemOrder);
        itemsTwo.add(itemOrder2);
        orderTwo.setItems(itemsTwo);

        System.out.print(orderOne.toString());
        assertThat(orderOne.toString(), equalTo(orderTwo.toString()));
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
