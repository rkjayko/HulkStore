package co.com.cidenet.hulkstore.repository;
/*
Created by : Jaime Mejia
*/

import co.com.cidenet.hulkstore.entity.Product;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProductRepository extends CrudRepository<Product, Long> {
    @Query("select p from Product p where p.name like %?1%")
    List<Product> findByNameContainingIgnoreCase(String term);

}
