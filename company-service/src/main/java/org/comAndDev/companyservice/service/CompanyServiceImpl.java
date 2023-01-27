package org.comAndDev.companyservice.service;

import org.comAndDev.companyservice.dao.CompanyRepository;
import org.comAndDev.companyservice.entities.Company;
import org.comAndDev.companyservice.proxies.CategoryProxy;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
public class CompanyServiceImpl  implements CompanyService  {

    private final CompanyRepository companyRepository;
    private final CategoryProxy categoryProxy;

    public CompanyServiceImpl(CompanyRepository companyRepository, CategoryProxy categoryProxy) {
        this.companyRepository = companyRepository;
        this.categoryProxy = categoryProxy;
    }

     @Override
    public Company Create(String name, Long categoryId) {
        Company company=new Company();
        company.setName(name);
        company.setCategoryId(categoryId);
        company.setCreateOn(LocalDate.now());
        companyRepository.save(company);
        return company ;
    }

    @Override
    public Company update(Long id, String name, String description, String localization, LocalDate birthdayOn, String logo) {
        Company company= companyRepository.findCompanyById(id);
        company.setName(name);
        company.setDescription(description);
        company.setLocalization(localization);
        company.setBirthdayOn(birthdayOn);
        company.setLogo(logo);
        company.setUpdateOn(LocalDate.now());
        companyRepository.save(company);
        return company;
    }

    @Override
    public Company archived(Long id) {
        Company company= companyRepository.findCompanyById(id);
        company.setArchived(true);
        return company ;
    }

    @Override
    public List<Company> AllCompanies() {
        List<Company> companies= companyRepository.findCompaniesByArchivedFalse();
        return companies;
    }

    @Override
    public Company getCompany(Long id) {
        Company company= companyRepository.findCompanyById(id);
        company.setCategory(categoryProxy.findCategoryById(company.getCategoryId()));
        return company ;
    }
}
