package com.example.financetest.report;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReportRepository extends JpaRepository<ReportItem, Integer> {

    List<ReportItem> findByMerchant_Id(long merchant_id, Pageable pageable);

}
