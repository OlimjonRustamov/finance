package com.example.financetest.report;

import com.example.financetest.error.CustomError;
import com.example.financetest.image.Image;
import com.example.financetest.image.ImageRepository;
import com.example.financetest.merchant.Merchant;
import com.example.financetest.merchant.MerchantRepository;
import com.example.financetest.user.User;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ReportServiceImpl implements ReportService {

    ReportRepository reportRepository;
    MerchantRepository merchantRepository;
    ImageRepository imageRepository;

    public ReportServiceImpl(ReportRepository reportRepository, MerchantRepository merchantRepository, ImageRepository imageRepository) {
        this.reportRepository = reportRepository;
        this.merchantRepository = merchantRepository;
        this.imageRepository = imageRepository;
    }

    @Override
    public HttpEntity<?> insertReportItem(User user, InsertReportDto dto) {
        if (!dto.getCurrency().equalsIgnoreCase("uzs") && !dto.getCurrency().equalsIgnoreCase("usd")) {
            return ResponseEntity.status(400).body(new CustomError("Currency must be either USD or UZS"));
        }
        if (dto.getCurrency().equalsIgnoreCase("usd") && dto.getCurrencyRate() == 0.0) {
            return ResponseEntity.status(400).body(new CustomError("Please provide currency_rate present time"));
        }

        Optional<Merchant> optionalMerchant = merchantRepository.findById(dto.getMerchantId());
        if(!optionalMerchant.isPresent())
            return ResponseEntity.status(404).body(new CustomError("Merchant not found", 404));
        Merchant merchant = optionalMerchant.get();

        ReportItem reportItem = new ReportItem();

        if (dto.getImageId() != null) {
            Optional<Image> optionalImage = imageRepository.findById(dto.getImageId());
            if (!optionalImage.isPresent())
                return ResponseEntity.status(404).body(new CustomError("Image not found", 404));
            Image image = optionalImage.get();
            reportItem.setImage(image);
        }

        reportItem.setCreator(user);
        reportItem.setAmount(dto.getAmount());
        reportItem.setCurrency(dto.getCurrency());
        reportItem.setMerchant(merchant);
        reportItem.setPaid(dto.getPaid());
        reportItem.setSaldo(dto.getAmount() - dto.getPaid());
        reportItem.setCurrencyRate(dto.getCurrencyRate());
        reportItem.setNote(dto.getNote());
        reportItem = reportRepository.save(reportItem);
        return ResponseEntity.ok(reportItem);
    }

    @Override
    public HttpEntity<?> getReportItemByMerchant(long merchantId, int page, int size) {
        try {
            return ResponseEntity.ok(reportRepository.findByMerchant_Id(merchantId, PageRequest.of(page, size)));
        } catch (Exception exception) {
            return ResponseEntity.status(400).body(new CustomError(exception.getMessage(), 400));
        }
    }
}
