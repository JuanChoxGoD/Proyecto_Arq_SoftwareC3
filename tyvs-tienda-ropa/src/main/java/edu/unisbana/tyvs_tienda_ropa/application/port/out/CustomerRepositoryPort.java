package edu.unisbana.tyvs_tienda_ropa.application.port.out;

import java.util.List;
import java.util.Optional;

import edu.unisbana.tyvs_tienda_ropa.application.domain.model.Customer;

public interface CustomerRepositoryPort {

    Optional<Customer> findById( Long id);

    List<Customer> findAll();

    Customer save(Customer customer);

    void delete( Long id);
}
