package com.modularbank.services.entity.accounts;

import javax.persistence.*;

@Table(name = "account_data")
@Entity
public class AccountDataEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "email")
    private String email;
    @Column(name = "city")
    private String city;

    public AccountDataEntity() {
    }

    public AccountDataEntity(Long id, String name, String email, String city) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.city = city;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
