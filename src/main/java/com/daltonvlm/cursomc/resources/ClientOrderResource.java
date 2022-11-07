package com.daltonvlm.cursomc.resources;

import com.daltonvlm.cursomc.domain.ClientOrder;
import com.daltonvlm.cursomc.services.ClientOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/orders")
public class ClientOrderResource {
    @Autowired
    private ClientOrderService service;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> find(@PathVariable Integer id) {
        ClientOrder order = service.find(id);
        return ResponseEntity.ok().body(order);
    }
}
