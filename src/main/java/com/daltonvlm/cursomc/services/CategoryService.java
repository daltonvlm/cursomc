package com.daltonvlm.cursomc.services;

import com.daltonvlm.cursomc.domain.Category;
import com.daltonvlm.cursomc.dto.CategoryDTO;
import com.daltonvlm.cursomc.repositories.CategoryRepository;
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
public class CategoryService {
    @Autowired
    private CategoryRepository repository;

    public Category find(Integer id) {
        Optional<Category> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Object not found! Id: " + id + ", Type: " + Category.class.getName()));
    }

    public Category insert(Category obj) {
        obj.setId(null);
        return repository.save(obj);
    }

    private void updateData(Category newObj, Category obj) {
        newObj.setName(obj.getName());
    }

    public Category update(Category obj) {
        Category newObj = find(obj.getId());
        updateData(newObj, obj);
        return repository.save(newObj);
    }

    public void delete(Integer id) {
        find(id);
        try {
            repository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("It is not possible to remove a Category containing products");
        }
    }

    public List<Category> findAll() {
        return repository.findAll();
    }

    public Page<Category> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
        return repository.findAll(pageRequest);
    }

    public Category fromDTO(CategoryDTO objDto) {
        return new Category(objDto.getId(), objDto.getName());
    }
}
