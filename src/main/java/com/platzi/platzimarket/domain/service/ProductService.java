package com.platzi.platzimarket.domain.service;

import com.platzi.platzimarket.domain.Product;
import com.platzi.platzimarket.domain.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;
    // vemos que apesar que ProductoRepository no tiene nunguna notacion,
    // Pero la Clase que que lo implementa la cual es ProductoRepository si tiene una notacion

    public List<Product> getAll() {
        return productRepository.getAll();
    }

    public Optional<Product> getProduct(int productId) {
        return productRepository.getProduct(productId);
    }

    public Optional<List<Product>> getByCategory(int categoryId) {
        return productRepository.getByCategory(categoryId);
    }

    public Product save(Product product){
        return productRepository.save(product);
    }

    public boolean delete(int productId) {
        // primero valimado si encontrmaos el producto y luego si lo elimnamos
        return getProduct(productId).map(product -> {
            productRepository.delete(productId);
            return true;
        }).orElse(false);

        // segunda forma de implementacion
        // validando si el producto esta presente

        /*

        if (getProduct(productId).isPresent()) {
            return true;
        } else {
            return false;
        }

         */
    }
}
