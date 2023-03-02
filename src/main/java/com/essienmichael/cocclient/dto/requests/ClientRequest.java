package com.essienmichael.cocclient.dto.requests;

import lombok.Data;
import lombok.Setter;

import java.time.LocalDate;

@Data
@Setter
public class ClientRequest {
    private String firstName;
    private String lastName;
    private String email;
    private String dob;
    private String password;
    private String role;
}
