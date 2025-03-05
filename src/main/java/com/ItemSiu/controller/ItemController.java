package com.ItemSiu.controller;

import com.ItemSiu.domain.Item;
import com.ItemSiu.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/items")
public class ItemController {
    private final ItemService itemService;

    @Autowired
    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping
    public String findItems(Model model){
        List<Item> items=itemService.findList();
        model.addAttribute("items",items);
        return "items";
    }
    @GetMapping("/{itemId}")
    public String findItem(@PathVariable("itemId") Long id, Model model){
        Item item=itemService.findOne(id);
        model.addAttribute("item",item);
        return "item";
    }

    @GetMapping("/add")
    public String addForm(Model model){
        Item item = new Item();
        model.addAttribute("item",item);
        return "addForm";
    }
    @PostMapping("/add")
    public String addItem(@ModelAttribute Item item){
        itemService.saveItem(item);
        return "redirect:/items";
    }

    @GetMapping("/{itemId}/edit")
    public String editForm(@PathVariable("itemId") Long id, Model model){
        Item item=itemService.findOne(id);
        model.addAttribute("item",item);
        return "editForm";
    }
    @PostMapping("/{itemId}/edit")
    public String editItem(@PathVariable("itemId") Long id, @ModelAttribute Item item){
        item.setId(id);
        itemService.updateItem(item, id);
        return "redirect:/items/"+id;
    }

    @PostMapping("/{itemId}/delete")
    public String delete(@PathVariable("itemId") Long id){
        itemService.deleteItem(id);
        return "redirect:/items";
    }
}
