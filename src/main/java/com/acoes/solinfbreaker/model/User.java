package com.acoes.solinfbreaker.model;


import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@Entity
@Table (name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String password;
    @Column(name = "dollar_balance")
    private Double dollarBalance;
    private boolean enable;
    @CreationTimestamp
    @Column(name = "created_on")
    private Timestamp created;
    @UpdateTimestamp
    @Column(name = "updated_on")
    private Timestamp updated;


    public User(String username, String password, Double dollarBalance) {
        this.username = username;
        this.password = password;
        this.dollarBalance = dollarBalance;
    }

    public User() {
    }
}
