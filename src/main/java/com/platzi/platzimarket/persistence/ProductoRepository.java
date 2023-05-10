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
    @Override
    public List<Product> getAll() {
        List<Producto> productos = (List<Producto>) productoCrudRepository.findAll(); // inciialmente nos genera error)
        return mapper.toProcusts(productos);
        // pero nos recomiendan que castiemos((List<Producto>)) el return y se arregla el error.
    }

    @Override
    public Optional<List<Product>> getByCategory(int categoryId) {
        List<Producto> productos = productoCrudRepository.findByIdCategoriaOrderByNombreAsc(categoryId);
        return Optional.of(mapper.toProcusts(productos));
    }

    @Override
    public Optional<List<Product>> getScarseProducts(int quantity) {
        Optional<List<Producto>> productos =  productoCrudRepository.findByCantidadStockLessThanAndEstado(quantity, true);
        // como no tengo ningun mapiador que me convierta una lista de opcionales
        // por lo tango tengo que crear un map de los productos
        return productos.map(prods -> mapper.toProcusts(prods)); // De esta forma el .map me genera un opcional de lo que este haciendo dentro de mi exprecion
        // lo que hace es que me convierte (toda la lista de productos que cumplen con la condicion ) a Product
    }

    @Override
    public Optional<Product> getProduct(int productId) {
    return productoCrudRepository.findById(productId).map(producto -> mapper.toProduct(producto));

    }

    @Override
    public Product save(Product product) {
        // el save espera un producto no un product
        // por lo que hacemos la conversion inversa
        Producto producto = mapper.toProducto(product);
        return mapper.toProduct(productoCrudRepository.save(producto));
    }


    // Eliminar un producto mediante su Id
    @Override
    public void delete(int productId) {

        productoCrudRepository.deleteById(productId);
    }

}
