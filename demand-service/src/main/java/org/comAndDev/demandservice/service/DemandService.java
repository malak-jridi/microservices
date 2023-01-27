package org.comAndDev.demandservice.service;

import org.comAndDev.demandservice.entities.Demand;
import org.springframework.http.ResponseEntity;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

public interface DemandService {
    public ResponseEntity<String> save(String company_name, Long categoryId, String company_manager_name, String email, String phone ,
                                       String first_name, String last_name) throws MessagingException, IOException;
    public ResponseEntity<String> validate(Long id) throws MessagingException, IOException;
    public Demand archived(Long id);
    public List<Demand> AllDemand();
    public Demand getDemand(Long id) ;
    public ResponseEntity<String> CreatePassword (Long id, String password, String confirmedPassword );


    }
