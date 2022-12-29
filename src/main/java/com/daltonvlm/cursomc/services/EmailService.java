package com.daltonvlm.cursomc.services;

import com.daltonvlm.cursomc.domain.Client;
import com.daltonvlm.cursomc.domain.ClientOrder;
import org.springframework.mail.SimpleMailMessage;

import javax.mail.internet.MimeMessage;

public interface EmailService {

    void sendOrderConfirmationEmail(ClientOrder order);

    void sendEmail(SimpleMailMessage msg);

    void sendOrderConfirmationHtmlEmail(ClientOrder order);

    void sendHtmlEmail(MimeMessage msg);

    void sendNewPasswordEmail(Client client, String newPass);
}
