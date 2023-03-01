package com.essienmichael.cocclient.controllers;

import com.essienmichael.cocclient.models.Department;
import com.essienmichael.cocclient.services.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/auth/departments")
@RequiredArgsConstructor
public class DepartmentController {
    private final DepartmentService departmentService;

    @GetMapping
    public ResponseEntity<List<Department>> getAllDepartments(){
        var res = departmentService.getAllDepartments();
        return new ResponseEntity(res, HttpStatus.OK);
    }
}
