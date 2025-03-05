package com.ItemSiu.service;

import com.ItemSiu.domain.Item;
import com.ItemSiu.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ItemService {
    private final ItemRepository itemRepository;

    @Autowired
    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public void saveItem(Item item){
        itemRepository.save(item);
    }

    @Transactional(readOnly = true)
    public Item findOne(Long id){
        return itemRepository.findById(id);
    }

    @Transactional(readOnly = true)
    public List<Item> findList(){
        return itemRepository.findAll();
    }

    public void updateItem(Item updateItem, Long id){
        Item item = itemRepository.findById(id);
        if(item != null) {
            item.setName(updateItem.getName());
            item.setContent(updateItem.getContent());
        }
    }

    public void deleteItem(Long id){
        Item itemDel=itemRepository.findById(id);
        if(itemDel != null){
            itemRepository.delete(itemDel);
        }
    }
}
