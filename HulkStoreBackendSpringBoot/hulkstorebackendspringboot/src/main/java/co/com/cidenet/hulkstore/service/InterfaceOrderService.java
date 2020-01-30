package co.com.cidenet.hulkstore.service;

import co.com.cidenet.hulkstore.entity.Order;
import co.com.cidenet.hulkstore.entity.Product;
import org.springframework.validation.BindingResult;

import java.util.List;

public interface InterfaceOrderService {

    Order findOrderById(Long id);
    Order saveOrder(Order order);
    void deleteOrderById(Long id);
    List<Product> findProductByName(String term);
    List<Order> findAll();
    void updateQuantityProduct(Order order);
    Object listErrors(BindingResult result);
}
