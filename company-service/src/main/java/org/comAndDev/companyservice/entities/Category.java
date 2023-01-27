package org.comAndDev.companyservice.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Category {
    private Long id;
    private String name;
    private String description;
    private boolean archived;
    private LocalDate createdAt;
    private LocalDate updateAt;
}
