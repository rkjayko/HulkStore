package com.co.cidenet.hulkstore;

import co.com.cidenet.hulkstore.entity.ItemOrder;
import co.com.cidenet.hulkstore.entity.Product;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;

public class ItemOrderTest {

    @Test
    public void getImport() {
        //crear objeto item de orden
        Product productOne = new Product();
        productOne.setName("marvel");
        productOne.setQuantity(30);
        productOne.setPrice(20);
        productOne.setCategory("marvel");
        ItemOrder itemOrder = new ItemOrder();
        itemOrder.setProduct(productOne);
        itemOrder.setQuantity(20);
        itemOrder.setId(1L);
        int amount = itemOrder.getImport();
        assertEquals(400,amount);
    }
}
