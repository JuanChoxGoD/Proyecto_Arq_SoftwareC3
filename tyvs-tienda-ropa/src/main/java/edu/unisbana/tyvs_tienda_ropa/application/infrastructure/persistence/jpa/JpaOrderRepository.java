package edu.unisbana.tyvs_tienda_ropa.application.infrastructure.persistence.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.unisbana.tyvs_tienda_ropa.application.infrastructure.persistence.entity.OrderEntity;

public interface JpaOrderRepository extends JpaRepository<OrderEntity, Long> {
}
