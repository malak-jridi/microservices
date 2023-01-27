package org.comAndDev.demandservice.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Transient;
import java.time.LocalDate;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Company {
    private Long id;
    private String name;
    private String description;
    private boolean archived;
    private Long categoryId;
    private String localization;
    private LocalDate birthdayOn;
    private LocalDate createOn;
    private LocalDate updateOn;
    private String logo;
}
