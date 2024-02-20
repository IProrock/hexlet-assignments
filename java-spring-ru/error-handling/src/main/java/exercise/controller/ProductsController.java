package exercise.controller;

import exercise.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;
import java.util.Optional;

import exercise.model.Product;
import exercise.repository.ProductRepository;

@RestController
@RequestMapping("/products")
public class ProductsController {

    @Autowired
    private ProductRepository productRepository;

    @GetMapping(path = "")
    public List<Product> index() {
        return productRepository.findAll();
    }

    @PostMapping(path = "")
    @ResponseStatus(HttpStatus.CREATED)
    public Product create(@RequestBody Product product) {
        return productRepository.save(product);
    }

    // BEGIN
    @GetMapping("/{id}")
    public Product viewProdById(@PathVariable Long id) {
        Optional<Product> rs = productRepository.findById(id);

        Product product = rs.orElseThrow(() -> new ResourceNotFoundException("Product with id " + id + " not found"));
        return product;
    }

    @PutMapping("/{id}")
    public Product updateProductById(@PathVariable Long id, @RequestBody Product nproduct) {
        Optional<Product> pr = productRepository.findById(id);

        Product product = pr.orElseThrow(() -> new ResourceNotFoundException("Product with id " + id + " not found"));

        product.setPrice(nproduct.getPrice());
        product.setTitle(nproduct.getTitle());

        productRepository.save(product);

        return product;
    }


    // END

    @DeleteMapping(path = "/{id}")
    public void delete(@PathVariable long id) {
        productRepository.deleteById(id);
    }
}
