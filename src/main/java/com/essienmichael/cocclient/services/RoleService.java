package com.essienmichael.cocclient.services;

import com.essienmichael.cocclient.Repositories.RoleRepo;
import com.essienmichael.cocclient.exception.RoleNotFoundException;
import com.essienmichael.cocclient.models.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleService {
    @Autowired
    RoleRepo roleRepo;

    public void saveRole(Role role){
        roleRepo.save(role);
    }

    public List<Role> getAllRoles(){
        return roleRepo.findAll();
    }

    public Role getRoleByRoleName(String role){
        Optional<Role> role1 = roleRepo.findAll().stream().filter(__ -> __.getRoleName().equalsIgnoreCase(role)).findFirst();

        if (role1.isPresent()) {
            return role1.get();
        } else {
            throw new RoleNotFoundException("Role does not exist");
        }
    }
}
