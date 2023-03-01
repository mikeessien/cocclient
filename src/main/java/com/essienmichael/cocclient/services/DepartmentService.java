package com.essienmichael.cocclient.services;

import com.essienmichael.cocclient.Repositories.DepartmentRepo;
import com.essienmichael.cocclient.exception.DepartmentNotFoundException;
import com.essienmichael.cocclient.models.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DepartmentService {

    @Autowired
    DepartmentRepo departmentRepo;

    public List<Department> getAllDepartments() {
        return departmentRepo.findAll();
    }

    public List<String> getDepartmentNames(){
        return departmentRepo.findAll().stream().map(department -> department.getDepartmentName()).collect(Collectors.toList());
    }

    public Department getDepartment(String department){
        return findDepartmentByName(department);
    }

    public Department changeStringToDepartment(String department) {
        Department dept = new Department();
        Optional<Department> foundDepartment = departmentRepo.findByDepartmentName(department).stream().findFirst();

        if (foundDepartment.isPresent()) {
            dept = foundDepartment.get();
        } else {
            dept.setDepartmentName(department);
        }

        return dept;
    }

    private Department findDepartmentByName(String department){
        Optional<Department> foundDepartment = departmentRepo.findAll().stream().filter( __ -> __.getDepartmentName().equalsIgnoreCase(department)).findFirst();

        if (foundDepartment.isPresent()) {
            return foundDepartment.get();
        } else {
            throw new DepartmentNotFoundException("Department does not exist");
        }
    }


}
