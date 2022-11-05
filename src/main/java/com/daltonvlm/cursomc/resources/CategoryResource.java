package com.daltonvlm.cursomc.resources;

import com.daltonvlm.cursomc.domain.Category;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping(value = "/categories")
public class CategoryResource {

    @RequestMapping(method = RequestMethod.GET)
    public List<Category> list() {
        Category c1 = new Category(1, "Informatics");
        Category c2 = new Category(2, "Office");

        List<Category> lst = new ArrayList<>();
        lst.addAll(Arrays.asList(c1, c2));

        return lst;
    }
}
