package com.cydeo.service;

import com.cydeo.dto.CompanyDto;
import com.cydeo.dto.InvoiceDto;
import com.cydeo.enums.InvoiceType;

import java.math.BigDecimal;
import java.util.List;

public interface InvoiceService {

    InvoiceDto findById(Long id);

    List<InvoiceDto> listOfAllInvoices();


    InvoiceDto update(InvoiceDto invoiceDto, Long id);

    InvoiceDto save(InvoiceDto invoiceDto);

    InvoiceDto saveSalesInvoice(InvoiceDto invoiceDto);

    InvoiceDto delete(Long id);

    InvoiceDto approveSalesInvoice(Long id);

    InvoiceDto approvePurchaseInvoice(Long id);

    InvoiceDto createNewSalesInvoice();

    InvoiceDto createNewPurchasesInvoice();

    List<InvoiceDto> calculateInvoiceSummariesAndShowInvoiceListByType(InvoiceType type);

    InvoiceDto calculateInvoiceSummary(InvoiceDto invoiceDto);

    InvoiceDto getInvoiceForPrint(Long id);

    CompanyDto getCurrentCompany();

    BigDecimal calculateTotalCost();

    BigDecimal calculateTotalSales();

    List<InvoiceDto> list3LastApprovedInvoices();

}
