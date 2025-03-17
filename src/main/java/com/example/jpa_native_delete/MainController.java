package com.example.jpa_native_delete;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequiredArgsConstructor
public class MainController {

    @Autowired
    private final ItemRepo itemRepo;

    @Autowired
    private final CartRepo cartRepo;

    @EventListener(ApplicationReadyEvent.class)
    public void init() {
        // Create an order
        Cart cart = new Cart();
        cart.setId(1);
        cart.setName("Cart 1");

        cartRepo.save(cart);

        // Create an item
        Item item1 = new Item();
        item1.setId(1);
        item1.setName("Item 1");
        item1.setCart(cart);

        // Created another item
        Item item2 = new Item();
        item2.setId(2);
        item2.setName("Item 2");
        item2.setCart(cart);

        ArrayList<Item> items = new ArrayList<>();
        items.add(item1);
        items.add(item2);
        cart.setItems(items);

        // Save the item
        itemRepo.save(item1);
        itemRepo.save(item2);
        cartRepo.save(cart);
    }

    // curl -X GET http://localhost:8080/carts
    @GetMapping("/carts")
    public List<Cart> getCarts() {
        return cartRepo.findAll();
    }

    // curl -X GET http://localhost:8080/items
    @GetMapping("/items")
    public List<Item> getItems() {
        return itemRepo.findAll();
    }

    // curl -X POST 'http://localhost:8080/emptyCart?cartId=1'
    @PostMapping(value = "/emptyCart", params = "cartId")
    @Transactional
    public String emptyCart(@RequestParam String cartId) {
        Cart cart = cartRepo.findById(Integer.parseInt(cartId)).get();
        cart.getItems().clear();
        cartRepo.save(cart);
        return "cart emptied";
    }

}
