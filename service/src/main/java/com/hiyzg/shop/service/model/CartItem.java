package com.hiyzg.shop.service.model;

import com.hiyzg.shop.model.Product;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by Sam on 2019/3/19.
 */
@Data
@NoArgsConstructor
public class CartItem {
    private Product product;
    private Integer count;
    private Double subTotal;

    public CartItem(Product product, Integer count) {
        this.product = product;
        this.count = count;
    }

    public double getSubTotal() {
        return this.product.getShop_price() * this.count;
    }
}
