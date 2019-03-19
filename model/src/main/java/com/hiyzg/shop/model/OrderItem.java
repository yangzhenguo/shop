package com.hiyzg.shop.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by Sam on 2019/3/19.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderItem {
    private String itemid;
    private Integer count;
    private Double subtotal;
    private Product product;
    private Order order;
}
