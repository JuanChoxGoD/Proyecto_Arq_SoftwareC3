package edu.unisbana.tyvs_tienda_ropa.application.delivery.rest;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import edu.unisbana.tyvs_tienda_ropa.application.domain.model.Product;
import edu.unisbana.tyvs_tienda_ropa.application.port.in.ProductUseCase;

@Controller
@RequestMapping("/api/products")
public class ProductController {

    private final ProductUseCase productUseCase;

    public ProductController(ProductUseCase productUseCase) {
        this.productUseCase = productUseCase;
    }

    // ---------------------- REST ----------------------------

    @PostMapping
    @ResponseBody
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        return ResponseEntity.ok(productUseCase.createProduct(product));
    }

    @GetMapping
    @ResponseBody
    public ResponseEntity<List<Product>> getAllProducts() {
        return ResponseEntity.ok(productUseCase.getAllProducts());
    }

    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<Optional<Product>> getProductById(@PathVariable String id) {
        return ResponseEntity.ok(productUseCase.getProductById(id));
    }

    @PutMapping("/{id}")
    @ResponseBody
    public ResponseEntity<Product> updateProduct(@PathVariable String id, @RequestBody Product product) {
        return ResponseEntity.ok(productUseCase.updateProduct(id, product));
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public ResponseEntity<Void> deleteProduct(@PathVariable String id) {
        productUseCase.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }

    // ---------------------- VISTA HTML (Selenium) ----------------------------

    @GetMapping("/view")
    public ModelAndView productPage() {
        List<Product> products = productUseCase.getAllProducts();

        ModelAndView mav = new ModelAndView("products"); // busca products.html
        mav.addObject("products", products);

        return mav;
    }
}
