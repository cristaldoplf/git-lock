package com.dh.userservice.repository;

import com.dh.userservice.model.User;

import com.dh.userservice.model.UserNameDTO;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.Collectors;

@Repository
public class KeycloakRepository {
    @Autowired
    private Keycloak keycloak;
    @Value("${keycloak.realm}")
    private String realm;

    public List<UserNameDTO> findAll(){

        List<UserRepresentation> allUserRepresentations = new ArrayList<>();

        allUserRepresentations=keycloak
                .realm(realm)
                .users()
                .list();

        List<UserNameDTO> user_list = allUserRepresentations.stream().map(this::fromRepresentationToDTO).collect(Collectors.toList());

        return user_list;
    }

    public User findByUsername(String username){

        List <UserRepresentation> user = keycloak
                .realm(realm)
                .users()
                .search(username);

        return fromRepresentationToEntity(user.get(0));
    }

    private UserNameDTO fromRepresentationToDTO(UserRepresentation userRepresentation) {
        return new UserNameDTO(userRepresentation.getUsername(),userRepresentation.getEmail());
    }

    private User fromRepresentationToEntity(UserRepresentation userRepresentation) {
        return new User(userRepresentation.getUsername(),userRepresentation.getFirstName(),userRepresentation.getLastName(),userRepresentation.getEmail());
    }

}


