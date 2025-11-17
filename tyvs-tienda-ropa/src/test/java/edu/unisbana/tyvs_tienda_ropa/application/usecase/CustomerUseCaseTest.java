package edu.unisbana.tyvs_tienda_ropa.application.usecase;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import edu.unisbana.tyvs_tienda_ropa.application.domain.model.Customer;
import edu.unisbana.tyvs_tienda_ropa.application.port.out.CustomerRepositoryPort;

class CustomerUseCaseTest {

    @Mock
    private CustomerRepositoryPort customerRepository;

    @InjectMocks
    private CustomerUseCaseImpl customerUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldReturnAllCustomers() {
        List<Customer> customers = Arrays.asList(
                new Customer(1L, "Majo", "majo@example.com"),
                new Customer(2L, "Juan", "juan@example.com")
        );

        when(customerRepository.findAll()).thenReturn(customers);

        List<Customer> result = customerUseCase.getAllCustomers();

        assertEquals(2, result.size());
        verify(customerRepository, times(1)).findAll();
    }

    @Test
    void shouldCreateCustomer() {
        Customer customer = new Customer(null, "Ana", "ana@example.com");
        Customer saved = new Customer(3L, "Ana", "ana@example.com");

        when(customerRepository.save(customer)).thenReturn(saved);

        Customer result = customerUseCase.createCustomer(customer);

        assertEquals("Ana", result.getName());
        verify(customerRepository).save(customer);
    }
}