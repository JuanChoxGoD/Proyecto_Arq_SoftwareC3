package edu.unisbana.tyvs_tienda_ropa.application.delivery.rest;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.unisbana.tyvs_tienda_ropa.application.domain.model.Customer;
import edu.unisbana.tyvs_tienda_ropa.application.domain.rq.CreateCustomerRequest;
import edu.unisbana.tyvs_tienda_ropa.application.port.in.CustomerUseCase;


@RestController
@RequestMapping("/api/v1/customers")
public class CustomerController {

    private final CustomerUseCase customerUseCase;

    public CustomerController(CustomerUseCase customerUseCase) {
        this.customerUseCase = customerUseCase;
    }

@PostMapping
public ResponseEntity<Customer> createCustomer(@RequestBody CreateCustomerRequest request) {

    Customer customer = new Customer(
        null,
        request.getName(),
        request.getEmail()
    );

    Customer created = customerUseCase.createCustomer(customer);

    return ResponseEntity.status(201).body(created);
}

    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomer(@PathVariable Long id) {
        Optional<Customer> customer = customerUseCase.getCustomerById(id);
        return customer.map(ResponseEntity::ok)
                       .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<Customer>> getAllCustomers() {
        return ResponseEntity.ok(customerUseCase.getAllCustomers());
    }
}