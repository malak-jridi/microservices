package org.comAndDev.companyservice.web;

import lombok.Data;
import org.comAndDev.companyservice.entities.Company;
import org.comAndDev.companyservice.entities.ManagerCompany;
import org.comAndDev.companyservice.service.ManagerCompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.time.LocalDate;

@RestController
@RequestMapping("/ManagerCompany")
@CrossOrigin(origins="http://localhost:3000")
public class ManagerCompanyController {

    @Autowired
    private ManagerCompanyService managerCompanyService;

    @PostMapping("/Save")
    public ResponseEntity<Void> SaveCompany(@RequestBody ManagerCompanyForm managerCompanyForm ){

        ManagerCompany managerCompany =    managerCompanyService.Create(managerCompanyForm.getCompanyId(), managerCompanyForm.getEmail(), managerCompanyForm.getPhone(), managerCompanyForm.getFirstName(), managerCompanyForm.getLastName(), managerCompanyForm.getPassword());

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(managerCompany.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @PostMapping(value = "/Update")
    public ResponseEntity<Void> UpdateCompany(@RequestBody ManagerCompanyForm managerCompanyForm ) {

        ManagerCompany managerCompany =  managerCompanyService.update(managerCompanyForm.getId(), managerCompanyForm.getEmail(), managerCompanyForm.getPhone(), managerCompanyForm.getFirstName(), managerCompanyForm.getLastName(), managerCompanyForm.getPassword());

        if (managerCompany == null)
            return ResponseEntity.noContent().build();

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(managerCompany.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }
    @GetMapping(value = "/all/{id}")
    public ResponseEntity<ManagerCompany> findManagerCompanyById(@PathVariable("companyId") Long companyId) {
        ResponseEntity<ManagerCompany> managerCompany =  managerCompanyService.getManagerCompanyByCompanyId(companyId);
        return managerCompany;
    }
}
@Data
class ManagerCompanyForm {
    private Long id;
    private Long companyId;
    private String email;
    private String phone;
    private String firstName;
    private String lastName;
    private String password;
    private LocalDate createOn;
    private LocalDate updateOn;
    private boolean archived;
}
