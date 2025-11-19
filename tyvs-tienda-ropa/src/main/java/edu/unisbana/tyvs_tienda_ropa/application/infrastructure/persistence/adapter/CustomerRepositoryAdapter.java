// src/main/java/edu/unisabana/tyvs_tienda_ropa/infrastructure/persistence/adapter/CustomerRepositoryAdapter.java
package edu.unisbana.tyvs_tienda_ropa.application.infrastructure.persistence.adapter;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import edu.unisbana.tyvs_tienda_ropa.application.domain.model.Customer;
import edu.unisbana.tyvs_tienda_ropa.application.infrastructure.persistence.entity.CustomerEntity;
import edu.unisbana.tyvs_tienda_ropa.application.infrastructure.persistence.jpa.JpaCustomerRepository;
import edu.unisbana.tyvs_tienda_ropa.application.port.out.CustomerRepositoryPort;

@Repository
public class CustomerRepositoryAdapter implements CustomerRepositoryPort {

    private final JpaCustomerRepository jpaRepository;

    public CustomerRepositoryAdapter(JpaCustomerRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public List<Customer> findAll() {
        return jpaRepository.findAll().stream()
                .map(e -> new Customer(
                        e.getId(),
                        e.getName(),
                        e.getEmail()
                ))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Customer> findById(Long id) {
        java.util.Objects.requireNonNull(id, "id must not be null");
        return jpaRepository.findById(id)
                .map(e -> new Customer(
                        e.getId(),
                        e.getName(),
                        e.getEmail()
                ));
    }

    @Override
    public Customer save(Customer customer) {
        CustomerEntity entity = new CustomerEntity();
        entity.setName(customer.getName());
        entity.setEmail(customer.getEmail());

        CustomerEntity saved = jpaRepository.save(entity);

        return new Customer(
                saved.getId(),
                saved.getName(),
                saved.getEmail()
        );
    }
    @Override
    public void delete(Long id) {
        java.util.Objects.requireNonNull(id, "id must not be null");
        jpaRepository.deleteById(id);
    }
}
