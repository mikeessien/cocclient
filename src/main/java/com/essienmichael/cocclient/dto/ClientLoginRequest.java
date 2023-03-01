package com.essienmichael.cocclient.dto;

import lombok.Data;

@Data
public class ClientLoginRequest {
    private String email;
    private String password;
}
