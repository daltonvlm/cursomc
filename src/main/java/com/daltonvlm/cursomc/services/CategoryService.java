package com.daltonvlm.cursomc.services;

import com.daltonvlm.cursomc.domain.Category;
import com.daltonvlm.cursomc.repositories.CategoryRepository;
import com.daltonvlm.cursomc.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository repository;

    public Category find(Integer id) {
        Optional<Category> category = repository.findById(id);
        return category.orElseThrow(() -> new ObjectNotFoundException("Object not found! Id: " + id + ", Type: " + Category.class.getName()));
    }

    public Category insert(Category category) {
        category.setId(null);
        return repository.save(category);
    }

    public Category update(Category category) {
        find(category.getId());
        return repository.save(category);
    }
}
