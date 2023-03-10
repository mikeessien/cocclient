package com.essienmichael.cocclient.dto.requests;

import lombok.Data;

import java.util.List;

@Data
public class DeleteClientDepartmentRequest {
    private String client;
    private List<String> departments;
    private String administrator;
}
