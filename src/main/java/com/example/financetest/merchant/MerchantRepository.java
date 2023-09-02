package com.example.financetest.merchant;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MerchantRepository extends JpaRepository<Merchant, Long> {

    @Query(value = "select * from merchant where " +
            "merchant.name like %:query% or merchant.phone like %:query% or " +
            "merchant.info like %:query%", nativeQuery = true)
    List<Merchant> searchByQuery(String query);
}
