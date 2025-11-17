package edu.unisbana.tyvs_tienda_ropa.application.domain.rq;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Request DTO (Data Transfer Object) para la creación de un producto.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateProductRequest {
    private String name;
    private String description;
    private double price;
    private int stock;
    private String size;
    private String color;
}

