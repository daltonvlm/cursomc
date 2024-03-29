package com.daltonvlm.cursomc.dto;

import com.daltonvlm.cursomc.domain.Client;
import com.daltonvlm.cursomc.services.validation.ClientUpdate;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@ClientUpdate
public class ClientDTO implements Serializable {
    private Integer id;

    @NotEmpty(message = "Field required")
    @Length(min = 5, max = 120, message = "Size must be between 5 and 120 characters")
    private String name;

    @NotEmpty(message = "Field required")
    @Email(message = "Invalid email")
    private String email;

    public ClientDTO() {
    }

    public ClientDTO(Client client) {
        this.id = client.getId();
        this.name = client.getName();
        this.email = client.getEmail();
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
