package com.daltonvlm.cursomc.resources;

import com.daltonvlm.cursomc.domain.Client;
import com.daltonvlm.cursomc.dto.ClientDTO;
import com.daltonvlm.cursomc.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/clients")
public class ClientResource {
    @Autowired
    private ClientService service;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Client> find(@PathVariable Integer id) {
        Client client = service.find(id);
        return ResponseEntity.ok().body(client);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Void> update(@Valid @RequestBody ClientDTO clientDto, @PathVariable Integer id) {
        Client client = service.fromDTO(clientDto);
        client.setId(id);
        service.update(client);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<ClientDTO>> findAll() {
        List<Client> clients = service.findAll();
        List<ClientDTO> clientsDto = clients.stream().map(client -> new ClientDTO(client)).collect(Collectors.toList());
        return ResponseEntity.ok().body(clientsDto);
    }

    @RequestMapping(value = "/page", method = RequestMethod.GET)
    public ResponseEntity<Page<ClientDTO>> findPage(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
            @RequestParam(value = "orderBy", defaultValue = "name") String orderBy,
            @RequestParam(value = "direction", defaultValue = "ASC") String direction
    ) {
        Page<Client> clients = service.findPage(page, linesPerPage, orderBy, direction);
        Page<ClientDTO> clientsDto = clients.map(client -> new ClientDTO(client));
        return ResponseEntity.ok().body(clientsDto);
    }
}
