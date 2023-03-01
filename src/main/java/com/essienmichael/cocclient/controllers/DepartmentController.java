package com.essienmichael.cocclient.controllers;

import com.essienmichael.cocclient.models.Department;
import com.essienmichael.cocclient.services.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    @GetMapping("/department-names")
    public ResponseEntity<List<String>> getDepartmentNames(){
        var res = departmentService.getDepartmentNames();
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @GetMapping("/{department}")
    public ResponseEntity<Department> getDepartment(@PathVariable("department")String department){
        var res = departmentService.getDepartment(department);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }
}
