package edu.unisbana.tyvs_tienda_ropa.application.domain.model;

import java.time.LocalDate;

public class Order {

    private Long id;
    private LocalDate orderDate;
    private Long customerId;

    public Order() {}

    public Order(Long id, LocalDate orderDate, Long customerId) {
        this.id = id;
        this.orderDate = orderDate;
        this.customerId = customerId;
    }

    public Long getId() {
        return id;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }
}
