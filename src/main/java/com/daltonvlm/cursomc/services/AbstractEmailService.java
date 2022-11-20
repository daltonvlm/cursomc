package com.daltonvlm.cursomc.services;

import com.daltonvlm.cursomc.domain.ClientOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Date;

public abstract class AbstractEmailService implements EmailService {

    @Value("${default.sender}")
    private String sender;

    @Autowired
    private TemplateEngine templateEngine;

    @Autowired
    private JavaMailSender javaMailSender;

    @Override
    public void sendOrderConfirmationEmail(ClientOrder order) {
        SimpleMailMessage smm = prepareSimpleMailMessageFromOrder(order);
        sendEmail(smm);
    }

    @Override
    public void sendOrderConfirmationHtmlEmail(ClientOrder order) {
        try {
            MimeMessage mm = prepareMimeMessageFromOrder(order);
            sendHtmlEmail(mm);
        } catch (MessagingException e) {
            sendOrderConfirmationEmail(order);
        }
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

    protected MimeMessage prepareMimeMessageFromOrder(ClientOrder order) throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mmh = new MimeMessageHelper(mimeMessage, true);
        mmh.setTo(order.getClient().getEmail());
        mmh.setFrom(sender);
        mmh.setSubject("Order confirmed! Number: " + order.getId());
        mmh.setSentDate(new Date(System.currentTimeMillis()));
        mmh.setText(htmlFromOrderTemplate(order), true);
        return mimeMessage;
    }

    protected String htmlFromOrderTemplate(ClientOrder order) {
        Context context = new Context();
        context.setVariable("order", order);
        return templateEngine.process("email/orderConfirmation", context);
    }
}
