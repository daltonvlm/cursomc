package com.daltonvlm.cursomc.services;

import com.daltonvlm.cursomc.domain.Client;
import com.daltonvlm.cursomc.repositories.ClientRepository;
import com.daltonvlm.cursomc.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class AuthService {
    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private BCryptPasswordEncoder pe;

    @Autowired
    private EmailService emailService;

    private Random rand = new Random();

    public void sendNewPassword(String email) {
        Client client = clientRepository.findByEmail(email);
        if (client == null) {
            throw new ObjectNotFoundException("Email not found");
        }
        String newPass = newPassword();
        client.setPassword(pe.encode(newPass));

        clientRepository.save(client);
        emailService.sendNewPasswordEmail(client, newPass);
    }

    private String newPassword() {
        char[] vet = new char[10];
        for (int i = 0; i < vet.length; i++) {
            vet[i] = randomChar();
        }
        return new String(vet);
    }

    private char randomChar() {
        int opt = rand.nextInt(3);

        switch (opt) {
            case 0:
                return (char) (rand.nextInt(10) + 48);
            case 1:
                return (char) (rand.nextInt(26) + 65);
            default:
                return (char) (rand.nextInt(26) + 97);
        }
    }
}
