package com.essienmichael.cocclient.dto.response;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Data
@Getter
@Setter
public class ClientResponse {
    private String firstName;
    private String lastName;
    private String email;
    private LocalDate dob;
}
