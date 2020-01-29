package com.co.cidenet.hulkstore;

import co.com.cidenet.hulkstore.entity.ItemOrder;
import co.com.cidenet.hulkstore.entity.Order;
import co.com.cidenet.hulkstore.entity.Product;
import co.com.cidenet.hulkstore.repository.OrderRepository;
import co.com.cidenet.hulkstore.service.OrderServiceImplement;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Date;

import static org.junit.Assert.assertEquals;


@RunWith(MockitoJUnitRunner.class)
public class OrderTest {

    private Order order;
    private Order heroDtoFinal;

    @Mock
    OrderRepository orderRepository;

    @InjectMocks
    OrderServiceImplement orderService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void createOrder() {
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
