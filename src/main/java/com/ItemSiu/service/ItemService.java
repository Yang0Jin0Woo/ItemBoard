package com.ItemSiu.service;

import com.ItemSiu.domain.Item;
import com.ItemSiu.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class ItemService {
    private final ItemRepository itemRepository;

    @Autowired
    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @Transactional
    public void saveItem(Item item){
        itemRepository.save(item);
    }

    public Item findOne(Long id){
        return itemRepository.findById(id);
    }
    public List<Item> findList(){
        return itemRepository.findAll();
    }

    @Transactional
    public void updateItem(Item updateItem, Long id){
        Item item = itemRepository.findById(id);
        if(item != null) {
            item.setName(updateItem.getName());
            item.setContent(updateItem.getContent());
        }
    }

    @Transactional
    public void deleteItem(Long id){
        Item itemDel=itemRepository.findById(id);
        if(itemDel != null){
            itemRepository.delete(itemDel);
        }
    }
}
