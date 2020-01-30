package co.com.cidenet.hulkstore.repository;

import co.com.cidenet.hulkstore.entity.Order;
import org.springframework.data.repository.CrudRepository;

public interface InterfaceOrderRepository extends CrudRepository<Order, Long> {
}
