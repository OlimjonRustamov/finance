package com.example.financetest.merchant;

import com.example.financetest.error.CustomError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class MerchantServiceImpl implements MerchantService {

    MerchantRepository merchantRepository;

    public MerchantServiceImpl(MerchantRepository merchantRepository) {
        this.merchantRepository = merchantRepository;
    }

    @Override
    public HttpEntity<?> searchMerchantByQuery(String query) {
        return ResponseEntity.ok(merchantRepository.searchByQuery(query.toUpperCase()));
    }

    @Override
    public HttpEntity<?> insertMerchant(InsertMerchantDto dto) {
        try {
            merchantRepository.save(new Merchant(dto.getName(), dto.getPhone(), dto.getInfo()));
            return ResponseEntity.ok(new CustomError("Success", 200));
        } catch (DataIntegrityViolationException ex) {
            return ResponseEntity.status(400).body(new CustomError("This name merchant has already exists", 400));
        } catch (Exception ex) {
            return ResponseEntity.status(400).body(new CustomError(ex.getMessage(), 400));
        }
    }
}
