package org.comAndDev.companyservice.dao;

import org.comAndDev.companyservice.entities.ManagerCompany;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface ManagerCompanyRepository extends JpaRepository<ManagerCompany, Long> {
    public ManagerCompany findManagerCompanyById(Long id);
    public ManagerCompany findManagerCompanyByEmail(String email);
    public ManagerCompany findManagerCompanyByCompanyId(Long companyId);

}
