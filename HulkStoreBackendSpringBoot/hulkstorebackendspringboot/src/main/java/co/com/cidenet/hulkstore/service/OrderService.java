package co.com.cidenet.hulkstore.service;

import co.com.cidenet.hulkstore.entity.Order;
import co.com.cidenet.hulkstore.entity.Product;
import javax.validation.constraints.NotNull;

import java.util.List;

public interface OrderService {

    Order findOrderById(Long id);

    Order saveOrder(Order order);

    void deleteOrderById(Long id);

    Iterable<Product> findProductByName(String term);

    @NotNull Iterable<Order> findAll();
}
