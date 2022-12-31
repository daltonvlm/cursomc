package com.daltonvlm.cursomc.services;

import com.daltonvlm.cursomc.domain.State;
import com.daltonvlm.cursomc.repositories.StateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StateService {
    @Autowired
    private StateRepository repository;

    public List<State> findAll() {
        return repository.findAllByOrderByName();
    }
}
