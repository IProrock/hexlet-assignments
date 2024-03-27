package exercise.controller;

import java.util.List;

import exercise.dto.ProductCreateDTO;
import exercise.dto.ProductDTO;
import exercise.dto.ProductUpdateDTO;
import exercise.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import exercise.exception.ResourceNotFoundException;
import exercise.repository.ProductRepository;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/products")
public class ProductsController {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductMapper productMapper;

    // BEGIN
    @GetMapping
    public List<ProductDTO> getAllProducts() {
        var products = productRepository.findAll();
        var rs = products.stream()
                .map(x -> productMapper.map(x))
                .toList();

        return rs;
    }

    @GetMapping("/{id}")
    public ProductDTO getProduct(@PathVariable long id) {
        var product = productRepository.findById(id);

        var rs = product.orElseThrow(() -> new ResourceNotFoundException(id + " not found"));

        var rsDto = productMapper.map(rs);

        return rsDto;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductDTO createProduct(@RequestBody ProductCreateDTO productCreateDTO) {
        var product = productMapper.map(productCreateDTO);
        productRepository.save(product);
        var prDTO = productMapper.map(product);

        return prDTO;
    }

    @PutMapping("/{id}")
    public ProductDTO updateProduct(@PathVariable long id, @RequestBody ProductUpdateDTO productUpdateDTO) {
        var product = productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id + " not found"));
        productMapper.update(productUpdateDTO, product);

//        if (product.getCategory() == null) {
//            throw new RuntimeException("no category");
//        }

        productRepository.save(product);

        var pDto = productMapper.map(product);

        return pDto;
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable long id) {
        productRepository.deleteById(id);
    }
    // END
}
