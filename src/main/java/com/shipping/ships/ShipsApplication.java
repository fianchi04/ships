package com.shipping.ships;

import com.shipping.ships.util.InitialiseInventory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class  ShipsApplication {


	public static void main(String[] args) {
		SpringApplication.run(ShipsApplication.class, args);
	}

	@Bean(initMethod="init")
	public InitialiseInventory initialiseInventoryBean() {
		return new InitialiseInventory();
	}


}
