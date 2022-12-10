package com.daltonvlm.cursomc.config;

import com.daltonvlm.cursomc.services.DBService;
import com.daltonvlm.cursomc.services.EmailService;
import com.daltonvlm.cursomc.services.SmtpEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.text.ParseException;

@Configuration
public class EmailConfig {
    @Profile({"dev", "prod"})
    @Bean
    public EmailService emailService() {
        return new SmtpEmailService();
    }
}
