package co.com.cidenet.hulkstore.service;
/*
Created by : Jaime Mejia
*/

import co.com.cidenet.hulkstore.entity.Product;
import co.com.cidenet.hulkstore.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImplement implements InterfaceProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Product> findAll() {
        return (List<Product>) productRepository.findAll();
    }

    @Override
    @Transactional
    public Product save(Product product) {
        if(product.getQuantity() < 0){
            throw new IllegalArgumentException("La cantidad es negativa");
        }
        return productRepository.save(product);
    }

    @Override
    @Transactional(readOnly = true)
    public Product findOneProduct(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public boolean validateQuantity(Product oldProduct, Product product) {
        if (oldProduct.getQuantity() + product.getQuantity() >= 0) {
            oldProduct.setName(product.getName());
            oldProduct.setPrice(product.getPrice());
            oldProduct.setQuantity(oldProduct.getQuantity() + product.getQuantity());
            oldProduct.setCategory(product.getCategory());
            return true;
        }
        else{
            return false;
        }
    }

    public Object listErrors(BindingResult result) {
        return result.getFieldErrors()
                .stream()
                .map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage())
                .collect(Collectors.toList());
    }
}