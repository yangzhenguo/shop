package com.hiyzg.shop.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by Sam on 2019/3/14.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Category {
    private String cid;
    private String cname;
    private String curl;
}
