package exercise.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import java.util.List;

import exercise.repository.ProductRepository;
import exercise.dto.ProductDTO;
import exercise.dto.ProductCreateDTO;
import exercise.dto.ProductUpdateDTO;
import exercise.exception.ResourceNotFoundException;
import exercise.mapper.ProductMapper;

@RestController
@RequestMapping("/products")
public class ProductsController {

    @Autowired
    private ProductRepository productRepository;

    // BEGIN
    @Autowired
    private ProductMapper productMapper;

    @GetMapping()
    public List<ProductDTO> getProductsList() {
        List<ProductDTO> rs = productRepository.findAll()
                .stream()
                .map(x -> productMapper.map(x))
                .toList();

        return rs;
    }

    @GetMapping("/{id}")
    public ProductDTO getProductById(@PathVariable long id) {
        ProductDTO productDTO = productMapper.map(productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product with id " + id + " not found")));

        return productDTO;
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ProductDTO create(@RequestBody ProductCreateDTO productCreateDTO) {
        var product = productMapper.map(productCreateDTO);

        productRepository.save(product);

        return productMapper.map(product);
    }

    @PutMapping("/{id}")
    public ProductDTO update(@RequestBody ProductUpdateDTO productUpdateDTO, @PathVariable long id) {

        var product = productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product with id " + id + " not found"));

        productMapper.update(productUpdateDTO, product);

        productRepository.save(product);

        var productDto = productMapper.map(product);

        return productDto;


    }
    // END
}