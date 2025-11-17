package edu.unisbana.tyvs_tienda_ropa.application.infraestructure.persistence.adapter;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import edu.unisbana.tyvs_tienda_ropa.application.domain.model.Product;
import edu.unisbana.tyvs_tienda_ropa.application.infraestructure.persistence.entity.ProductEntity;
import edu.unisbana.tyvs_tienda_ropa.application.infraestructure.persistence.jpa.JpaProductRepository;
import edu.unisbana.tyvs_tienda_ropa.application.port.out.ProductRepositoryPort;

@Repository
public class ProductRepositoryAdapter implements ProductRepositoryPort {

    private final JpaProductRepository jpaRepository;

    public ProductRepositoryAdapter(JpaProductRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public List<Product> findAll() {
        return jpaRepository.findAll().stream()
                .map(e -> new Product(
                        e.getId(),
                        e.getName(),
                        e.getDescription(),
                        e.getPrice(),
                        e.getStock(),
                        e.getSize(),
                        e.getColor()
                ))
                .collect(Collectors.toList());
    }

    // -----------------------------
    // MÉTODO findById(String)
    // -----------------------------
    @Override
    public Optional<Product> findById(String id) {
        Long longId = Long.valueOf(id);
        return findById(longId); // reutiliza el otro método
    }

    // -----------------------------
    // MÉTODO findById(Long)
    // -----------------------------
    @Override
    public Optional<Product> findById(Long id) {
        return jpaRepository.findById(id)
                .map(e -> new Product(
                        e.getId(),
                        e.getName(),
                        e.getDescription(),
                        e.getPrice(),
                        e.getStock(),
                        e.getSize(),
                        e.getColor()
                ));
    }

    @Override
    public Product save(Product product) {
        ProductEntity entity = new ProductEntity();
        entity.setName(product.getName());
        entity.setDescription(product.getDescription());
        entity.setPrice(product.getPrice());
        entity.setStock(product.getStock());
        entity.setSize(product.getSize());
        entity.setColor(product.getColor());

        ProductEntity saved = jpaRepository.save(entity);

        return new Product(
                saved.getId(),
                saved.getName(),
                saved.getDescription(),
                saved.getPrice(),
                saved.getStock(),
                saved.getSize(),
                saved.getColor()
        );
    }

    // -----------------------------
    // MÉTODO delete(String)
    // -----------------------------
    @Override
    public void delete(String id) {
        Long longId = Long.valueOf(id);
        delete(longId); // reutiliza el otro método
    }

    // -----------------------------
    // MÉTODO delete(Long)
    // -----------------------------

    public void delete(Long id) {
        jpaRepository.deleteById(id);
    }
}
