package com.acoes.solinfbreaker.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Getter
@Setter
@Table (name="user_stock_balances")
public class UserStockBalances implements Serializable {
    @EmbeddedId
    private UserStockBalance id;
    @Column(name = "stock_symbol")
    private String stockSymbol;
    @Column(name = "stock_name")
    private String stockName;
    private Long volume;
    @CreationTimestamp
    @Column(name = "created_on")
    private Timestamp created;
    @UpdateTimestamp
    @Column(name = "updated_on")
    private Timestamp updated;


    public UserStockBalances() {

    }

    public UserStockBalances(UserStockBalance id, String stockSymbol, String stockName, Long volume){
        this.id = id;
        this.stockSymbol = stockSymbol;
        this.stockName = stockName;
        this.volume = volume;

    }

}
