package com.acoes.solinfbreaker.service;

import com.acoes.solinfbreaker.model.UserStockBalances;
import com.acoes.solinfbreaker.repository.UserStockBalancesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserStockBalanceService {
    private final UserStockBalancesRepository repository;
    public List<UserStockBalances> getStock(Long idUser, String stockName) {
        return repository.listCarteira2(idUser, stockName);
    }

    public List<UserStockBalances> getUser(Long idUser)  {
        return repository.listCarteira(idUser);
    }

}
