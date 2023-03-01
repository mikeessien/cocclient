package com.essienmichael.cocclient.controllers;

import com.essienmichael.cocclient.dto.*;
import com.essienmichael.cocclient.services.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("api/v1/auth")
@RequiredArgsConstructor
public class RegisterController {
    private final ClientService clientService;

    @GetMapping("/home")
    public String welcome(){
        return "Welcome there";
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody ClientRequest clientRequest){
        var res = clientService.registerClient(clientRequest);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(res);
    }

    @PostMapping("/registerAdmin")
    public ResponseEntity registerAdmin(@RequestBody AdminRequest adminRequest){
        var res = clientService.registerAdmin(adminRequest);
        return ResponseEntity.ok("User created successfully");
    }

    @PostMapping("/updateUserRole")
    public ResponseEntity updateClientUserRoleToAdmin(@RequestBody AdminRoleRequest adminRoleRequest){
        var res =  clientService.changeClientUserRoleToAdmin(adminRoleRequest);
        return new ResponseEntity(res, HttpStatus.OK);
    }

    @PostMapping("/updateAdminRole")
    public ResponseEntity updateClientAdminRoleToUser(@RequestBody UserRoleRequest userRoleRequest){
        var res = clientService.changeClientAdminRoleToUser(userRoleRequest);
        return new ResponseEntity(res, HttpStatus.OK);
    }

    @PostMapping("activateAccount")
    public ResponseEntity activateAccount(@RequestBody AccountActivationRequest accountActivationRequest){
        var res =  clientService.activateAccount(accountActivationRequest);
        return new ResponseEntity("Account activated successfully", HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody ClientLoginRequest clientLoginRequest){
        var res = clientService.login(clientLoginRequest);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @PostMapping("/deleteAdminFromDepartment")
    public ResponseEntity deleteAdminDept(@RequestBody DeleteClientDepartmentRequest deleteClientDepartmentRequest){
        var res = clientService.deleteAdminDepartment(deleteClientDepartmentRequest);
        return new ResponseEntity<>("Department has been updated successfully", HttpStatus.OK);
    }

    @GetMapping("/clients")
    public ResponseEntity allClients(){

//        Role userrole = new Role();
//        Role adminrole = new Role();
//
//        userrole.setRoleName("User");
//        adminrole.setRoleName("Admin");
//
//        roleService.saveRole(userrole);
//        roleService.saveRole(adminrole);
//
//        Client client = new Client();
//        client.setFirstName("michael");
//        client.setLastName("essien");
//        client.setEmail("essienmichale@gmail.com");
//        client.setDob(formatDate("2021-04-02"));
//        client.setPassword("prototype");
//        client.setRole(adminrole);
//
//        clientService.saveClient(client);

        return ResponseEntity.ok(clientService.getAllClients());
    }

    private LocalDate formatDate(String date){
        return LocalDate.parse(date);
    }
}
