package edu.unisbana.tyvs_tienda_ropa.infraestructure.persistence.adapter;

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

import edu.unisbana.tyvs_tienda_ropa.application.domain.model.Customer;
import edu.unisbana.tyvs_tienda_ropa.application.infraestructure.persistence.adapter.CustomerRepositoryAdapter;
import edu.unisbana.tyvs_tienda_ropa.application.infraestructure.persistence.entity.CustomerEntity;
import edu.unisbana.tyvs_tienda_ropa.application.infraestructure.persistence.jpa.JpaCustomerRepository;

class CustomerRepositoryAdapterTest {

    @Mock
    private JpaCustomerRepository jpaRepository;

    @InjectMocks
    private CustomerRepositoryAdapter adapter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findAll_shouldReturnDomainCustomers() {
        CustomerEntity e1 = new CustomerEntity();
        e1.setId(1L); e1.setName("Majo"); e1.setEmail("majo@example.com");
        CustomerEntity e2 = new CustomerEntity();
        e2.setId(2L); e2.setName("Juan"); e2.setEmail("juan@example.com");

        when(jpaRepository.findAll()).thenReturn(List.of(e1, e2));

        List<Customer> result = adapter.findAll();

        assertEquals(2, result.size());
        assertEquals("Majo", result.get(0).getName());
        verify(jpaRepository).findAll();
    }

    @Test
    void save_shouldReturnSavedCustomer() {
        Customer domain = new Customer(null, "Ana", "ana@example.com");
        CustomerEntity saved = new CustomerEntity();
        saved.setId(10L); saved.setName("Ana"); saved.setEmail("ana@example.com");

        when(jpaRepository.save(any(CustomerEntity.class))).thenReturn(saved);

        Customer result = adapter.save(domain);

        assertEquals(10L, result.getId());
        assertEquals("Ana", result.getName());
        verify(jpaRepository).save(any(CustomerEntity.class));
    }

@Test
void findById_shouldReturnOptionalDomain() {
    CustomerEntity e = new CustomerEntity();
    e.setId(5L); 
    e.setName("Pedro"); 
    e.setEmail("pedro@example.com");

    when(jpaRepository.findById(5L)).thenReturn(Optional.of(e));

    Optional<Customer> res = adapter.findById(5L);

    assertTrue(res.isPresent());
    assertEquals("Pedro", res.get().getName());
    verify(jpaRepository).findById(5L);
}

}
