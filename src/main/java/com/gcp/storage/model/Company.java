package com.gcp.storage.model;

import com.google.cloud.spring.data.datastore.core.mapping.Entity;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.springframework.data.annotation.Id;

@Entity(name = "company")
@ToString
@AllArgsConstructor
@Getter
@EqualsAndHashCode
public class Company {

    @Id
    private Long id;

    private String companyName;
    private String industryType;
    private String department;
    private Integer numberOfEmployees;
}
