package com.daltonvlm.cursomc.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import javax.mail.internet.MimeMessage;

public class SmtpEmailService extends AbstractEmailService {
    private static final Logger LOG = LoggerFactory.getLogger(SmtpEmailService.class);
    @Autowired
    private MailSender mailSender;

    @Autowired
    private JavaMailSender javaMailSender;

    @Override
    public void sendEmail(SimpleMailMessage msg) {
        LOG.info("Sending email...");
        mailSender.send(msg);
        LOG.info("Email sent");
    }

    @Override
    public void sendHtmlEmail(MimeMessage msg) {
        LOG.info("Sending email...");
        javaMailSender.send(msg);
        LOG.info("Email sent");
    }
}
