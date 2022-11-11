package com.daltonvlm.cursomc.services;

import com.daltonvlm.cursomc.domain.Client;
import com.daltonvlm.cursomc.dto.ClientDTO;
import com.daltonvlm.cursomc.repositories.ClientRepository;
import com.daltonvlm.cursomc.services.exceptions.DataIntegrityException;
import com.daltonvlm.cursomc.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {
    @Autowired
    private ClientRepository repository;

    public Client find(Integer id) {
        Optional<Client> client = repository.findById(id);
        return client.orElseThrow(() -> new ObjectNotFoundException("Object not found! Id: " + id + ", Type: " + Client.class.getName()));
    }

    private void updateData(Client newClient, Client client) {
        newClient.setName(client.getName());
        newClient.setEmail(client.getEmail());
    }

    public Client update(Client client) {
        Client newClient = find(client.getId());
        updateData(newClient, client);
        return repository.save(newClient);
    }

    public void delete(Integer id) {
        find(id);
        try {
            repository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("It is not possible to remove a Client with related entities.");
        }
    }

    public List<Client> findAll() {
        return repository.findAll();
    }

    public Page<Client> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
        return repository.findAll(pageRequest);
    }

    public Client fromDTO(ClientDTO clientDTO) {
        return new Client(clientDTO.getId(), clientDTO.getName(), clientDTO.getEmail(), null, null);
    }
}
