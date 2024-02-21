package com.mziuri.model;

import jakarta.persistence.*;

@Entity
@Table(name = "storage")
public class Storage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "password")
    private String password;

    public Storage() {
    }

    public Storage(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }
}
