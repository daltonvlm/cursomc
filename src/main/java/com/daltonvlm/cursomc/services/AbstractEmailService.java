package com.daltonvlm.cursomc.services;

import com.daltonvlm.cursomc.domain.ClientOrder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;

import java.util.Date;

public abstract class AbstractEmailService implements EmailService {

    @Value("${default.sender}")
    private String sender;

    @Override
    public void sendOrderConfirmationEmail(ClientOrder order) {
        SimpleMailMessage smm = prepareSimpleMailMessageFromOrder(order);
        sendEmail(smm);
    }

    protected SimpleMailMessage prepareSimpleMailMessageFromOrder(ClientOrder order) {
        SimpleMailMessage smm = new SimpleMailMessage();
        smm.setTo(order.getClient().getEmail());
        smm.setFrom(sender);
        smm.setSubject("Order confirmed! Number: " + order.getId());
        smm.setSentDate(new Date(System.currentTimeMillis()));
        smm.setText(order.toString());
        return smm;
    }
}
