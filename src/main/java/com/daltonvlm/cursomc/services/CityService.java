package com.daltonvlm.cursomc.services;

import com.daltonvlm.cursomc.domain.City;
import com.daltonvlm.cursomc.repositories.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityService {
    @Autowired
    private CityRepository repository;

    public List<City> findByState(Integer stateId) {
        return repository.findCities(stateId);
    }
}
