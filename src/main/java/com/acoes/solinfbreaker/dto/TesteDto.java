package com.acoes.solinfbreaker.dto;

import com.acoes.solinfbreaker.model.UserOrders;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Data
public class TesteDto {
    private Long id;
    private Integer status;

    public UserOrders tranformaParaObjeto2(Long id){
        return new UserOrders (id, status);
    }
}
