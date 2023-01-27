package org.comAndDev.demandservice.dao;

import org.comAndDev.demandservice.entities.Demand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;
import java.util.Optional;

@RepositoryRestResource
public interface  DemandRepository extends JpaRepository<Demand, Long> {
    public Demand findDemandById(Long id);

    @Query(value = "SELECT t FROM Demand t WHERE t.state = 'pending'")
    public List<Demand> findDemandByState();
}