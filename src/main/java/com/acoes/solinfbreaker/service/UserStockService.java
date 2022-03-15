package com.acoes.solinfbreaker.service;

import com.acoes.solinfbreaker.model.UserStockBalances;
import com.acoes.solinfbreaker.repository.UserStockBalancesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserStockService {
    @Autowired
    private UserStockBalancesRepository userStockBalancesRepository;

    public UserStockBalances salvar(UserStockBalances userStockBalances){
        return userStockBalancesRepository.save(userStockBalances);
    }
}
