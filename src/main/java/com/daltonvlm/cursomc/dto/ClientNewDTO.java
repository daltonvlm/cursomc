package com.daltonvlm.cursomc.dto;

import com.daltonvlm.cursomc.services.validation.ClientInsertion;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@ClientInsertion
public class ClientNewDTO implements Serializable {
    @NotEmpty(message = "Field required")
    @Length(min = 5, max = 120, message = "Size must be between 5 and 120 characters")
    private String name;

    @NotEmpty(message = "Field required")
    @Email(message = "Invalid email")
    private String email;

    @NotEmpty(message = "Field required")

    private String cpfOrCnpj;
    private Integer type;

    @NotEmpty(message = "Field required")
    private String publicArea;

    @NotEmpty(message = "Field required")
    private String number;
    private String complement;
    private String district;

    @NotEmpty(message = "Field required")
    private String zipCode;

    @NotEmpty(message = "Field required")
    private String phone1;
    private String phone2;
    private String phone3;

    private Integer cityId;

    public ClientNewDTO() {
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

    public String getCpfOrCnpj() {
        return cpfOrCnpj;
    }

    public void setCpfOrCnpj(String cpfOrCnpj) {
        this.cpfOrCnpj = cpfOrCnpj;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getPublicArea() {
        return publicArea;
    }

    public void setPublicArea(String publicArea) {
        this.publicArea = publicArea;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getComplement() {
        return complement;
    }

    public void setComplement(String complement) {
        this.complement = complement;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getPhone1() {
        return phone1;
    }

    public void setPhone1(String phone1) {
        this.phone1 = phone1;
    }

    public String getPhone2() {
        return phone2;
    }

    public void setPhone2(String phone2) {
        this.phone2 = phone2;
    }

    public String getPhone3() {
        return phone3;
    }

    public void setPhone3(String phone3) {
        this.phone3 = phone3;
    }

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }
}
