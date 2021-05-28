package com.modularbank.services;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.modularbank.services.dbmapper")
@SpringBootApplication
public class ModularBankAccountApplication {

    public static void main(String[] args) {
        SpringApplication.run(ModularBankAccountApplication.class, args);
    }

}
