package org.comAndDev.companyservice.web;

import lombok.Data;
import org.comAndDev.companyservice.entities.Category;
import org.comAndDev.companyservice.entities.Company;
import org.comAndDev.companyservice.entities.ManagerCompany;
import org.comAndDev.companyservice.service.CompanyService;
import org.comAndDev.companyservice.service.ManagerCompanyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.persistence.Column;
import javax.persistence.Transient;
import java.net.URI;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/Company")
@CrossOrigin(origins="http://localhost:3000")
public class CompanyController {
    private static Logger logger = LoggerFactory.getLogger(CompanyController.class);

    @Autowired
    private CompanyService companyService;

    @Autowired
    private ManagerCompanyService managerCompanyService;

    @PostMapping("/Save")
    public ResponseEntity<Void> SaveCompany( @RequestBody CompanyForm companyForm ){
        Company company =    companyService.Create(companyForm.getName(), companyForm.getCategoryId());
        if ( company == null)
            return ResponseEntity.noContent().build();
//        ManagerCompany managerCompany =    managerCompanyService.Create(company.getId(), email, phone, firstName, lastName, password);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(company.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @PostMapping(value = "/Update")
    public ResponseEntity<Void> UpdateCompany(@RequestBody CompanyForm companyForm ) {

        Company company =  companyService.update(companyForm.getId(), companyForm.getName(), companyForm.getDescription(), companyForm.getLocalization(),
                                                    companyForm.getBirthdayOn(), companyForm.getLogo());

        if (company == null)
            return ResponseEntity.noContent().build();

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(company.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @PostMapping(value = "/Archived")
    public ResponseEntity<Void> ArchivedCompany(@RequestBody CompanyForm companyForm) {

        Company company =  companyService.archived(companyForm.getId());

        if (company == null)
            return ResponseEntity.noContent().build();

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(company.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @GetMapping(value = "/All")
    public List<Company> AllCompanies() {
        List<Company> companies =  companyService.AllCompanies();
        return companies;
    }

    @GetMapping(value = "/all/{id}")
    public Company findCompanyById(@PathVariable("id") Long id) {
        Company company =  companyService.getCompany(id);
        return company;
    }
}
@Data
class CompanyForm {
    private Long id;
    private String name;
    private String description;
    private boolean archived;
    private Long categoryId;
    private String localization;
    private LocalDate birthdayOn;
    private LocalDate createOn;
    private LocalDate updateOn;
    private String logo;
}
