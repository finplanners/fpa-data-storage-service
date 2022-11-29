package com.gcp.storage.model;

import com.google.cloud.spring.data.datastore.core.mapping.Entity;
import lombok.AllArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;

@Entity(name = "sample")
@ToString
@AllArgsConstructor
public class FPDetails {

    @Id
    private Long id;

    private String fpData;
    private String updatedBy;
}
