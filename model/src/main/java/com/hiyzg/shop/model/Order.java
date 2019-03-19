package com.hiyzg.shop.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

/**
 * Created by Sam on 2019/3/19.
 */
@Data
@NoArgsConstructor
public class Order {
    private String oid;
    private Date ordertime;
    private Double total;
    private Integer state;
    private String address;
    private String name;
    private String telephone;
    private User user;
    private List<OrderItem> orderItems;
}
