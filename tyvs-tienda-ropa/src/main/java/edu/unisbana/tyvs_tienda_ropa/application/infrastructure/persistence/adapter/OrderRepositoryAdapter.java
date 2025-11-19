package edu.unisbana.tyvs_tienda_ropa.application.infrastructure.persistence.adapter;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import edu.unisbana.tyvs_tienda_ropa.application.domain.model.Order;
import edu.unisbana.tyvs_tienda_ropa.application.infrastructure.persistence.entity.CustomerEntity;
import edu.unisbana.tyvs_tienda_ropa.application.infrastructure.persistence.entity.OrderEntity;
import edu.unisbana.tyvs_tienda_ropa.application.infrastructure.persistence.jpa.JpaOrderRepository;
import edu.unisbana.tyvs_tienda_ropa.application.port.out.OrderRepositoryPort;


@Repository
public class OrderRepositoryAdapter implements OrderRepositoryPort {

    private final JpaOrderRepository jpaRepository;

    public OrderRepositoryAdapter(JpaOrderRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public List<Order> findAll() {
        return jpaRepository.findAll().stream()
                .map(e -> new Order(
                        e.getId(),
                        LocalDate.parse(e.getOrderDate()), 
                        e.getCustomer() != null ? e.getCustomer().getId() : null
                ))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Order> findById(Long id) {
        if (id == null) {
            return Optional.empty();
        }
        return jpaRepository.findById(id)
                .map(e -> new Order(
                        e.getId(),
                        LocalDate.parse(e.getOrderDate()), 
                        e.getCustomer() != null ? e.getCustomer().getId() : null
                ));
    }

    @Override
    public Order save(Order order) {
        OrderEntity entity = new OrderEntity();

        // LocalDate  String
        entity.setOrderDate(order.getOrderDate().toString());

        if (order.getCustomerId() != null) {
            CustomerEntity customer = new CustomerEntity();
            customer.setId(order.getCustomerId());
            entity.setCustomer(customer);
        }

        OrderEntity saved = jpaRepository.save(entity);

        return new Order(
                saved.getId(),
                LocalDate.parse(saved.getOrderDate()), 
                saved.getCustomer() != null ? saved.getCustomer().getId() : null
        );
    }

    @Override
    public void delete(Long id) {
        if (id == null) {
            return;
        }
        jpaRepository.deleteById(id);
    }
}