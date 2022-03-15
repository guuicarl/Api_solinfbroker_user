package com.acoes.solinfbreaker.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Getter
@Setter
@Table (name = "user_orders")
public class UserOrders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "id_stock")
    private Long idStock;
    @Column(name = "stock_symbol")
    private String stockSymbol;
    @Column(name = "stock_name")
    private String stockName;
    private Long volume;
    private Double price;
    private Integer type;
    private Integer status;
    @Column(name = "remaining_value")
    private Long remainingValue;



    @CreationTimestamp
    @Column(name = "created_on")
    private Timestamp created;
    @UpdateTimestamp
    @Column(name = "updated_on")
    private Timestamp updated;

    @ManyToOne
    @JoinColumn(name = "id_user")
    private User user;

    public UserOrders() {
    }

    public UserOrders(Long id, Integer status){
        this.id = id;
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserOrders that = (UserOrders) o;
        return id.equals(that.id) && idStock.equals(that.idStock);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, idStock);
    }



}
