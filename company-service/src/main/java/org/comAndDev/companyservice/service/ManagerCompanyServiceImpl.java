package org.comAndDev.companyservice.service;

import org.comAndDev.companyservice.dao.ManagerCompanyRepository;
import org.comAndDev.companyservice.entities.Company;
import org.comAndDev.companyservice.entities.ManagerCompany;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;

@Service
@Transactional
public class ManagerCompanyServiceImpl  implements ManagerCompanyService {
    private final ManagerCompanyRepository managerCompanyRepository;

    public ManagerCompanyServiceImpl(ManagerCompanyRepository managerCompanyRepository) {
        this.managerCompanyRepository = managerCompanyRepository;
    }
    @Override
    public ManagerCompany Create(Long companyId, String email, String phone, String firstName, String lastName, String password){
        ManagerCompany managerCompany = new ManagerCompany();
        managerCompany.setCompanyId(companyId);
        managerCompany.setEmail(email);
        managerCompany.setPhone(phone);
        managerCompany.setFirstName(firstName);
        managerCompany.setLastName(lastName);
        managerCompany.setPassword(password);
        managerCompanyRepository.save(managerCompany);
        return managerCompany;
    }
    @Override
    public ResponseEntity<ManagerCompany> getManagerCompany(Long id){
        ManagerCompany managerCompany = managerCompanyRepository.findManagerCompanyById(id);
        if ( managerCompany != null ) return new ResponseEntity<>( HttpStatus.NOT_FOUND);
        return new ResponseEntity<ManagerCompany>(managerCompany, HttpStatus.OK);
    }

    @Override
    public ManagerCompany update(Long id, String email, String phone, String firstName, String lastName, String password) {
        ManagerCompany managerCompany= managerCompanyRepository.findManagerCompanyById(id);
        managerCompany.setEmail(email);
        managerCompany.setPhone(phone);
        managerCompany.setFirstName(firstName);
        managerCompany.setLastName(lastName);
        managerCompany.setPassword(password);
        managerCompany.setUpdateOn(LocalDate.now());
        managerCompanyRepository.save(managerCompany);
        return managerCompany;
    }

    @Override
    public ResponseEntity<ManagerCompany> getManagerCompanyByCompanyId(Long companyId){
        ManagerCompany managerCompany = managerCompanyRepository.findManagerCompanyByCompanyId(companyId);
        if ( managerCompany != null ) return new ResponseEntity<>( HttpStatus.NOT_FOUND);
        return new ResponseEntity<ManagerCompany>(managerCompany, HttpStatus.OK);
    }

}
