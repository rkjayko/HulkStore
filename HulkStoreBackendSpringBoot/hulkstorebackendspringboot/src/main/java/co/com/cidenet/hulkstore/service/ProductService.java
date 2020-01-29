package co.com.cidenet.hulkstore.service;

import co.com.cidenet.hulkstore.entity.Product;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;


public interface ProductService {

    @NotNull Iterable<Product> findAll();
    Product save(Product product);
    Product getProduct(@Min(value = 1L, message = "Id del producto invalido") long id);
    void deleteProduct(Long id);

}
