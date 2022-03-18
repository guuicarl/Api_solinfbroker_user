package com.acoes.solinfbreaker;

import com.acoes.solinfbreaker.service.UserOrderService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.Assertions;

@SpringBootTest
class SolinfbreakerApplicationTests {
	UserOrderService uo = new UserOrderService();

	@Test
	void remaining() {
		Long a = 10L;
		Long b = 5L;
		Long c = uo.remainingValue(a,b);
		Assertions.assertEquals(5, c);
	}

	@Test
	void dollar(){
		Double dollar = 200.00;
		Double price1 = 10.00;
		Double price2 = 11.50;
		Long remainingA = 15L;
		Long remainingB = 10L;
		Integer tipo = 1;
		Double resultado = uo.dollarBalance(dollar, remainingA, remainingB, price1, price2, tipo);
		Assertions.assertEquals(300, resultado);
	}

	@Test
	void fecharOrdemC(){
		Double price = 10.00;
		Double dollar = 500.00;
		Long volume = 50L;
		Long remaining = 50L;
		Integer tipo = 0;
		Double resultado = uo.fecharOrdemC(volume, price, dollar, tipo, remaining);
		Assertions.assertEquals(1000, resultado);
	}

}
