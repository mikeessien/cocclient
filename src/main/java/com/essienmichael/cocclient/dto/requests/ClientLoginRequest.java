package com.essienmichael.cocclient.dto.requests;

import lombok.Data;

@Data
public class ClientLoginRequest {
    private String email;
    private String password;
}
