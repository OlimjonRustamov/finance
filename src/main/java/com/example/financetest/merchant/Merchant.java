package com.example.financetest.merchant;

import com.example.financetest.Views;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Merchant {

    @Id
    @JsonView(Views.Public.class)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    @JsonView(Views.Public.class)
    private String phone;

    @Column(unique = true)
    @JsonView(Views.Public.class)
    private String name;

    @Column
    @JsonView(Views.Public.class)
    private String info;

    public Merchant() {

    }

    public Merchant(String name, String phone, String info) {
        this.name = name;
        this.phone = phone;
        this.info = info;
    }

}
