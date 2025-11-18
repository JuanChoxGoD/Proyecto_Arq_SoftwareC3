package edu.unisbana.tyvs_tienda_ropa.application.usecase;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import edu.unisbana.tyvs_tienda_ropa.application.domain.model.Customer;
import edu.unisbana.tyvs_tienda_ropa.application.port.in.CustomerUseCase;
import edu.unisbana.tyvs_tienda_ropa.application.port.out.CustomerRepositoryPort;

@Service 
public class CustomerUseCaseImpl implements CustomerUseCase {

    private final CustomerRepositoryPort customerRepository;

    public CustomerUseCaseImpl(CustomerRepositoryPort customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public Customer createCustomer(Customer customer) {

        //  Validación para que pase test negativo
        if (customer.getName() == null || customer.getName().isBlank()) {
            throw new IllegalArgumentException("El nombre no puede estar vacío");
        }

        return customerRepository.save(customer);
    }

    @Override
    public Optional<Customer> getCustomerById(Long id) {
        return customerRepository.findById(id);
    }

    @Override
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }
}
