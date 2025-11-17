package edu.unisbana.tyvs_tienda_ropa.application.port.in;

import java.util.List;
import java.util.Optional;

import edu.unisbana.tyvs_tienda_ropa.application.domain.model.Order;

public interface OrderUseCase {
    Order createOrder(Order order);
    Optional<Order> getOrderById(String id);
    List<Order> getAllOrders();
    Order updateOrder(String id, Order order);
    void deleteOrder(String id);
}
