// src/main/java/edu/unisabana/tyvs_tienda_ropa/domain/model/Customer.java
package edu.unisbana.tyvs_tienda_ropa.application.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Entidad de Dominio (Pura): Contiene atributos y lógica de negocio
 * del Producto, sin dependencias de Spring o JPA.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Customer {
    
    private Long id;
    private String name;
    private String email;

}