package com.alexisindustries.banktransactions;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class BankTransactionsApplication {
	public static void main(String[] args) {
		SpringApplication.run(BankTransactionsApplication.class, args);
	}
}
