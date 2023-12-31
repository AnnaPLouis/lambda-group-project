package com.cydeo.controller;

import com.cydeo.dto.CompanyDto;
import com.cydeo.service.AddressService;
import com.cydeo.service.CompanyService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/companies")
public class CompanyController {

    private final CompanyService companyService;
    private final AddressService addressService;

    public CompanyController(CompanyService companyService, AddressService addressService) {
        this.companyService = companyService;
        this.addressService = addressService;
    }

    @GetMapping("/list")
    public String listCompanies(Model model) {

        model.addAttribute("companies", companyService.listAllCompanies());

        return "/company/company-list";

    }

    @GetMapping("/update/{id}")
    public String editCompany(@PathVariable("id") Long id, Model model) {

        model.addAttribute("company", companyService.findById(id));
        model.addAttribute("countries", addressService.getCountryList());

        return "/company/company-update";

    }

    @PostMapping("/update/{id}")
    public String saveUpdatedCompany(@ModelAttribute("company") @Valid CompanyDto companyDto, BindingResult bindingResult,
                                     @PathVariable("id") Long id, Model model) {


        if (companyService.isTitleExist(companyDto)) {
            bindingResult.rejectValue("title", " ", "This title already exists");
        }


        if (bindingResult.hasErrors()) {
            model.addAttribute("countries", addressService.getCountryList());
            return "/company/company-update";

        }

        companyService.updateById(id, companyDto);

        return "redirect:/companies/list";

    }


    @GetMapping("/create")
    public String createCompany(Model model) {

        model.addAttribute("newCompany", new CompanyDto());
        model.addAttribute("countries", addressService.getCountryList());

        return "/company/company-create";

    }

    @PostMapping("/create")
    public String saveCompany(@ModelAttribute("newCompany") @Valid CompanyDto companyDto, BindingResult bindingResult,
                              RedirectAttributes redirectAttributes, Model model) {

        if (companyService.isTitleExist(companyDto)) {
            bindingResult.rejectValue("title", " ", "This title already exists.");
        }

        if (bindingResult.hasErrors()) {
            model.addAttribute("countries", addressService.getCountryList());
            return "/company/company-create";
        }

        if (companyDto.getAddress().getCountry().isBlank()) {
            redirectAttributes.addFlashAttribute("error", "Please select a country.");
            return "redirect:/companies/create";
        }

        companyService.save(companyDto);

        return "redirect:/companies/list";

    }


    @GetMapping("/activate/{id}")
    public String activateCompany(@PathVariable("id") Long id, Model model) {

        companyService.activateCompanyById(id);

        return "redirect:/companies/list";

    }

    @GetMapping("/deactivate/{id}")
    public String deactivateCompany(@PathVariable("id") Long id, Model model) {


        companyService.deactivateCompanyById(id);

        return "redirect:/companies/list";

    }


}
