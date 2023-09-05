package com.example.financetest.report;

import com.example.financetest.user.User;
import org.springframework.http.HttpEntity;

public interface ReportService {

    HttpEntity<?> insertReportItem(User user, InsertReportDto dto);

    HttpEntity<?> getReportItemByMerchant(long merchantId, int page, int size);

}
