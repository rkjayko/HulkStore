package co.com.cidenet.hulkstore.service;

import co.com.cidenet.hulkstore.entity.ItemOrder;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Validated
public interface ItemOrderService {

    ItemOrder create(@NotNull(message = "The products for order cannot be null.") @Valid ItemOrder itemOrder);
}
