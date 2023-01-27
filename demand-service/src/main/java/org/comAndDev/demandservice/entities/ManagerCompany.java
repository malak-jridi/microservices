package org.comAndDev.demandservice.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ManagerCompany {
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
