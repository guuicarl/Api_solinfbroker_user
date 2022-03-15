package com.acoes.solinfbreaker.dto;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class StockDto {
    private Long id;
    private String stockSymbol;
    private String stockName;
    private Double askMin;
    private Double askMax;
    private Double bidMin;
    private Double bidMax;

    public StockDto(Long id, String stockSymbol, String stockName, Double askMin, Double askMax, Double bidMin, Double bidMax) {
        this.id = id;
        this.stockSymbol = stockSymbol;
        this.stockName = stockName;
        this.askMin = askMin;
        this.askMax = askMax;
        this.bidMin = bidMin;
        this.bidMax = bidMax;
    }
    public StockDto tranformaParaObjeto1(){
        return new StockDto (id, stockSymbol, stockName, askMin, askMax, bidMin, bidMax);
    }
}
