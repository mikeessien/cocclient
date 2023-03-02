package com.essienmichael.cocclient.dto.requests;

import lombok.Data;

import java.util.List;

@Data
public class AdminRoleRequest {
    private String email;
    private String role;
    private List<String> departments;
}
