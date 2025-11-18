package edu.unisbana.tyvs_tienda_ropa.delivery.rest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

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

    @Test
    void shouldReturnOrderById() {
        Order order = new Order(1L, LocalDate.now(), 10L);
        when(orderUseCase.getOrderById("1")).thenReturn(Optional.of(order));

        ResponseEntity<Order> response = controller.getOrderById("1");

        assertEquals(200, response.getStatusCode().value());
        assertEquals(1L, response.getBody().getId());
        verify(orderUseCase).getOrderById("1");
    }

    @Test
    void shouldReturn404IfOrderNotFound() {
        when(orderUseCase.getOrderById("999")).thenReturn(Optional.empty());

        ResponseEntity<Order> response = controller.getOrderById("999");

        assertEquals(404, response.getStatusCode().value());
        assertNull(response.getBody());
        verify(orderUseCase).getOrderById("999");
    }

    @Test
    void shouldUpdateOrder() {
        Order order = new Order(1L, LocalDate.now(), 10L);
        when(orderUseCase.updateOrder("1", order)).thenReturn(order);

        ResponseEntity<Order> response = controller.updateOrder("1", order);

        assertEquals(200, response.getStatusCode().value());
        assertEquals(1L, response.getBody().getId());
        verify(orderUseCase).updateOrder("1", order);
    }

    @Test
    void shouldDeleteOrder() {
        doNothing().when(orderUseCase).deleteOrder("1");

        ResponseEntity<Void> response = controller.deleteOrder("1");

        assertEquals(204, response.getStatusCode().value());
        verify(orderUseCase).deleteOrder("1");
    }
}
