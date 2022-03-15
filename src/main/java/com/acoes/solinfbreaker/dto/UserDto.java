package com.acoes.solinfbreaker.dto;

import com.acoes.solinfbreaker.model.User;
import lombok.Data;
@Data
public class UserDto {
    private String username;
    private String password;
    private Double dollarBalance;

    public User tranformaParaObjeto1(){
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setDollarBalance(dollarBalance);
        return user;
    }
}

