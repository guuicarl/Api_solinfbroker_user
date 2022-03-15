package com.acoes.solinfbreaker.repository;

import com.acoes.solinfbreaker.model.UserOrders;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface UserOrdersRepository extends JpaRepository<UserOrders, Long> {
    @Modifying
    @Query(value = "UPDATE user_orders SET status = 2 WHERE remaining_value = 0", nativeQuery = true)
    int updateStatus2();

    @Query(value = "select MAX(price) from user_orders where id_stock = ?1 and status = 1 and type = 1", nativeQuery = true)
    Double getAskMax(Long idStock);

    @Query(value = "select MIN(price) from user_orders where id_stock = ?1 and status = 1 and type = 1", nativeQuery = true)
    Double getAskMin(Long idStock);

    @Query(value = "select MAX(price) from user_orders where id_stock = ?1 and status = 1 and type = 0 ", nativeQuery = true)
    Double getBidMax(Long idStock);

    @Query(value = "select MIN(price) from user_orders where id_stock = ?1 and status = 1 and type = 0", nativeQuery = true)
    Double getBidMin(Long idStock);

    @Query(value = "select * from user_orders uo where id_user=?1", nativeQuery = true)
    Page<UserOrders> listOrders(Long idUser, Pageable page);

//    todos testes novos abaixo qualquer coisa apaga tudo
    @Query(value = "select * from user_orders uo where id_stock = ?1 and type <> ?2 and status <> 2 order by created_on asc", nativeQuery = true)
    List<UserOrders> pegandoMatch(Long idStock, Integer type);

}
