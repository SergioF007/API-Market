package com.platzi.platzimarket.web.controller;



import com.platzi.platzimarket.domain.Product;
import com.platzi.platzimarket.domain.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

// esta clase va hacer un controlador de una Api rest

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/all")
    public List<Product> getAll() {

        return productService.getAll();
    }

    @GetMapping("/productId/{Id}")
    public Optional<Product> getProduct(@PathVariable("Id") int productId) {
        return productService.getProduct(productId);
    }

    @GetMapping("/categoryId/{Id}")
    public Optional<List<Product>> getByCategory(@PathVariable("Id") int categoryId) {
        return productService.getByCategory(categoryId);
    }

    @PostMapping("/save")
    public Product save(@RequestBody Product product){
        return productService.save(product);
    }

    @DeleteMapping("/delete/{Id}")
    public boolean delete(@PathVariable("Id") int productId) {
        return productService.delete(productId);
    }
}
