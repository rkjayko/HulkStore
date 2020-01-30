package co.com.cidenet.hulkstore.service;

import co.com.cidenet.hulkstore.entity.ItemOrder;
import co.com.cidenet.hulkstore.entity.Order;
import co.com.cidenet.hulkstore.entity.Product;
import co.com.cidenet.hulkstore.repository.InterfaceOrderRepository;
import co.com.cidenet.hulkstore.repository.InterfaceProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImplement implements InterfaceOrderService {
    @Autowired
    private InterfaceOrderRepository orderDao;

    @Autowired
    private InterfaceProductRepository productDao;

    @Autowired
    private InterfaceProductService productService;

    public OrderServiceImplement(InterfaceOrderRepository orderDao, InterfaceProductRepository productDao, InterfaceProductService productService) {
        this.orderDao = orderDao;
        this.productDao = productDao;
        this.productService = productService;
    }

    @Override
    @Transactional(readOnly = true)
    public Order findOrderById(Long id) {
        return orderDao.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public Order saveOrder(Order order) {
        return orderDao.save(order);
    }

    @Override
    @Transactional
    public void deleteOrderById(Long id) {
        orderDao.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Product> findProductByName(String term) {
        return productDao.findByNameContainingIgnoreCase(term);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Order> findAll() {
        return (List<Order>) orderDao.findAll();
    }

    @Override
    public void updateQuantityProduct(Order order) {
        for (ItemOrder items : order.getItems()){
            Product oldProduct = productService.findOne(items.getProduct().getId());
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
