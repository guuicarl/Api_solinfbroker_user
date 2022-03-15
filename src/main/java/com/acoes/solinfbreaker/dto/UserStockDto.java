package com.acoes.solinfbreaker.dto;

import com.acoes.solinfbreaker.model.User;
import com.acoes.solinfbreaker.model.UserStockBalance;
import com.acoes.solinfbreaker.model.UserStockBalances;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserStockDto {
    private Long idUser;
    private Long idStock;
    private String stockSymbol;
    private String stockName;
    private Long volume;

    public UserStockBalances tranformaParaObjeto(User user){
        return new UserStockBalances(new UserStockBalance(user, idStock), stockSymbol, stockName, volume);
    }

}
