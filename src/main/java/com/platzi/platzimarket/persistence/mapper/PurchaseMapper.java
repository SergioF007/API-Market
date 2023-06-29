package com.platzi.platzimarket.persistence.mapper;

import com.platzi.platzimarket.domain.Purchase;
import com.platzi.platzimarket.persistence.entity.Compra;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring", uses = {PurchaseItemsMapper.class}) // por que internamente vamos a mapiar dentro de la compra todos sus productos
public interface PurchaseMapper {

    @Mappings({
            @Mapping( source = "idCompra", target = "purchaseId"),
            @Mapping( source = "idCliente", target = "clientId"),
            @Mapping( source = "fecha", target = "date"),
            @Mapping( source = "medioPago", target = "paymentMethod"),
            @Mapping( source = "comentario", target = "comment"),
            @Mapping( source = "estado", target = "state"),
            @Mapping( source = "productos", target = "items"),

    })
    Purchase toPurchase(Compra compra);
    List<Purchase> toPurchases(List<Compra> compras);

    @InheritInverseConfiguration
    @Mapping(target = "cliente", ignore = true)
    Compra toCompra(Purchase purchase);
}
