package com.example.financetest.merchant;

import org.springframework.http.HttpEntity;

import java.util.List;

public interface MerchantService {

    HttpEntity<?> searchMerchantByQuery(String query);

    HttpEntity<?> insertMerchant(InsertMerchantDto dto);

}
