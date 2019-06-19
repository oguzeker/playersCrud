package com.sosyal.tr.playersCrud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan( basePackages = {"com.sosyal.tr.playersCrud.entity"} )
public class PlayersCrudApplication {

	public static void main(String[] args) {
		SpringApplication.run(PlayersCrudApplication.class, args);
	}

}
