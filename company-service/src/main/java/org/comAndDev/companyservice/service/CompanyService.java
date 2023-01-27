package org.comAndDev.companyservice.service;

import org.comAndDev.companyservice.entities.Company;

import java.time.LocalDate;
import java.util.List;

public interface CompanyService {

    public Company Create(String name, Long categoryId);
    public Company update(Long id, String name, String description, String localization,
                          LocalDate birthdayOn, String logo );
    public Company archived(Long id);
    public List<Company> AllCompanies();
    public Company getCompany(Long id);

}
