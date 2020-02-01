package co.com.cidenet.hulkstore.repository;

import co.com.cidenet.hulkstore.entity.Order;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<Order, Long> {
}
