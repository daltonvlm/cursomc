package com.daltonvlm.cursomc.config;

import com.daltonvlm.cursomc.services.DBService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.text.ParseException;

@Configuration
public class DBConfig {
    @Autowired
    private DBService dbService;

    @Value("${spring.jpa.hibernate.ddl-auto}")
    private String strategy;

    @Profile({"default", "test"})
    @Bean
    public boolean instantiateTestDatabase() throws ParseException {
        dbService.instantiateMockDatabase();
        return true;
    }

    @Profile("dev")
    @Bean
    public boolean instantiateDevDatabase() throws ParseException {
        if (!strategy.equals("create")) {
            return false;
        }
        dbService.instantiateMockDatabase();
        return true;
    }
}
