package com.hiyzg.shop.service.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by Sam on 2019/3/19.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cart {
    private Map<String, CartItem> elements = new LinkedHashMap<>();
    private double total;

    public Collection<CartItem> getItems() {
        return this.elements.values();
    }

    public double getTotal() {
        return this.getItems().stream().map(CartItem::getSubTotal).reduce(0.0, (x, y) -> x + y);
    }

    public void add(CartItem cartItem) {
        String pid = cartItem.getProduct().getPid();
        if (this.elements.containsKey(pid)) {
            CartItem oldCartItem = this.elements.get(pid);
            oldCartItem.setCount(oldCartItem.getCount() + cartItem.getCount());
        } else {
            this.elements.put(pid, cartItem);
        }
    }

    public boolean delete(String pid) {
        return this.elements.remove(pid) != null;
    }

    public void clear() {
        this.elements.clear();
    }
}
