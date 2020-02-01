package com.co.cidenet.hulkstore.services;

import co.com.cidenet.hulkstore.entity.ItemOrder;
import co.com.cidenet.hulkstore.entity.Product;
import org.junit.Test;


import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class ItemOrderTest {

    @Test
    public void createItemTest() {
        Product productOne = new Product();
        productOne.setName("marvel");
        productOne.setQuantity(30);
        productOne.setPrice(20);
        productOne.setCategory("marvel");
        ItemOrder itemOrder = new ItemOrder();
        itemOrder.setProduct(productOne);
        itemOrder.setQuantity(20);
        itemOrder.setId(1L);
        assertThat(itemOrder.toString(), equalTo("ItemOrder{id=1, quantity=20, product=Product{id=null, name='marvel', price=20, quantity=30, category='marvel'}}"));
    }

    @Test
    public void getImport() {
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
