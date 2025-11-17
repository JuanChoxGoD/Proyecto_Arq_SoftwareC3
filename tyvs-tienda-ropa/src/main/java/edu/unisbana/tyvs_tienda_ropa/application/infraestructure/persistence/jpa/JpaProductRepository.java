// src/main/java/edu/unisabana/tyvs_tienda_ropa/infrastructure/persistence/jpa/JpaProductRepository.java
package edu.unisbana.tyvs_tienda_ropa.application.infraestructure.persistence.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.unisbana.tyvs_tienda_ropa.application.infraestructure.persistence.entity.ProductEntity;

/**
 * Interfaz de Spring Data: Define el acceso directo a la BD (CRUD automático).
 */
public interface JpaProductRepository extends JpaRepository<ProductEntity, Long> {
    
}