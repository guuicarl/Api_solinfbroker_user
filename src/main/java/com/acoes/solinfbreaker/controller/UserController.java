package com.acoes.solinfbreaker.controller;

import com.acoes.solinfbreaker.dto.UserDto;
import com.acoes.solinfbreaker.model.User;
import com.acoes.solinfbreaker.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = { "http://localhost:8081/", "http://localhost:4440/"})
@RestController
public class UserController {

    @Autowired
    private UsersRepository usersRepository;

    @GetMapping("/users")
    public List<User> listar(){
        return usersRepository.findAll();
    }

    @GetMapping("/u/{username}")
    public Long lista(@PathVariable ("username") String username){
        Optional<User> us = usersRepository.findByUser(username);
        if(us.isEmpty()){
            return usersRepository.save(new User(username, "teste1234", 10000.00)).getId();
        }else {
            return us.get().getId();
        }
    }

    @GetMapping("/user/{id}")
    public Double lista1(@PathVariable ("id") Long id){
        Optional<User> us = usersRepository.findById(id);
        if(us.isPresent()) {
            return us.get().getDollarBalance();
        }
        return null;
    }


    @PostMapping("/users")
    public User adicionar(@RequestBody UserDto user) {
        return usersRepository.save(user.tranformaParaObjeto1());

    }

}
