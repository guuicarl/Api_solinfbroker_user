package com.acoes.solinfbreaker.integracao;

import com.acoes.solinfbreaker.controller.UserController;
import com.acoes.solinfbreaker.controller.UserStockBalancesController;
import com.acoes.solinfbreaker.model.User;
import com.acoes.solinfbreaker.repository.UserOrdersRepository;
import com.acoes.solinfbreaker.repository.UserStockBalancesRepository;
import com.acoes.solinfbreaker.repository.UsersRepository;
import com.acoes.solinfbreaker.service.UserOrderService;
import com.acoes.solinfbreaker.service.UserStockService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class TestUsersRepository {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private UserController userController;
    @Autowired
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

    @Test
    void postUser() throws Exception {
        User user = new User("teste2", "testepass", 10D);
        String token = "Bearer eyJraWQiOiJGOUxUSktJa2pmMGVzdHFjWVgzM3lXRi1KckVPcWRReHQtb1owT01BaERvIiwiYWxnIjoiUlMyNTYifQ.eyJ2ZXIiOjEsImp0aSI6IkFULk5QVlVHZlBGRy1scnV2RGI2OVU3UXgyS3hicTFDZTBVTE5sbERLbkJoRGciLCJpc3MiOiJodHRwczovL2Rldi0zMDcxNzg2Lm9rdGEuY29tL29hdXRoMi9kZWZhdWx0IiwiYXVkIjoiYXBpOi8vZGVmYXVsdCIsImlhdCI6MTY0Nzg4MjI4OSwiZXhwIjoxNjQ3ODg1ODg5LCJjaWQiOiIwb2EzazhyZzdlYVA1TzN2YjVkNyIsInVpZCI6IjAwdTNqdWF3ZmpsU3B6dHVRNWQ3Iiwic2NwIjpbImVtYWlsIiwib3BlbmlkIiwicHJvZmlsZSJdLCJhdXRoX3RpbWUiOjE2NDc4ODIyODYsInN1YiI6Imd1aWxoZXJtZS5jYXJsb3MwMTRAZ21haWwuY29tIn0.TJmqcmdsu5A5WRfJN_5Kz9D2QpoWgm6n9HO6gnxT-wgD4KoarfD6tsoQfCBV2fHvy1UqjPvM7TqwsPAUOGa-UmOqMsqC-NEJJzs31k1tf5lVJkvd-1pJUdiEQGobG-P2eIJN5zzqGSSixWbT3MRtW5ZFrxCMowk0et2zgiq3QVmYWOthWOTmkvEp8CAPwdf5rRQ2trpDlreOcJD65Vg5nygjO3Osj2GRu0oJnKGAO5_0KPuE4ZbQkLa0kdPBryokhEZkLNPBKu_0s0lCn2B8GnzJ10j_QwTyXDeeK_m8bgPTauibfmlv-Lx6I9G9nqtwAeTCXXskDdOUu3-KvJAldw";

        mockMvc.perform(post("/users")
                        .header(HttpHeaders.AUTHORIZATION, token)
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(user)))
                .andExpect(status().isOk());

        Optional<User> userEntity = usersRepository.findByUser("teste2");
        Assertions.assertThat(userEntity.get().getUsername()).isEqualTo("teste2");
    }

    @Test
    void getUsuario() throws Exception {
        String token = "Bearer eyJraWQiOiJGOUxUSktJa2pmMGVzdHFjWVgzM3lXRi1KckVPcWRReHQtb1owT01BaERvIiwiYWxnIjoiUlMyNTYifQ.eyJ2ZXIiOjEsImp0aSI6IkFULk5QVlVHZlBGRy1scnV2RGI2OVU3UXgyS3hicTFDZTBVTE5sbERLbkJoRGciLCJpc3MiOiJodHRwczovL2Rldi0zMDcxNzg2Lm9rdGEuY29tL29hdXRoMi9kZWZhdWx0IiwiYXVkIjoiYXBpOi8vZGVmYXVsdCIsImlhdCI6MTY0Nzg4MjI4OSwiZXhwIjoxNjQ3ODg1ODg5LCJjaWQiOiIwb2EzazhyZzdlYVA1TzN2YjVkNyIsInVpZCI6IjAwdTNqdWF3ZmpsU3B6dHVRNWQ3Iiwic2NwIjpbImVtYWlsIiwib3BlbmlkIiwicHJvZmlsZSJdLCJhdXRoX3RpbWUiOjE2NDc4ODIyODYsInN1YiI6Imd1aWxoZXJtZS5jYXJsb3MwMTRAZ21haWwuY29tIn0.TJmqcmdsu5A5WRfJN_5Kz9D2QpoWgm6n9HO6gnxT-wgD4KoarfD6tsoQfCBV2fHvy1UqjPvM7TqwsPAUOGa-UmOqMsqC-NEJJzs31k1tf5lVJkvd-1pJUdiEQGobG-P2eIJN5zzqGSSixWbT3MRtW5ZFrxCMowk0et2zgiq3QVmYWOthWOTmkvEp8CAPwdf5rRQ2trpDlreOcJD65Vg5nygjO3Osj2GRu0oJnKGAO5_0KPuE4ZbQkLa0kdPBryokhEZkLNPBKu_0s0lCn2B8GnzJ10j_QwTyXDeeK_m8bgPTauibfmlv-Lx6I9G9nqtwAeTCXXskDdOUu3-KvJAldw";
        List<User> userEntity = usersRepository.findAll();
        mockMvc.perform(get("/users")
                        .header(HttpHeaders.AUTHORIZATION, token)
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(userEntity)))
                .andExpect(status().isOk());


        Assertions.assertThat(userEntity).isNotEmpty();
    }

    @Test
    void getUsuarioPorId() throws Exception {
        String token = "Bearer eyJraWQiOiJGOUxUSktJa2pmMGVzdHFjWVgzM3lXRi1KckVPcWRReHQtb1owT01BaERvIiwiYWxnIjoiUlMyNTYifQ.eyJ2ZXIiOjEsImp0aSI6IkFULk5QVlVHZlBGRy1scnV2RGI2OVU3UXgyS3hicTFDZTBVTE5sbERLbkJoRGciLCJpc3MiOiJodHRwczovL2Rldi0zMDcxNzg2Lm9rdGEuY29tL29hdXRoMi9kZWZhdWx0IiwiYXVkIjoiYXBpOi8vZGVmYXVsdCIsImlhdCI6MTY0Nzg4MjI4OSwiZXhwIjoxNjQ3ODg1ODg5LCJjaWQiOiIwb2EzazhyZzdlYVA1TzN2YjVkNyIsInVpZCI6IjAwdTNqdWF3ZmpsU3B6dHVRNWQ3Iiwic2NwIjpbImVtYWlsIiwib3BlbmlkIiwicHJvZmlsZSJdLCJhdXRoX3RpbWUiOjE2NDc4ODIyODYsInN1YiI6Imd1aWxoZXJtZS5jYXJsb3MwMTRAZ21haWwuY29tIn0.TJmqcmdsu5A5WRfJN_5Kz9D2QpoWgm6n9HO6gnxT-wgD4KoarfD6tsoQfCBV2fHvy1UqjPvM7TqwsPAUOGa-UmOqMsqC-NEJJzs31k1tf5lVJkvd-1pJUdiEQGobG-P2eIJN5zzqGSSixWbT3MRtW5ZFrxCMowk0et2zgiq3QVmYWOthWOTmkvEp8CAPwdf5rRQ2trpDlreOcJD65Vg5nygjO3Osj2GRu0oJnKGAO5_0KPuE4ZbQkLa0kdPBryokhEZkLNPBKu_0s0lCn2B8GnzJ10j_QwTyXDeeK_m8bgPTauibfmlv-Lx6I9G9nqtwAeTCXXskDdOUu3-KvJAldw";
        Optional<User> userEntity = usersRepository.findById(5L);
        mockMvc.perform(get("/user/{id}", 5L)
                        .header(HttpHeaders.AUTHORIZATION, token)
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(userEntity)))
                .andExpect(status().isOk());


        System.out.println(userEntity);
        Assertions.assertThat(userEntity.get().getUsername()).isEqualTo("Guilherme Carlos");
        Assertions.assertThat(userEntity).isNotNull();
    }

}