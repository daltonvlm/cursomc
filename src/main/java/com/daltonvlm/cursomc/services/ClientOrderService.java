package com.daltonvlm.cursomc.services;

import com.daltonvlm.cursomc.domain.ClientOrder;
import com.daltonvlm.cursomc.repositories.ClientOrderRepository;
import com.daltonvlm.cursomc.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClientOrderService {
    @Autowired
    private ClientOrderRepository repository;

    public ClientOrder find(Integer id) {
        Optional<ClientOrder> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Object not found! Id: " + id + ", Type: " + ClientOrder.class.getName()));
    }
}
