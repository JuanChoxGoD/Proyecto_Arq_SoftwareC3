package edu.unisbana.tyvs_tienda_ropa.application.port.in;

import java.util.List;
import java.util.Optional;

import edu.unisbana.tyvs_tienda_ropa.application.domain.model.Customer;

public interface CustomerUseCase {

    Customer createCustomer(Customer customer);

    Optional<Customer> getCustomerById(Long id);

    List<Customer> getAllCustomers();
}
