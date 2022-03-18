package com.acoes.solinfbreaker.integracao;

import com.acoes.solinfbreaker.controller.UserController;
import com.acoes.solinfbreaker.controller.UserStockBalancesController;
import com.acoes.solinfbreaker.repository.UserOrdersRepository;
import com.acoes.solinfbreaker.repository.UserStockBalancesRepository;
import com.acoes.solinfbreaker.repository.UsersRepository;
import com.acoes.solinfbreaker.service.UserOrderService;
import com.acoes.solinfbreaker.service.UserStockService;
import io.restassured.http.ContentType;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.web.reactive.function.client.WebClient;

@WebMvcTest
@RequiredArgsConstructor
class UsersRepositoryTeste {
    @Autowired
    private UserController userController;
    @MockBean
    private UsersRepository usersRepository;
    @MockBean
    private UserOrdersRepository userOrdersRepository;
    @MockBean
    private WebClient webClient;
    @MockBean
    private UserOrderService userOrderService;
    @MockBean
    private UserStockBalancesRepository userStockBalancesRepository;
    @MockBean
    private UserStockService userStockService;
    @MockBean
    private UserStockBalancesController userStockBalancesController;
    @BeforeEach
    public void setUp() {
        RestAssuredMockMvc.standaloneSetup(this.userController);
    }

    @Test
    void retornarUsers(){
        RestAssuredMockMvc.given().accept(ContentType.JSON).when()
                .get("/users")
                .then()
                .statusCode(HttpStatus.OK.value());
    }
}