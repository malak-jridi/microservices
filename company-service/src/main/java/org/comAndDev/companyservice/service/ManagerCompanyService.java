package org.comAndDev.companyservice.service;

import org.comAndDev.companyservice.entities.ManagerCompany;
import org.springframework.http.ResponseEntity;

public interface ManagerCompanyService {
    public ManagerCompany Create(Long companyId, String email, String phone, String firstName, String lastName, String password);
    public ResponseEntity<ManagerCompany> getManagerCompany(Long id);
    public ResponseEntity<ManagerCompany> getManagerCompanyByCompanyId(Long companyId);
    public ManagerCompany update(Long id, String email, String phone, String firstName, String lastName, String password);
    }