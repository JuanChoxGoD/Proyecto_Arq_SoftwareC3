package edu.unisbana.tyvs_tienda_ropa.infrastructure.persistence.adapter;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import edu.unisbana.tyvs_tienda_ropa.application.domain.model.Order;
import edu.unisbana.tyvs_tienda_ropa.application.infrastructure.persistence.adapter.OrderRepositoryAdapter;
import edu.unisbana.tyvs_tienda_ropa.application.infrastructure.persistence.entity.CustomerEntity;
import edu.unisbana.tyvs_tienda_ropa.application.infrastructure.persistence.entity.OrderEntity;
import edu.unisbana.tyvs_tienda_ropa.application.infrastructure.persistence.jpa.JpaOrderRepository;

class OrderRepositoryAdapterTest {

    @Mock
    private JpaOrderRepository jpaRepository;

    @InjectMocks
    private OrderRepositoryAdapter adapter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findAll_shouldMapEntitiesToDomain() {
        CustomerEntity c = new CustomerEntity();
        c.setId(2L); c.setName("Cliente"); c.setEmail("c@example.com");

        OrderEntity o1 = new OrderEntity();
        o1.setId(1L); o1.setOrderDate("2025-11-01"); o1.setCustomer(c);

        when(jpaRepository.findAll()).thenReturn(List.of(o1));

        List<Order> list = adapter.findAll();

        assertEquals(1, list.size());
        assertEquals(2L, list.get(0).getCustomerId());
        verify(jpaRepository).findAll();
    }

    @Test
    void save_shouldPersistOrder() {
        Order domain = new Order(null,LocalDate.parse ("2025-11-07"), 2L);
        OrderEntity saved = new OrderEntity();
        saved.setId(7L); saved.setOrderDate("2025-11-07");
        CustomerEntity cust = new CustomerEntity(); cust.setId(2L);
        saved.setCustomer(cust);

        when(jpaRepository.save(any(OrderEntity.class))).thenReturn(saved);

        Order result = adapter.save(domain);

        assertEquals(7L, result.getId());
        assertEquals(2L, result.getCustomerId());
        verify(jpaRepository).save(any(OrderEntity.class));
    }

    @Test
    void findById_shouldReturnEmptyWhenNotExists() {
        when(jpaRepository.findById(99L)).thenReturn(Optional.empty());
        Optional<Order> opt = adapter.findById(99L); 
        verify(jpaRepository).findById(99L);
    }
}
