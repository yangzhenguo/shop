package com.hiyzg.shop.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * Created by Sam on 2019/3/8.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private String uid;
    private String username;
    private String password;
    private String name;
    private String email;
    private String telephone;
    private Date birthday;
    private String sex;
    private Integer state;
    private String code;
}
