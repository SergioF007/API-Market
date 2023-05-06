package com.platzi.platzimarket.persistence.crud;

import com.platzi.platzimarket.persistence.entity.Producto;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ProductoCrudRepository extends CrudRepository<Producto, Integer> {

    // Recuperar toda la lista que corresponda a una categoria en especifico de productos.
    List<Producto> findByIdCategoriaOrderByNombreAsc(int idCategoria);

    // Ó lo podriamos haber hecho mediante un Query nativo
    /*
    @Query(value = "SELECT * FROM productos WHERE id_categoria = ?", nativeQuery = true)
    List<Producto> findByCategoria(int idCategoria);
    */


    // Aplicando Opcional

    Optional<List<Producto>> findByCantidadStockLessThanAndEstado(int cantidadStock, boolean estado);
}
