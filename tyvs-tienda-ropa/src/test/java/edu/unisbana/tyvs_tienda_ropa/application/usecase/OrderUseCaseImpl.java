package edu.unisbana.tyvs_tienda_ropa.application.usecase;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import edu.unisbana.tyvs_tienda_ropa.application.domain.model.Order;
import edu.unisbana.tyvs_tienda_ropa.application.port.in.OrderUseCase;
import edu.unisbana.tyvs_tienda_ropa.application.port.out.OrderRepositoryPort;

@Service
public class OrderUseCaseImpl implements OrderUseCase {

    private final OrderRepositoryPort orderRepository;

    public OrderUseCaseImpl(OrderRepositoryPort orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public Order createOrder(Order order) {

        //  VALIDACIÓN AÑADIDA 
        if (order.getCustomerId() == null) {
            throw new IllegalArgumentException("customerId cannot be null");
        }

        return orderRepository.save(order);
    }

    @Override
    public Optional<Order> getOrderById(String id) {
        Long longId = Long.valueOf(id); // Convertimos String -> Long
        return orderRepository.findById(longId);
    }

    @Override
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @Override
    public Order updateOrder(String id, Order order) {
        Long longId = Long.valueOf(id);
        order.setId(longId);
        return orderRepository.save(order);
    }

    @Override
    public void deleteOrder(String id) {
        Long longId = Long.valueOf(id);
        orderRepository.delete(longId);
    }

}
