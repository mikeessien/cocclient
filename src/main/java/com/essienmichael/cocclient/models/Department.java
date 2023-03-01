package com.essienmichael.cocclient.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long departmentId;
    private String departmentName;
    private String status;

    @ManyToMany(mappedBy = "department", fetch = FetchType.EAGER)
    @JsonBackReference
    Set<Client> clients;
}
