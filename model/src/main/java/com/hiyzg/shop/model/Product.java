package com.hiyzg.shop.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * Created by Sam on 2019/3/18.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    private String pid;
    private String pname;
    private Double market_price;
    private Double shop_price;
    private String pimage;
    private Date pdate;
    private Integer is_hot;
    private String pdesc;
    private Integer pflag;
    private String cid;
    private Category category;
}
