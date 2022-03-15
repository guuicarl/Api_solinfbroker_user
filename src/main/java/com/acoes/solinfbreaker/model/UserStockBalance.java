package com.acoes.solinfbreaker.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
@Getter
@Setter
public class UserStockBalance implements Serializable {

        private Long idStock;
        @ManyToOne
        @JoinColumn(name = "id_user")
        private User user;


       public UserStockBalance(User user,Long idStock){
            this.idStock = idStock;
            this.user = user;
        }


    public UserStockBalance(){}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserStockBalance that = (UserStockBalance) o;
        return Objects.equals(idStock, that.idStock) && Objects.equals(user, that.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idStock, user);
    }
}

