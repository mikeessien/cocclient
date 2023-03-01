package com.essienmichael.cocclient.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
public class Client {
    @Id
    @SequenceGenerator(
            name = "client_sequence",
            sequenceName = "client_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "client_sequence"
    )
    private Long clientId;
    private String firstName;
    private String lastName;
    @NonNull
    private  String email;
    private LocalDate dob;
    private String password;
    private LocalDate createdAt;
    private String status;
    private Boolean isActivated;
    private String activatedBy;
    private LocalDate dayActivated;

    @ManyToOne
    @JoinColumn(name = "role_id", nullable = false)
    @JsonIgnoreProperties("client")
    private Role role;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(
            name = "client_department",
            joinColumns = @JoinColumn(name = "client_id", referencedColumnName = "clientId"),
            inverseJoinColumns = @JoinColumn(name = "department_id", referencedColumnName = "departmentId")
    )
    @JsonIgnoreProperties("clients")
    private Set<Department> department;
}
