package com.platzi.platzimarket.persistence;

import com.platzi.platzimarket.domain.Product;
import com.platzi.platzimarket.domain.repository.ProductRepository;
import com.platzi.platzimarket.persistence.crud.ProductoCrudRepository;
import com.platzi.platzimarket.persistence.entity.Producto;
import com.platzi.platzimarket.persistence.mapper.ProductMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

// con la notacion @Repository le estamos indicando a spring
// que esta clase se encarga de intereactuar con la BD
@Repository
public class ProductoRepository implements ProductRepository {

    private ProductoCrudRepository productoCrudRepository;

    private ProductMapper mapper;

    // Creamo el metodo para recuperar todos los productos que hay en BD
    public List<Product> getAll() {
        List<Producto> productos = (List<Producto>) productoCrudRepository.findAll(); // inciialmente nos genera error)
        return mapper.toProcusts(productos);
        // pero nos recomiendan que castiemos((List<Producto>)) el return y se arregla el error.
    }

    @Override
    public Optional<List<Product>> getByCategory(int categoryId) {
        return Optional.empty();
    }

    @Override
    public Optional<List<Product>> getScarseProducts(int quantity) {
        return Optional.empty();
    }

    @Override
    public Optional<Product> getProduct(int productId) {
        return Optional.empty();
    }

    @Override
    public Product save(Product product) {
        return null;
    }

    //productos por categoria
    public List<Producto> getByCategoria(int idCategoria) {
        return productoCrudRepository.findByIdCategoriaOrderByNombreAsc(idCategoria);
    }

    //productos por cantidad y estado  (para identificar los productos escasos)
    public Optional<List<Producto>> getEscasos(int cantidad) {
        return productoCrudRepository.findByCantidadStockLessThanAndEstado(cantidad, true);
    }

    // para recuperar un produecto en especifico dado su Id
    public Optional<Producto> getProducto(int idProducto) {
        return productoCrudRepository.findById(idProducto);

    }

    // guardar un producto
    public Producto save(Producto producto) {
        return productoCrudRepository.save(producto);
    }

    // Eliminar un producto mediante su Id
    public void delete(int idProducto) {
        productoCrudRepository.deleteById(idProducto);
    }

}
