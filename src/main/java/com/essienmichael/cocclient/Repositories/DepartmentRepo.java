package com.essienmichael.cocclient.Repositories;

import com.essienmichael.cocclient.models.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepartmentRepo extends JpaRepository<Department, Long> {
    List<Department> findByDepartmentName(String department);
}
