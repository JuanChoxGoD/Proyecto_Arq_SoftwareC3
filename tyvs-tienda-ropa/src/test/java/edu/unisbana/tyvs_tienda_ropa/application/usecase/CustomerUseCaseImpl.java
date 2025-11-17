package edu.unisbana.tyvs_tienda_ropa.application.usecase;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import edu.unisbana.tyvs_tienda_ropa.application.domain.model.Customer;
import edu.unisbana.tyvs_tienda_ropa.application.port.in.CustomerUseCase;
import edu.unisbana.tyvs_tienda_ropa.application.port.out.CustomerRepositoryPort;

@Service  // ⬅⬅⬅ NECESARIO para que Spring cree este bean
public class CustomerUseCaseImpl implements CustomerUseCase {

    private final CustomerRepositoryPort customerRepository;

    public CustomerUseCaseImpl(CustomerRepositoryPort customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public Customer createCustomer(Customer customer) {
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
