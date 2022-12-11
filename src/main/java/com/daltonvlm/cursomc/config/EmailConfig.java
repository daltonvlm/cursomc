package com.daltonvlm.cursomc.config;

import com.daltonvlm.cursomc.services.DBService;
import com.daltonvlm.cursomc.services.EmailService;
import com.daltonvlm.cursomc.services.MockEmailService;
import com.daltonvlm.cursomc.services.SmtpEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.text.ParseException;

@Configuration
public class EmailConfig {
    @Profile({"dev", "prod"})
    @Bean
    public EmailService smptEmailService() {
        return new SmtpEmailService();
    }

    @Profile({"default", "test"})
    @Bean
    public EmailService mockEmailService() {
        return new MockEmailService();
    }

    @Profile({"default", "test"})
    @Bean
    public JavaMailSender javaMailSender() {
        return new JavaMailSenderImpl();
    }
}
