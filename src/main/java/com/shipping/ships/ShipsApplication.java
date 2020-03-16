package com.shipping.ships;

import com.shipping.ships.util.InitialiseInventory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class  ShipsApplication {
	// todo: adding linting + spotbugs / sonarqube

	private static final Logger LOG = LoggerFactory.getLogger(ShipsApplication.class);


	public static void main(String[] args) {
		LOG.info("Application started");
		SpringApplication.run(ShipsApplication.class, args);
	}

	@Bean(initMethod="init")
	public InitialiseInventory initialiseInventoryBean() {
		LOG.info("InitialiseInventory bean called by application startup");
		return new InitialiseInventory();
	}


}
