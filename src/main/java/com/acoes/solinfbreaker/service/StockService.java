package com.acoes.solinfbreaker.service;

import com.acoes.solinfbreaker.controller.UserOrdersController;
import com.acoes.solinfbreaker.dto.StockDto;
import com.acoes.solinfbreaker.model.UserStockBalances;
import com.acoes.solinfbreaker.repository.UserOrdersRepository;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;

@Service
@RestController
public class StockService {
    @Autowired
    private WebClient webClient;
    @Autowired
    private UserOrdersRepository userOrdersRepository;
    @Autowired
    private UserOrdersController userOrdersController;
    @GetMapping
    public StockDto obterPorCodigo2(Long id, @RequestHeader("Authorization") String token ) {
        Mono<StockDto> monoStock = this.webClient
                .method(HttpMethod.GET)
                .uri("/stocks/{id}", id)
                .header(HttpHeaders.AUTHORIZATION, token)
                .retrieve()
                .bodyToMono(StockDto.class);
        return monoStock.block();
    }
    @GetMapping("/stocks")
    public List<UserStockBalances> listar2(@RequestHeader("Authorization") String token){
        Mono<UserStockBalances[]> monoStock = this.webClient
                .method(HttpMethod.GET)
                .uri("/stocks")
                .header(HttpHeaders.AUTHORIZATION, token)
                .retrieve()
                .bodyToMono(UserStockBalances[].class);
        return Arrays.stream(monoStock.block()).toList();

    }

    public StockDto teste1(Long id, @RequestHeader("Authorization") String token) {
        JSONObject json = new JSONObject();
        json.put("id", id);
        json.put("askMin", userOrdersRepository.getAskMin(id));
        json.put("askMax", userOrdersRepository.getAskMax(id));
        json.put("bidMin", userOrdersRepository.getBidMin(id));
        json.put("bidMax", userOrdersRepository.getBidMax(id));
        Mono<StockDto> monoStock =
                this.webClient
                        .post()
                        .uri("/teste")
                        .header(HttpHeaders.AUTHORIZATION, token)
                        .body(BodyInserters.fromValue(json))
                        .retrieve()
                        .bodyToMono(StockDto.class);
        return monoStock.block();
    }
}
