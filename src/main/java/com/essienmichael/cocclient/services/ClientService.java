package com.essienmichael.cocclient.services;

import com.essienmichael.cocclient.Repositories.ClientRepo;
import com.essienmichael.cocclient.Repositories.DepartmentRepo;
import com.essienmichael.cocclient.Repositories.RoleRepo;
import com.essienmichael.cocclient.dto.*;
import com.essienmichael.cocclient.exception.ClientBadRequestException;
import com.essienmichael.cocclient.exception.ClientInvalidPasswordException;
import com.essienmichael.cocclient.exception.ClientNotFoundException;
import com.essienmichael.cocclient.models.Client;
import com.essienmichael.cocclient.models.Department;
import com.essienmichael.cocclient.models.Role;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

@Service
@AllArgsConstructor
public class ClientService {

    private final ClientRepo clientRepo;
    private final RoleRepo roleRepo;
    private final DepartmentRepo departmentRepo;
    private final PasswordEncoder passwordEncoder;

    public List getAllClients() {
        return clientRepo.findAll();
    }

    public String saveClient(Client client) {
        clientRepo.save(client);
        return "saved";
    }

    public Client registerClient(ClientRequest clientRequest) {
        if (ifAccountExists(clientRequest.getEmail())) {
            throw new ClientBadRequestException("Client already exists");
        }

        Role role = getRole(clientRequest.getRole());

        String encoded_Password = this.passwordEncoder.encode(clientRequest.getPassword());

        Client client = new Client();
        client.setFirstName(clientRequest.getFirstName());
        client.setLastName(clientRequest.getLastName());
        client.setEmail(clientRequest.getEmail());
        client.setDob(formatDate(clientRequest.getDob()));
        client.setPassword(encoded_Password);
        client.setCreatedAt(LocalDate.now());
        client.setIsActivated(false);
        client.setRole(role);
        return clientRepo.save(client);
    }

    public ClientResponse login(ClientLoginRequest clientLoginRequest) {
        Client client = getClientByEmail(clientLoginRequest.getEmail());

        if (!passwordEncoder.matches(clientLoginRequest.getPassword(), client.getPassword())) {
           throw new ClientInvalidPasswordException("Password is invalid");
        }

        ClientResponse clientResponse = new ClientResponse();
        clientResponse.setFirstName(client.getFirstName());
        clientResponse.setLastName(client.getLastName());
        clientResponse.setEmail(client.getEmail());
        clientResponse.setDob(client.getDob());

        return clientResponse;
    }

    public Client registerAdmin(AdminRequest clientRequest) {
        if (ifAccountExists(clientRequest.getEmail())) {
            throw new RuntimeException("Client already exists");
        }

        Client client = new Client();

        String encoded_Password = this.passwordEncoder.encode(clientRequest.getPassword());

        Set<Department> department = new HashSet<>();
        clientRequest.getDepartmentName().forEach(departments -> department.add(changeStringToDepartment(departments)));

        client.setRole(getRole(clientRequest.getRole()));
        client.setFirstName(clientRequest.getFirstName());
        client.setLastName(clientRequest.getLastName());
        client.setEmail(clientRequest.getEmail());
        client.setDob(formatDate(clientRequest.getDob()));
        client.setPassword(encoded_Password);
        client.setDepartment(department);
        return clientRepo.save(client);
    }

    public Client changeClientUserRoleToAdmin(AdminRoleRequest adminRoleRequest) {
        Client client = getClientByEmail(adminRoleRequest.getEmail());

        client.setRole(getRole(adminRoleRequest.getRole()));

        if (adminRoleRequest.getDepartments().isEmpty()) {
            throw new ClientBadRequestException("Administrators must have at least 1 department");
        }

        adminRoleRequest.getDepartments().forEach(departments -> client.getDepartment().add(changeStringToDepartment(departments)));

        clientRepo.save(client);

        return client;
    }

    public Client changeClientAdminRoleToUser(UserRoleRequest userRoleRequest) {
        Client client = getClientByEmail(userRoleRequest.getEmail());

        client.setRole(getRole(userRoleRequest.getRole()));
        client.setDepartment(null);

        return clientRepo.save(client);
    }

    public Client activateAccount(AccountActivationRequest accountActivationRequest) {
        Client client = getClientByEmail(accountActivationRequest.getClientEmail());

        Client adminClient = getClientByEmail(accountActivationRequest.getActivator());

        if (adminClient.getRole().getRoleName().equalsIgnoreCase("User")) {
            throw new ClientBadRequestException("Activator is not an Administrator");
        }

        client.setActivatedBy(adminClient.getActivatedBy());
        client.setIsActivated(true);
        client.setDayActivated(LocalDate.now());

        return clientRepo.save(client);
    }

    public Client deleteAdminDepartment(DeleteClientDepartmentRequest deleteClientDepartmentRequest) {
        Client client = getClientByEmail(deleteClientDepartmentRequest.getClient());
        Client adminClient = getClientByEmail(deleteClientDepartmentRequest.getAdministrator());

        deleteClientDepartmentRequest.getDepartments().forEach(department -> client.getDepartment().remove(changeStringToDepartment(department)));

        if (!adminClient.getRole().getRoleName().equalsIgnoreCase("Admin")) {
            throw new ClientBadRequestException("Account is not an Admin Account");
        }

        if (client.getDepartment().isEmpty()) {
            client.setRole(getRole("User"));
        }

        return clientRepo.save(client);
    }

    private LocalDate formatDate(String date) {
        return LocalDate.parse(date);
    }

    private Department changeStringToDepartment(String department) {
        Department dept = new Department();
        Optional<Department> foundDepartment = departmentRepo.findByDepartmentName(department).stream().findFirst();

        if (foundDepartment.isPresent()) {
            dept = foundDepartment.get();
        } else {
            dept.setDepartmentName(department);
        }

        return dept;
    }

    private Client getClientByEmail(String email){
        Optional<Client> foundClient = clientRepo.findByEmail(email).stream().findFirst();

        if (foundClient.isPresent()) {
            return foundClient.get();
        } else {
            throw new ClientNotFoundException("Client does not exist");
        }
    }

    private Boolean ifAccountExists(String email){
        return clientRepo.findByEmail(email).stream().findFirst().isPresent() ? true : false;
    }

    private Role getRole(String role){
        Optional<Role> role1 = roleRepo.findAll().stream().filter(__ -> __.getRoleName().equalsIgnoreCase(role)).findFirst();

        if (role1.isPresent()) {
            return role1.get();
        } else {
            throw new RuntimeException("Role does not exist");
        }
    }
}