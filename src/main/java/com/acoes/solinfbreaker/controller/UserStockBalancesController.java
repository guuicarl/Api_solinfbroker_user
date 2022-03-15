package com.acoes.solinfbreaker.controller;

import com.acoes.solinfbreaker.dto.StockDto;
import com.acoes.solinfbreaker.dto.UserStockDto;
import com.acoes.solinfbreaker.model.User;
import com.acoes.solinfbreaker.model.UserStockBalances;
import com.acoes.solinfbreaker.repository.UserStockBalancesRepository;
import com.acoes.solinfbreaker.repository.UsersRepository;
import com.acoes.solinfbreaker.service.StockService;
import com.acoes.solinfbreaker.service.UserStockBalanceService;
import com.acoes.solinfbreaker.service.UserStockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@CrossOrigin
@RestController
public class UserStockBalancesController {

    @Autowired
    private UserStockBalancesRepository userStockBalancesRepository;
    @Autowired
    private StockService stockService;
    @Autowired
    private WebClient webClient;
    @Autowired
    private UsersRepository usersRepository;
    @Autowired
    private UserStockService userStockService;
    @Autowired
    private UserStockBalanceService service;

    @GetMapping("/teste")
    public List<UserStockBalances> listar(){
        return userStockBalancesRepository.findAll();
    }

    @GetMapping("/teste/{id}")
    public ResponseEntity<StockDto> obterPorCodigo2(@PathVariable Long id, @RequestHeader("Authorization") String token) {

        StockDto userStockBalances = this.stockService.obterPorCodigo2(id, token);

        return ResponseEntity.ok(userStockBalances);
    }

    @PostMapping("/")
    public ResponseEntity<UserStockBalances> salvar(@RequestBody UserStockDto dto){
        User user = usersRepository.findById(dto.getIdUser()).orElseThrow();
        UserStockBalances userStockBalances = userStockService.salvar(dto.tranformaParaObjeto(user));
        return new ResponseEntity<>(userStockBalances,HttpStatus.CREATED);

    }

    @GetMapping("/wallet/{id_user}")
    public ResponseEntity<List<UserStockBalances>> getUser(@PathVariable("id_user") Long idUser) {
        try {
            return ResponseEntity.ok().body(service.getUser(idUser));
        }  catch (Exception e) {
            if(e.getMessage().equals("FAZENDA_NOT_FOUND"))
                return ResponseEntity.notFound().build();
            return ResponseEntity.badRequest().build();
            //so
        }
    }

    @GetMapping("/{idUser}/{stockName}")
    public ResponseEntity<List<UserStockBalances>> getBalance(@PathVariable Long idUser, @PathVariable String stockName) {
        try {
            return ResponseEntity.ok().body(service.getStock(idUser, stockName));
        }  catch (Exception e) {
            if(e.getMessage().equals("FAZENDA_NOT_FOUND"))
                return ResponseEntity.notFound().build();
            return ResponseEntity.badRequest().build();
        }
    }
}
