package edu.unisbana.tyvs_tienda_ropa.application.infraestructure.persistence.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.unisbana.tyvs_tienda_ropa.application.infraestructure.persistence.entity.CustomerEntity;

public interface JpaCustomerRepository extends JpaRepository<CustomerEntity, Long> {
}
