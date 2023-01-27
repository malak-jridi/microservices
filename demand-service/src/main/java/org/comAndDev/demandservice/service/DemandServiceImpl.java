package org.comAndDev.demandservice.service;


import org.comAndDev.demandservice.dao.DemandRepository;
import org.comAndDev.demandservice.entities.Company;
import org.comAndDev.demandservice.entities.Demand;
import org.comAndDev.demandservice.entities.Email;
import org.comAndDev.demandservice.entities.ManagerCompany;
import org.comAndDev.demandservice.proxies.CategoryProxy;
import org.comAndDev.demandservice.proxies.CompanyProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.transaction.Transactional;
import java.io.IOException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class DemandServiceImpl implements DemandService {
    private final DemandRepository demandRepository;
    @Autowired
    private final CategoryProxy categoryProxy;
    @Autowired
    private EmailSenderService mailService;
    @Autowired
    private final CompanyProxy companyProxy;

    public DemandServiceImpl(DemandRepository demandRepository, CategoryProxy categoryProxy, EmailSenderService mailService, CompanyProxy companyProxy) {
        this.demandRepository = demandRepository;
        this.categoryProxy = categoryProxy;
        this.mailService = mailService;
        this.companyProxy = companyProxy;
    }

    @Override
    public ResponseEntity<String> save(String companyName, Long categoryId, String companyManagerName, String email, String phone ,
                                       String firstName, String lastName) throws MessagingException, IOException {
        Demand demand=new Demand();
        demand.setCompanyName(companyName);
        demand.setCategoryId(categoryId);
        demand.setCompanyManagerName(companyManagerName);
        demand.setCreateOn(LocalDate.now());
        demand.setEmail(email);
        demand.setPhone(phone);
        demand.setFirstName(firstName);
        demand.setLastName(lastName);
        demand.setState("pending");
        demandRepository.save(demand);
        Email emailSend = new Email();
        emailSend.setFrom("garalimaha9@gmail.com");
        emailSend.setTo(email);
        emailSend.setSubject("FARHA - bien reçu votre demande ");
        emailSend.setName(firstName +" "+ lastName);

        Map<String, String> model = new HashMap<>();
        model.put("name", emailSend.getName());
        String response = mailService.sendEmailWithAttachment(emailSend, model, "email.ftl");
        return new ResponseEntity<String>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> validate(Long id) throws MessagingException, IOException {
        Demand demand= demandRepository.findDemandById(id);
        demand.setState("validate");
        Email emailSend = new Email();
        emailSend.setFrom("garalimaha9@gmail.com");
        emailSend.setTo(demand.getEmail());
        emailSend.setSubject("FARHA - Votre mot de passe");
        emailSend.setName(demand.getFirstName() +" "+ demand.getLastName());

        Map<String, String> model = new HashMap<>();
        model.put("name", emailSend.getName());
        String response = mailService.sendEmailWithAttachment(emailSend, model, "validateEmail.ftl");
        demandRepository.save(demand);
        return new ResponseEntity<String>(response, HttpStatus.OK);

    }

    @Override
    public Demand archived(Long id) {
        Demand demand= demandRepository.findDemandById(id);
        demand.setState("archived");
        demandRepository.save(demand);
        return demand ;
    }
    public List<Demand> AllDemand() {

        List<Demand> demands = demandRepository.findDemandByState();
        return demands;
    }
    @Override
    public Demand getDemand(Long id) {
        Demand demand= demandRepository.findDemandById(id);
        demand.setCategory(categoryProxy.findCategoryById(demand.getCategoryId()));
        return demand ;
    }

    @Override
    public ResponseEntity<String> CreatePassword (Long id, String password, String confirmedPassword ){
        Demand demand = demandRepository.findDemandById(id);
        if ( demand == null  )
        {
            return new ResponseEntity<String>("managerCompany is null", HttpStatus.NOT_FOUND);

        }
        if ( demand.getPassword() != null )
        {
            return new ResponseEntity<String>("managerCompany has password", HttpStatus.NOT_FOUND);
        }
        if (password != confirmedPassword)
        {
            return new ResponseEntity<String>("password and confirmedPassword not equal", HttpStatus.NOT_FOUND);
        }

        demand.setPassword(password);
        demandRepository.save(demand);
        Company company=new Company();
        company.setName(demand.getCompanyName());
        company.setCategoryId(demand.getCategoryId());
        companyProxy.SaveCompany( company );
        ManagerCompany managerCompany = new ManagerCompany();
        managerCompany.setCompanyId(company.getId());
        managerCompany.setEmail(demand.getEmail());
        managerCompany.setPhone(demand.getPhone());
        managerCompany.setFirstName(demand.getFirstName());
        managerCompany.setLastName(demand.getLastName());
        managerCompany.setPassword(demand.getPassword());
        companyProxy.SaveManagerCompany( managerCompany );
        return new ResponseEntity<String>("OK", HttpStatus.OK);

    }
}
