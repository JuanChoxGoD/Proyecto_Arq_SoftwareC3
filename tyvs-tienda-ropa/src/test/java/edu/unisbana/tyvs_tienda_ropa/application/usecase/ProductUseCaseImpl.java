package edu.unisbana.tyvs_tienda_ropa.application.usecase;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import edu.unisbana.tyvs_tienda_ropa.application.domain.model.Product;
import edu.unisbana.tyvs_tienda_ropa.application.port.in.ProductUseCase;
import edu.unisbana.tyvs_tienda_ropa.application.port.out.ProductRepositoryPort;

@Service
public class ProductUseCaseImpl implements ProductUseCase {

    private final ProductRepositoryPort productRepository;

    public ProductUseCaseImpl(ProductRepositoryPort productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Optional<Product> getProductById(String id) {
        return productRepository.findById(id);
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product updateProduct(String id, Product product) {
        Optional<Product> existing = productRepository.findById(id);

        if (existing.isEmpty()) {
            throw new IllegalArgumentException("Product not found with id: " + id);
        }

        Product toUpdate = existing.get();
        toUpdate.setName(product.getName());
        toUpdate.setPrice(product.getPrice());
        toUpdate.setStock(product.getStock());

        return productRepository.save(toUpdate);
    }

    @Override
    public void deleteProduct(String id) {
        productRepository.delete(id);
    }
}
