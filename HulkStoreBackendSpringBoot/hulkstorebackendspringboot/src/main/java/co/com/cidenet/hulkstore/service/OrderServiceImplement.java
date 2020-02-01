package co.com.cidenet.hulkstore.service;

import co.com.cidenet.hulkstore.entity.ItemOrder;
import co.com.cidenet.hulkstore.entity.Order;
import co.com.cidenet.hulkstore.entity.Product;
import co.com.cidenet.hulkstore.repository.OrderRepository;
import co.com.cidenet.hulkstore.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImplement implements InterfaceOrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private InterfaceProductService productService;

    public OrderServiceImplement(OrderRepository orderRepository, ProductRepository productRepository, InterfaceProductService productService) {
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
        this.productService = productService;
    }

    @Override
    @Transactional(readOnly = true)
    public Order findOrderById(Long id) {
        return orderRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public Order saveOrder(Order order) {
        return orderRepository.save(order);
    }

    @Override
    @Transactional
    public void deleteOrderById(Long id) {
        orderRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Product> findProductByName(String term) {
        return productRepository.findByNameContainingIgnoreCase(term);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Order> findAll() {
        return (List<Order>) orderRepository.findAll();
    }

    @Override
    public void updateQuantityProduct(Order order) {
        for (ItemOrder items : order.getItems()){
            Product oldProduct = productService.findOneProduct(items.getProduct().getId());
            Integer getOldProductQuantity = oldProduct.getQuantity();
            Integer Amount =items.getQuantity();
            int updateQuantity = getOldProductQuantity - Amount;
            oldProduct.setQuantity(updateQuantity);
            productService.save(oldProduct);
        }
    }

    public Object listErrors(BindingResult result) {
        return result.getFieldErrors()
                .stream()
                .map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage())
                .collect(Collectors.toList());
    }
}
