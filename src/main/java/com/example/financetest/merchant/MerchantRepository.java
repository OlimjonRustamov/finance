package com.example.financetest.merchant;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MerchantRepository extends JpaRepository<Merchant, Long> {

    @Query(value = "select * from merchant where " +
            "UPPER(merchant.name) like UPPER(%:query%) or " +
            "UPPER(merchant.phone) like UPPER(%:query%) or " +
            "UPPER(merchant.info) like UPPER(%:query%)", nativeQuery = true)
    List<Merchant> searchByQuery(String query);
}
