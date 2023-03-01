package com.essienmichael.cocclient.dto;

import lombok.Data;

@Data
public class AccountActivationRequest {
    private String clientEmail;
    private String activator;
}
