package edu.unisbana.tyvs_tienda_ropa.delivery.rest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import edu.unisbana.tyvs_tienda_ropa.application.delivery.rest.OrderController;
import edu.unisbana.tyvs_tienda_ropa.application.domain.model.Order;
import edu.unisbana.tyvs_tienda_ropa.application.port.in.OrderUseCase;

class OrderControllerTest {

    @Mock
    private OrderUseCase orderUseCase;

    @InjectMocks
    private OrderController controller;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldReturnListOfOrders() {
        when(orderUseCase.getAllOrders())
                .thenReturn(List.of(new Order(1L, LocalDate.now(), 10L)));

        ResponseEntity<List<Order>> response = controller.getAllOrders();

        assertEquals(200, response.getStatusCode().value());
        assertEquals(1, response.getBody().size());
        verify(orderUseCase).getAllOrders();
    }

    @Test
    void shouldCreateOrder() {
        Order order = new Order(1L, LocalDate.now(), 10L);

        when(orderUseCase.createOrder(order)).thenReturn(order);

        ResponseEntity<Order> response = controller.createOrder(order);

        assertEquals(201, response.getStatusCode().value());
        assertEquals(1L, response.getBody().getId());
        verify(orderUseCase).createOrder(order);
    }
}
