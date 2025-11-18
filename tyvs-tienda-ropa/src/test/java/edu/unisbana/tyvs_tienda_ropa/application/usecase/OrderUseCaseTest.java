package edu.unisbana.tyvs_tienda_ropa.application.usecase;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import edu.unisbana.tyvs_tienda_ropa.application.domain.model.Order;
import edu.unisbana.tyvs_tienda_ropa.application.port.out.OrderRepositoryPort;

class OrderUseCaseTest {

    @Mock
    private OrderRepositoryPort orderRepository;

    @InjectMocks
    private OrderUseCaseImpl orderUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldReturnAllOrders() {
        List<Order> orders = Arrays.asList(
                new Order(1L, LocalDate.now(), 101L),
                new Order(2L, LocalDate.now(), 102L)
        );

        when(orderRepository.findAll()).thenReturn(orders);

        List<Order> result = orderUseCase.getAllOrders();

        assertEquals(2, result.size());
        verify(orderRepository, times(1)).findAll();
    }

    @Test
    void shouldSaveOrder() {
        Order order = new Order(null, LocalDate.now(), 500L);
        Order saved = new Order(3L, order.getOrderDate(), order.getCustomerId());

        when(orderRepository.save(order)).thenReturn(saved);

        Order result = orderUseCase.createOrder(order);

        assertNotNull(result.getId());
        verify(orderRepository).save(order);
    }

    //  NUEVO TEST NEGATIVO 
    @Test
    void shouldThrowExceptionWhenCustomerIdIsNull() {
        Order invalidOrder = new Order(null, LocalDate.now(), null);

        assertThrows(IllegalArgumentException.class, () ->
                orderUseCase.createOrder(invalidOrder)
        );

        verify(orderRepository, never()).save(any());
    }
}
