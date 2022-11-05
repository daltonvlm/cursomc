package com.daltonvlm.cursomc.services;

import com.daltonvlm.cursomc.domain.Category;
import com.daltonvlm.cursomc.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository repository;

    public Category find(Integer id) {
        Optional<Category> category = repository.findById(id);
        return category.orElse(null);
    }
}
