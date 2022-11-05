package com.daltonvlm.cursomc;

import com.daltonvlm.cursomc.domain.Category;
import com.daltonvlm.cursomc.domain.City;
import com.daltonvlm.cursomc.domain.Product;
import com.daltonvlm.cursomc.domain.State;
import com.daltonvlm.cursomc.repositories.CategoryRepository;
import com.daltonvlm.cursomc.repositories.CityRepository;
import com.daltonvlm.cursomc.repositories.ProductRepository;
import com.daltonvlm.cursomc.repositories.StateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.JpaRepository;

import java.lang.reflect.Array;
import java.util.Arrays;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner {
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private StateRepository stateRepository;
    @Autowired
    private CityRepository cityRepository;

    public static void main(String[] args) {
        SpringApplication.run(CursomcApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Category informatics = new Category(null, "Informatics");
        Category office = new Category(null, "Office");

        Product computer = new Product(null, "Computer", 2000.00);
        Product printer = new Product(null, "Printer", 800.00);
        Product mouse = new Product(null, "Mouse", 80.00);

        informatics.getProducts().addAll(Arrays.asList(computer, printer, mouse));
        office.getProducts().addAll(Arrays.asList(printer));

        computer.getCategories().addAll(Arrays.asList(informatics));
        printer.getCategories().addAll(Arrays.asList(informatics, office));
        mouse.getCategories().addAll(Arrays.asList(informatics));

        categoryRepository.saveAll(Arrays.asList(informatics, office));
        productRepository.saveAll(Arrays.asList(computer, printer, mouse));

        State minasGerais = new State(null, "Minas Gerais");
        State saoPaulo = new State(null, "São Paulo");

        City uberlandia = new City(null, "Uberlândia", minasGerais);
        City saoPauloCity = new City(null, "São Paulo", saoPaulo);
        City campinas = new City(null, "Campinas", saoPaulo);

        minasGerais.getCities().addAll(Arrays.asList(uberlandia));
        saoPaulo.getCities().addAll(Arrays.asList(saoPauloCity, campinas));

        stateRepository.saveAll(Arrays.asList(minasGerais, saoPaulo));
        cityRepository.saveAll(Arrays.asList(uberlandia, saoPauloCity, campinas));
    }
}
