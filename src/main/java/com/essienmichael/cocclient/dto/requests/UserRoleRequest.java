package com.essienmichael.cocclient.dto.requests;

import lombok.Data;

@Data
public class UserRoleRequest {
    private String email;
    private String role;
}
