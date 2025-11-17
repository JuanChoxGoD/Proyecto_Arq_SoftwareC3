package edu.unisbana.tyvs_tienda_ropa.application.port.out;

import java.util.List;
import java.util.Optional;

import edu.unisbana.tyvs_tienda_ropa.application.domain.model.Order;


public interface OrderRepositoryPort {

    List<Order> findAll();

    Optional<Order> findById(Long id);

    Order save(Order order);

    void delete(Long id);
}
