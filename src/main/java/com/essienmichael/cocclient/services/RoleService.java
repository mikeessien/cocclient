package com.essienmichael.cocclient.services;

import com.essienmichael.cocclient.Repositories.RoleRepo;
import com.essienmichael.cocclient.models.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {
    @Autowired
    RoleRepo roleRepo;

    public void saveRole(Role role){
        roleRepo.save(role);
    }
}
