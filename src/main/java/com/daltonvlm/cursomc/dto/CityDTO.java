package com.daltonvlm.cursomc.dto;

import com.daltonvlm.cursomc.domain.City;

import java.io.Serializable;

public class CityDTO implements Serializable {
    private Integer id;
    private String name;

    public CityDTO() {
    }

    public CityDTO(City city) {
        id = city.getId();
        name = city.getName();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
