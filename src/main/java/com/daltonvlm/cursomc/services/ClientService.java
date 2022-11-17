package com.daltonvlm.cursomc.services;

import com.daltonvlm.cursomc.domain.Address;
import com.daltonvlm.cursomc.domain.City;
import com.daltonvlm.cursomc.domain.Client;
import com.daltonvlm.cursomc.domain.enums.ClientType;
import com.daltonvlm.cursomc.dto.ClientDTO;
import com.daltonvlm.cursomc.dto.ClientNewDTO;
import com.daltonvlm.cursomc.repositories.AddressRepository;
import com.daltonvlm.cursomc.repositories.ClientRepository;
import com.daltonvlm.cursomc.services.exceptions.DataIntegrityException;
import com.daltonvlm.cursomc.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {
    @Autowired
    private ClientRepository repository;

    @Autowired
    private AddressRepository addressRepository;

    public Client find(Integer id) {
        Optional<Client> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Object not found! Id: " + id + ", Type: " + Client.class.getName()));
    }

    @Transactional
    public Client insert(Client obj) {
        obj.setId(null);
        obj = repository.save(obj);
        addressRepository.saveAll(obj.getAddresses());
        return obj;
    }

    private void updateData(Client newObj, Client obj) {
        newObj.setName(obj.getName());
        newObj.setEmail(obj.getEmail());
    }

    public Client update(Client obj) {
        Client newObj = find(obj.getId());
        updateData(newObj, obj);
        return repository.save(newObj);
    }

    public void delete(Integer id) {
        find(id);
        try {
            repository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("It is not possible to remove a Client with associated orders");
        }
    }

    public List<Client> findAll() {
        return repository.findAll();
    }

    public Page<Client> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
        return repository.findAll(pageRequest);
    }

    public Client fromDTO(ClientDTO objDto) {
        return new Client(objDto.getId(), objDto.getName(), objDto.getEmail(), null, null);
    }

    public Client fromDTO(ClientNewDTO objDto) {
        Client client = new Client(null, objDto.getName(), objDto.getEmail(), objDto.getCpfOrCnpj(), ClientType.toEnum(objDto.getType()));
        City city = new City(objDto.getCityId(), null, null);
        Address address = new Address(
                null, objDto.getPublicArea(), objDto.getNumber(), objDto.getComplement(), objDto.getDistrict(), objDto.getZipCode(), client, city);

        client.getAddresses().add(address);
        client.getPhones().add(objDto.getPhone1());
        if (objDto.getPhone2() != null) {
            client.getPhones().add(objDto.getPhone2());
        }
        if (objDto.getPhone3() != null) {
            client.getPhones().add(objDto.getPhone3());
        }
        return client;
    }
}
