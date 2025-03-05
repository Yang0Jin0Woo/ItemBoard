package com.ItemSiu.repository;

import com.ItemSiu.domain.Item;

import java.util.List;

public interface ItemRepository {
    void save(Item item);
    Item findById(Long id);
    List<Item> findAll();
    void delete(Item item);
}
