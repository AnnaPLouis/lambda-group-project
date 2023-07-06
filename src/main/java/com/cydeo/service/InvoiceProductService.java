package com.cydeo.service;

import com.cydeo.dto.InvoiceProductDto;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface InvoiceProductService {
    InvoiceProductDto findById(Long id);
    List<InvoiceProductDto> findAll();

    InvoiceProductDto save(InvoiceProductDto invoiceProductDto, Long id);
    List<InvoiceProductDto> findByInvoiceId(Long id);
    InvoiceProductDto deleteInvoiceProduct(Long invoiceId, Long productId);

    Map<String, BigDecimal> listMonthlyProfitLoss();
}
