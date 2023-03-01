package com.essienmichael.cocclient.controllers;

import com.essienmichael.cocclient.models.Client;
import com.essienmichael.cocclient.services.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/clients")
@RequiredArgsConstructor
public class ClientController {
    private final ClientService clientService;

    @GetMapping
    public ResponseEntity<List<Client>> getAllClients(){
        return new ResponseEntity<>(clientService.getAllClients(), HttpStatus.OK);
    }

    @GetMapping("/{client-mail}")
    public ResponseEntity<Client> getClient(@PathVariable("client-mail") String email){
        return new ResponseEntity<>(clientService.getClientByEmail(email), HttpStatus.OK);
    }

}
