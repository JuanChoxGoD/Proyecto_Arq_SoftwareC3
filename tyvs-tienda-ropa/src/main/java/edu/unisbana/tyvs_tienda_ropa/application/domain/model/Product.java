// src/main/java/edu/unisabana/tyvs_tienda_ropa/domain/model/Product.java
package edu.unisbana.tyvs_tienda_ropa.application.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * Entidad de Dominio (Pura): Contiene atributos y lógica de negocio del Producto.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    private Long id;
    private String name;
    private String description;
    private double price;
    private int stock;
    private String size;
    private String color;

    // Constructor usado en tests (ID como String)
    public Product(String id, String name, double price, int stock) {
        this.id = (id != null) ? Long.valueOf(id) : null;
        this.name = name;
        this.price = price;
        this.stock = stock;

        this.description = null;
        this.size = null;
        this.color = null;
    }

    // Constructor usado en tests (ID como Long)
    public Product(Long id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;

        this.description = null;
        this.stock = 0;
        this.size = null;
        this.color = null;
    }

    // Regla de negocio
    public boolean hasEnoughStock(int quantity) {
        return this.stock >= quantity;
    }
}
