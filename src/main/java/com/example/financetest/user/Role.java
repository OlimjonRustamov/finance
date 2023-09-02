package com.example.financetest.user;

import com.example.financetest.Views;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class Role{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(Views.Public.class)
    int id;

    @JsonIgnore
    private long createdAt = System.currentTimeMillis();

    @JsonIgnore
    private long updatedAt = System.currentTimeMillis();

    @Column(nullable = false, unique = true)
    @JsonView(Views.Public.class)
    String name;

    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    @JsonIgnore
    private List<Permission> permissionList;
}

