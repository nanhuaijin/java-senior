package com.breeze.senior.cas;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author : breeze
 * @date : 2020/5/25
 * @description : 资源类对象
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User{
    String username;
    int age;

    public void setUsername(String username) {
        this.username = username;
    }
}