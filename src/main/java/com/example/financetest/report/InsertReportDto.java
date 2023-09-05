package com.example.financetest.report;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class InsertReportDto {

    @NotNull
    @JsonProperty("merchant_id")
    private long merchantId;

    @NotNull
    private long amount;

    @NotNull
    private long paid;

    @NotNull
    private String currency;

    @JsonProperty("currency_rate")
    private float currencyRate;

    @JsonProperty("image_id")
    private Long imageId;

    private String note;
}
