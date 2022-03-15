package com.acoes.solinfbreaker.dto;

import com.acoes.solinfbreaker.model.User;
import com.acoes.solinfbreaker.model.UserOrders;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserOrdersDto {
    private Long id;
    private Long idUser;
    private Long idStock;
    private String stockSymbol;
    private String stockName;
    private Long volume;
    private Double price;
    private Integer type;
    private Integer status;
    private Long remainingValue;


    public UserOrders tranformaParaObjeto1(User user){
        UserOrders uo = new UserOrders();
        uo.setUser(user);
        uo.setIdStock(idStock);
        uo.setStockName(stockName);
        uo.setStockSymbol(stockSymbol);
        uo.setVolume(volume);
        uo.setPrice(price);
        uo.setType(type);
        uo.setStatus(status);
        uo.setRemainingValue(remainingValue);
        return uo;
    }
}
