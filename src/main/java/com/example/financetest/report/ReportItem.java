package com.example.financetest.report;

import com.example.financetest.Views;
import com.example.financetest.image.Image;
import com.example.financetest.merchant.Merchant;
import com.example.financetest.user.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity(name = "report")
public class ReportItem {

    @Id
    @JsonView(Views.Public.class)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @JsonProperty("created_at")
    @JsonView(Views.Public.class)
    private long createdAt = System.currentTimeMillis();

    @JsonProperty("updated_at")
    @JsonView(Views.Public.class)
    private long updatedAt = System.currentTimeMillis();

    @JsonView(Views.Public.class)
    private long amount;

    @JsonView(Views.Public.class)
    private long paid;

    @JsonView(Views.Public.class)
    private long saldo;

    @ManyToOne
    @JsonView(Views.Public.class)
    private Merchant merchant;

    @ManyToOne
    @JsonView(Views.Public.class)
    private User creator;

    @OneToOne
    @JsonView(Views.Public.class)
    private Image image;

    @JsonView(Views.Public.class)
    private String currency;

    @JsonView(Views.Public.class)
    @JsonProperty("currency_rate")
    private float currencyRate;

    @JsonView(Views.Public.class)
    private String note;

}
