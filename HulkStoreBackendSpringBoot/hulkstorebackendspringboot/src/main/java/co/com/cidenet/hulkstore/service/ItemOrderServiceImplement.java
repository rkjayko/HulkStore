package co.com.cidenet.hulkstore.service;

import co.com.cidenet.hulkstore.entity.ItemOrder;
import co.com.cidenet.hulkstore.repository.ItemOrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ItemOrderServiceImplement implements ItemOrderService {

    private ItemOrderRepository itemOrderRepository;

    public ItemOrderServiceImplement(ItemOrderRepository itemOrderRepository) {
        this.itemOrderRepository = itemOrderRepository;
    }

    @Override
    public ItemOrder create(ItemOrder itemOrder) {
        return this.itemOrderRepository.save(itemOrder);
    }
}