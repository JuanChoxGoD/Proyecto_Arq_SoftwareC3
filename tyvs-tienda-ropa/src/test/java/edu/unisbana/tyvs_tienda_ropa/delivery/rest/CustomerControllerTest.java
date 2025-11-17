package edu.unisbana.tyvs_tienda_ropa.delivery.rest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import edu.unisbana.tyvs_tienda_ropa.application.delivery.rest.CustomerController;
import edu.unisbana.tyvs_tienda_ropa.application.domain.model.Customer;
import edu.unisbana.tyvs_tienda_ropa.application.domain.rq.CreateCustomerRequest;
import edu.unisbana.tyvs_tienda_ropa.application.port.in.CustomerUseCase;

class CustomerControllerTest {

    @Mock
    private CustomerUseCase customerUseCase;

    @InjectMocks
    private CustomerController controller;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldReturnListOfCustomers() {
        when(customerUseCase.getAllCustomers())
                .thenReturn(List.of(new Customer(1L, "Majo", "majo@example.com")));

        ResponseEntity<List<Customer>> response = controller.getAllCustomers();

        assertEquals(200, response.getStatusCode().value());
        assertNotNull(response.getBody());
        assertEquals(1, response.getBody().size()); //Potential null pointer access: The method getBody() may return nullJava(536871831)
        verify(customerUseCase).getAllCustomers();
    }

    @Test
    void shouldCreateCustomer() {
        CreateCustomerRequest request =  new CreateCustomerRequest("Majo", "majo@example.com", "Calle 123"); 
        Customer created = new Customer(1L, "Majo", "majo@example.com");

        when(customerUseCase.createCustomer(any(Customer.class))).thenReturn(created);

        ResponseEntity<Customer> response = controller.createCustomer(request);

        assertEquals(201, response.getStatusCode().value());
        assertEquals("Majo", response.getBody().getName());//Potential null pointer access: The method getBody() may return nullJava(536871831)
        verify(customerUseCase).createCustomer(any(Customer.class));
    }

    @Test
    void shouldGetCustomerById() {
        Customer customer = new Customer(1L, "Majo", "majo@example.com");

        when(customerUseCase.getCustomerById(1L)).thenReturn(Optional.of(customer));

        ResponseEntity<Customer> response = controller.getCustomer(1L);

        assertEquals(200, response.getStatusCode().value());
        assertEquals("Majo", response.getBody().getName()); //Potential null pointer access: The method getBody() may return nullJava(536871831)
        verify(customerUseCase).getCustomerById(1L);
    }
}
