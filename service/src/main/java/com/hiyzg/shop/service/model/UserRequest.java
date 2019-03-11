package com.hiyzg.shop.service.model;

import com.hiyzg.shop.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by Sam on 2019/3/11.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRequest extends User {
    private String confirmpwd;
}
