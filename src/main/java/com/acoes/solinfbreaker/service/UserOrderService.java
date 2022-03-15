package com.acoes.solinfbreaker.service;

import com.acoes.solinfbreaker.model.User;
import com.acoes.solinfbreaker.model.UserOrders;
import com.acoes.solinfbreaker.model.UserStockBalance;
import com.acoes.solinfbreaker.model.UserStockBalances;
import com.acoes.solinfbreaker.repository.UserOrdersRepository;
import com.acoes.solinfbreaker.repository.UserStockBalancesRepository;
import com.acoes.solinfbreaker.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ResponseStatusException;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

@Service
public class UserOrderService {
    @Autowired
    private UserOrdersRepository userOrdersRepository;
    @Autowired
    private UsersRepository usersRepository;
    @Autowired
    private WebClient webClient;
    @Autowired
    private StockService stockService;
    @Autowired
    private UserStockBalancesRepository repository;

    public UserOrders match(UserOrders dto){
        List<UserOrders> userOrders = userOrdersRepository.pegandoMatch(dto.getIdStock(), dto.getType());
        for (UserOrders cont: userOrders) {
            if (!userOrders.isEmpty()) {
                if (dto.getType() == 0 && dto.getPrice() >= cont.getPrice() || dto.getType() == 1 && dto.getPrice() <= cont.getPrice()) {
                    //Atualiza o remaining value
                    Long remainingValueNova = dto.getRemainingValue();
                    Long remainingValueOrder = cont.getRemainingValue();
                    dto.setRemainingValue(remainingValue(remainingValueNova, remainingValueOrder));
                    cont.setRemainingValue(remainingValue(remainingValueOrder, remainingValueNova));
                    //Atualiza dollar balance do usuario
                    dto.getUser().setDollarBalance(dollarBalance(dto.getUser().getDollarBalance(), remainingValueNova, remainingValueOrder, dto.getPrice(), cont.getPrice(), dto.getType()));
                    cont.getUser().setDollarBalance(dollarBalance(cont.getUser().getDollarBalance(), remainingValueNova, remainingValueOrder, cont.getPrice(), dto.getPrice(),cont.getType()));
                    //Atualiza carteira do usuario
                    atualizarUsbVenda(dto.getVolume(), dto.getRemainingValue(), dto.getType(),dto.getUser(), dto.getIdStock(), dto.getStockName(), dto.getStockSymbol(), remainingValueNova);
                    atualizarUsbVenda(cont.getVolume(), cont.getRemainingValue(), cont.getType(), cont.getUser(), cont.getIdStock(),cont.getStockName(), cont.getStockSymbol(),remainingValueOrder);

                }
            }
        }
        userOrdersRepository.updateStatus2();
        return null;
    }
    public Long remainingValue(Long a, Long b){
        if (a >= b){
            return a-b;
        } else {
            return Long.valueOf(0);
        }
    }

    public Double dollarBalance(Double dollar, Long remainingA, Long remainingB, Double priceOrder, Double priceOrder2, Integer tipo){
        if (tipo == 1 && remainingA >= remainingB){
            return dollar + remainingB*priceOrder;
        } else if(tipo ==1 && remainingA < remainingB){
            return  dollar + remainingA*priceOrder;
        } else if(tipo ==0 && remainingA >= remainingB){
            return dollar + ((remainingB * priceOrder) - (remainingB * priceOrder2));
        } else {
            return dollar + ((remainingA * priceOrder) - (remainingA * priceOrder2));
        }
    }

    public void dollarDisponivel(UserOrders uo){
        if (uo.getType() == 0){
            uo.getUser().setDollarBalance(uo.getUser().getDollarBalance() - (uo.getVolume() * uo.getPrice()));
        }
    }

    public Double fecharOrdemC(Long volume, Double price, Double dollar, Integer tipo, Long remaining){
        if (tipo == 0 && remaining == volume){
            return dollar + volume  * price;
        } else if (tipo ==0 && remaining != volume) {
            return dollar + remaining * price;
        }
        return dollar;
    }

    public void volumeDisponivel(UserOrders uo){
        List<UserStockBalances> usb = repository.atualizarBalanceTeste(uo.getUser(), uo.getIdStock());
        usb.get(0).setVolume(usb.get(0).getVolume() - uo.getVolume());
    }

    public void fecharOrdemV(User user, Long idStock, Integer tipo, Long remaining, Long volume){
        List<UserStockBalances> userStockBalances1 = repository.atualizarBalanceTeste(user, idStock);
        if (tipo == 1 && remaining == volume){
            userStockBalances1.get(0).setVolume(userStockBalances1.get(0).getVolume() + volume);
        } else if (tipo ==1 && remaining != volume){
            userStockBalances1.get(0).setVolume(userStockBalances1.get(0).getVolume() + remaining);
        }
    }

    public void atualizarUsbVenda (Long volume, Long remaining, Integer tipo, User user, Long idStock, String name, String symbol, Long remaining2){
        List<UserStockBalances> userStockBalances1 = repository.atualizarBalanceTeste(user, idStock);
        if(userStockBalances1.isEmpty()){
            repository.save(new UserStockBalances(new UserStockBalance(user, idStock), symbol, name, volume));
        } else if(tipo == 0 && remaining != 0){
            userStockBalances1.get(0).setVolume(userStockBalances1.get(0).getVolume() + (volume-remaining));
        } else if(tipo == 0 && remaining == 0){
            userStockBalances1.get(0).setVolume(userStockBalances1.get(0).getVolume() + remaining2);
        }
    }
    public Page<UserOrders> getUser(Long idUser, int pageNumber, int pageSize) {
        Pageable page = PageRequest.of(pageNumber, pageSize);
        return userOrdersRepository.listOrders(idUser, page);
    }

    public void update(Long id, Map<String, Object> request){
        UserOrders uo  = userOrdersRepository.findById(id).orElseThrow(() -> {
           throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Nao encontrado");
        });
        request.forEach((k,v) -> {
            Field field = ReflectionUtils.findField(UserOrders.class, k);
            field.setAccessible(true);
            ReflectionUtils.setField(field, uo, v);
        });
        userOrdersRepository.save(uo);
    }

}
