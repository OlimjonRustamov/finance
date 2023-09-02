package com.example.financetest.merchant;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Merchant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String phone;

    @Column(unique = true)
    private String name;

    @Column
    private String info;

    public Merchant() {

    }

    public Merchant(String name, String phone, String info) {
        this.name = name;
        this.phone = phone;
        this.info = info;
    }

}
