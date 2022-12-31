package com.daltonvlm.cursomc.resources;

import com.daltonvlm.cursomc.domain.City;
import com.daltonvlm.cursomc.domain.State;
import com.daltonvlm.cursomc.dto.CityDTO;
import com.daltonvlm.cursomc.dto.StateDTO;
import com.daltonvlm.cursomc.services.CityService;
import com.daltonvlm.cursomc.services.StateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/states")
public class StateResource {

    @Autowired
    private StateService service;

    @Autowired
    private CityService cityService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<StateDTO>> findAll() {
        List<State> list = service.findAll();
        List<StateDTO> listDto = list.stream().map(obj -> new StateDTO(obj)).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDto);
    }

    @RequestMapping(value = "/{stateId}/cities", method = RequestMethod.GET)
    public ResponseEntity<List<CityDTO>> findCities(@PathVariable Integer stateId) {
        List<City> list = cityService.findByState(stateId);
        List<CityDTO> listDto = list.stream().map(obj -> new CityDTO(obj)).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDto);
    }
}
