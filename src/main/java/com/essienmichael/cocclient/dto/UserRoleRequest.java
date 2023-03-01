package com.essienmichael.cocclient.dto;

import lombok.Data;

@Data
public class UserRoleRequest {
    private String email;
    private String role;
}
