package com.example.financetest.merchant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/merchant")
public class MerchantController {

    MerchantService merchantService;

    public MerchantController(MerchantService merchantService) {
        this.merchantService = merchantService;
    }

    @GetMapping("/search")
    public HttpEntity<?> searchMerchantByQuery(@RequestParam("query") String query) {
        return merchantService.searchMerchantByQuery(query);
    }

    @PostMapping("/insert")
    public HttpEntity<?> insertMerchant(@Valid @RequestBody InsertMerchantDto dto) {
        return merchantService.insertMerchant(dto);
    }
}
