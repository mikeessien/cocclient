package com.essienmichael.cocclient.Repositories;

import com.essienmichael.cocclient.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleRepo extends JpaRepository<Role, Long> {
    List<Role> findByRoleName(String role);
}
