package com.essienmichael.cocclient.services;

import com.essienmichael.cocclient.Repositories.DepartmentRepo;
import com.essienmichael.cocclient.models.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentService {

    @Autowired
    DepartmentRepo departmentRepo;

    public List<Department> getAllDepartments() {
        return departmentRepo.findAll();
    }
}
