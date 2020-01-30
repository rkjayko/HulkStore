package co.com.cidenet.hulkstore.service;
/*
Created by : Jaime Mejia
*/

import co.com.cidenet.hulkstore.entity.Product;
import co.com.cidenet.hulkstore.repository.InterfaceProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductServiceImplement implements InterfaceProductService {

    @Autowired
    private InterfaceProductRepository productDao;

    @Override
    @Transactional(readOnly = true)
    public List<Product> findAll() {
        return (List<Product>) productDao.findAll();
    }

    @Override
    @Transactional
    public Product save(Product product) {
        return productDao.save(product);
    }

    @Override
    @Transactional(readOnly = true)
    public Product findOne(Long id) {
        return productDao.findById(id).orElse(null);
    }

    @Override
    public void deleteProduct(Long id) {
        productDao.deleteById(id);
    }
}
