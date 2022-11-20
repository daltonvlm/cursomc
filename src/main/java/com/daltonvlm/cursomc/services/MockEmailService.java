package com.daltonvlm.cursomc.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.SimpleMailMessage;

import javax.mail.internet.MimeMessage;

public class MockEmailService extends AbstractEmailService {
    private static final Logger LOG = LoggerFactory.getLogger(MockEmailService.class);

    @Override
    public void sendEmail(SimpleMailMessage msg) {
        LOG.info("Simulating email sending...");
        LOG.info(msg.toString());
        LOG.info("Email sent");
    }

    @Override
    public void sendHtmlEmail(MimeMessage msg) {
        LOG.info("Simulating html email sending...");
        LOG.info(msg.toString());
        LOG.info("Email sent");
    }
}
