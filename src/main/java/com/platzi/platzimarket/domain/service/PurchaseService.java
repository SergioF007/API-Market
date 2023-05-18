package com.platzi.platzimarket.domain.service;

import com.platzi.platzimarket.domain.Purchase;
import com.platzi.platzimarket.domain.repository.PurchaseRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PurchaseService {

    @Autowired
    private PurchaseRespository purchaseRespository;

    public List<Purchase> getAll() {
        return purchaseRespository.getAll();
    }


    public Optional<List<Purchase>> getByClient(String clientId) {
        return purchaseRespository.getByClient(clientId);
    }



    public Purchase save(Purchase purchase) {
        return purchaseRespository.save(purchase);
    }
}
