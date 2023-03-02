package com.essienmichael.cocclient.dto.requests;

import lombok.Data;

@Data
public class AccountActivationRequest {
    private String clientEmail;
    private String activator;
}
