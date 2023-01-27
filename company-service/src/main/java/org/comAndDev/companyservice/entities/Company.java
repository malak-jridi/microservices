package org.comAndDev.companyservice.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column( name = "name")
    private String name;
    @Column(name = "description")
    private String description;
    @Column(name = "is_archived", columnDefinition = "boolean default false")
    private boolean archived;
    @Column(name = "category_id")
    private Long categoryId;
    @Transient private Category category;
    @Column(name = "localization")
    private String localization;
    @Column(name = "birthday_on")
    private LocalDate birthdayOn;
    @Column(name = "create_on")
    private LocalDate createOn;
    @Column(name = "update_on")
    private LocalDate updateOn;
    @Column(name = "logo")
    private String logo;
}
