package com.platzi.platzimarket.persistence;

import com.platzi.platzimarket.domain.Purchase;
import com.platzi.platzimarket.domain.repository.PurchaseRespository;
import com.platzi.platzimarket.persistence.crud.CompraCrudRepository;
import com.platzi.platzimarket.persistence.entity.Compra;
import com.platzi.platzimarket.persistence.mapper.PurchaseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class CompraRepository implements PurchaseRespository {

    @Autowired
    private CompraCrudRepository compraCrudRepository;

    @Autowired
    private PurchaseMapper mapper;
    @Override
    public List<Purchase> getAll() {
        return mapper.toPurchases((List<Compra>) compraCrudRepository.findAll());
    }

    @Override
    public Optional<List<Purchase>> getByClient(String clientId) {

        return compraCrudRepository.findById(Integer.valueOf(clientId))
                .map(compras -> mapper.toPurchases((List<Compra>) compras));
    }

    /*
    @Override
    public Optional<List<Purchase>> getByClient2(String clientId) {

        return compraCrudRepository.findByIdClient(clientId)
                .map(compras -> mapper.toPurchases(compras));
    }

     */
    @Override
    public Purchase save(Purchase purchase) {

        // comvertilo a compra
        Compra compra = mapper.toCompra(purchase);
        //vamos a garantizar que esta informacion se guarden en cascada
        // y que compras conoce los productos y los productos conocen a que compra pertenece
        compra.getProductos().forEach(producto -> producto.setCompra(compra));

        return mapper.toPurchase(compraCrudRepository.save(compra));
    }
    /*
    En resumen, este método recibe una compra, la convierte a un objeto Compra, asegura que los productos tengan
    una referencia a la compra, guarda la compra en la base de datos utilizando un repositorio y finalmente
    convierte y devuelve la compra guardada como un objeto Purchase.
     */
}
