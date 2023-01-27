package org.comAndDev.demandservice.web;

import lombok.Data;
import org.comAndDev.demandservice.entities.Demand;
import org.comAndDev.demandservice.service.DemandService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.mail.MessagingException;
import java.io.IOException;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/demand")
@CrossOrigin(origins="http://localhost:3000")
public class DemandController {
    private static Logger logger = LoggerFactory.getLogger(DemandController.class);

    @Autowired
    private DemandService demandService;

    @PostMapping("/Save")
    public ResponseEntity<String> SaveCategory(@RequestBody DemandForm demandForm, String email ) throws MessagingException, IOException {
        ResponseEntity<String> demand =    demandService.save(demandForm.getCompanyName(), demandForm.getCategoryId(), demandForm.getCompanyManagerName() ,
                                                demandForm.getEmail(), demandForm.getPhone(), demandForm.getFirstName(), demandForm.getLastName() );
        return demand;
    }

    @PostMapping(value = "/validate")
    public ResponseEntity<String> validateDemand(@RequestBody DemandForm demandForm) throws MessagingException, IOException {
        ResponseEntity<String> demand =  demandService.validate(demandForm.getId());

        return demand;
    }

    @PostMapping(value = "/Archived")
    public ResponseEntity<Void> ArchivedDemand(@RequestBody DemandForm demandForm) {

        Demand demand =  demandService.archived(demandForm.getId());

        if (demand == null)
            return ResponseEntity.noContent().build();

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(demand.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @GetMapping(value = "/All")
    public List<Demand> AllCategories() {
        List<Demand> demands =  demandService.AllDemand();
        return demands;
    }

    @GetMapping(value = "/get/{id}")
    public Demand getDemand(@PathVariable("id") Long id) {

        Demand demand =  demandService.getDemand(id);
        return demand;
    }

    @PostMapping("/CreatePassword")
    public ResponseEntity<String> CreatePassword(@RequestBody DemandForm demandForm ) throws MessagingException, IOException {
        ResponseEntity<String> demand =    demandService.CreatePassword(demandForm.getId(), demandForm.getPassword(), demandForm.getConfirmedPassword() );
        return demand;
    }
}
@Data
class DemandForm {
    private Long id;
    private String companyName;
    private Long categoryId;
    private String companyManagerName;
    private String email;
    private String phone;
    private String firstName;
    private String lastName;
    private String password;
    private String ConfirmedPassword;
}