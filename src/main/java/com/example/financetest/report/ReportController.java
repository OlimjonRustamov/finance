package com.example.financetest.report;

import com.example.financetest.Views;
import com.example.financetest.annotation.CurrentUser;
import com.example.financetest.user.User;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/report")
public class ReportController {

    ReportService reportService;

    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @PostMapping("/insert")
    @JsonView(Views.Public.class)
    HttpEntity<?> insertReportItem(@CurrentUser User user, @Valid @RequestBody InsertReportDto dto) {
        return reportService.insertReportItem(user, dto);
    }

    @GetMapping("/list")
    @JsonView(Views.Public.class)
    HttpEntity<?> getReportItem(@RequestParam("merchant_id") long merchantId, @RequestParam int page, @RequestParam int size) {
        return reportService.getReportItemByMerchant(merchantId, page - 1, size);
    }
}
