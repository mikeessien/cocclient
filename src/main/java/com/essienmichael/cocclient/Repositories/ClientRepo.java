package com.essienmichael.cocclient.Repositories;

import com.essienmichael.cocclient.models.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClientRepo extends JpaRepository<Client, Long> {
    List<Client> findByEmail(String email);

}
