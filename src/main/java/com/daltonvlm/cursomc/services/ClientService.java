package com.daltonvlm.cursomc.services;

import com.daltonvlm.cursomc.domain.Client;
import com.daltonvlm.cursomc.repositories.ClientRepository;
import com.daltonvlm.cursomc.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClientService {
    @Autowired
    private ClientRepository repository;

    public Client find(Integer id) {
        Optional<Client> client = repository.findById(id);
        return client.orElseThrow(() -> new ObjectNotFoundException("Object not found! Id: " + id + ", Type: " + Client.class.getName()));
    }
}
