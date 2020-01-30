package co.com.cidenet.hulkstore.service;

import co.com.cidenet.hulkstore.entity.Product;
import org.springframework.validation.BindingResult;

import java.util.List;

public interface InterfaceProductService {

    List<Product> findAll();
    Product save(Product product);
    Product findOne(Long id);
    void deleteProduct(Long id);
    boolean validateQuantity(Product oldProduct, Product product);
    Object listErrors(BindingResult result);
}
