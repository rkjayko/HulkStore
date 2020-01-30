package co.com.cidenet.hulkstore.service;

import co.com.cidenet.hulkstore.entity.Order;
import co.com.cidenet.hulkstore.entity.Product;

import java.util.List;

public interface InterfaceOrderService {

    Order findOrderById(Long id);

    Order saveOrder(Order order);

    void deleteOrderById(Long id);

    List<Product> findProductByName(String term);

    List<Order> findAll();
}
