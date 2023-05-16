package com.platzi.platzimarket.domain.repository;

import com.platzi.platzimarket.domain.Purchase;

import java.util.List;
import java.util.Optional;

public interface PurchaseRespository {

    // Creamos 3 metodos a implementar

    // todas la compras
    List<Purchase> getAll();

    // compras por cliente
    Optional<List<Purchase>> getByClient(String clientId);

    // guardar compra
    Purchase save(Purchase purchase);


}
