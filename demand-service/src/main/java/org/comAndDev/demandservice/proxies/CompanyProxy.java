package org.comAndDev.demandservice.proxies;

import org.comAndDev.demandservice.entities.Category;
import org.comAndDev.demandservice.entities.Company;
import org.comAndDev.demandservice.entities.ManagerCompany;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name= "company-service")

public interface CompanyProxy {

    @PostMapping("/Company/Save")
    public ResponseEntity<Void> SaveCompany(@RequestBody Company company);

    @PostMapping("/ManagerCompany/Save")
    public ResponseEntity<Void> SaveManagerCompany(@RequestBody ManagerCompany managerCompany);

}
