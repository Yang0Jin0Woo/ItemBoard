package com.ItemSiu.repository;

import com.ItemSiu.domain.Item;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MemoryItemRepository implements ItemRepository{

    @PersistenceContext
    private EntityManager em;

    @Override
    public void save(Item item) {
        if(item.getId() == null){
            em.persist(item);
        }
        else {
            em.merge(item);
        }
    }

    @Override
    public Item findById(Long id) {
        return em.find(Item.class, id);
    }

    @Override
    public List<Item> findAll() {
        return em.createQuery("select i from Item i", Item.class)
                .getResultList();
    }

    @Override
    public void delete(Item item) {
        em.remove(item);
    }
}
