package com.acoes.solinfbreaker.repository;

import com.acoes.solinfbreaker.model.User;
import com.acoes.solinfbreaker.model.UserStockBalance;
import com.acoes.solinfbreaker.model.UserStockBalances;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserStockBalancesRepository extends JpaRepository<UserStockBalances, UserStockBalance> {
    @Query(value = "select * from user_stock_balances where id_user=?1", nativeQuery = true)
    List<UserStockBalances> listCarteira(Long idUser);

    @Query(value = "select * from user_stock_balances where id_user=?1 and stock_name = ?2", nativeQuery = true)
    List<UserStockBalances> listCarteira2(Long idUser, String stockName);

    @Query(value =  "  select * from user_stock_balances usb where id_user = ?1 and id_stock = ?2 " , nativeQuery = true)
    List<UserStockBalances> verficarStock(Long idUser, Long idStock);

    @Query(value = "select * from user_stock_balances  where id_user = ?1 and id_stock = ?2 ", nativeQuery = true)
    List <UserStockBalances> atualizarBalanceTeste(User user, Long idStock);

}
