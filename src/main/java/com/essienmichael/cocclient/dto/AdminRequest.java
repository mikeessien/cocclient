package com.essienmichael.cocclient.dto;

import lombok.Data;

import java.util.List;

@Data
public class AdminRequest {
    private String firstName;
    private String lastName;
    private String email;
    private String dob;
    private String password;
    private String role;

    private List<String> departmentName;
}
