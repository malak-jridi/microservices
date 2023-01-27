package org.comAndDev.companyservice.dao;

import org.comAndDev.companyservice.entities.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface CompanyRepository extends JpaRepository<Company, Long> {
    public Company findCompanyById(Long id);
    public List<Company> findCompaniesByArchivedFalse();

}
