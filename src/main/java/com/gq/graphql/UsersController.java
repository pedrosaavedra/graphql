package com.gq.graphql;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import jakarta.annotation.PostConstruct;

@Controller
public class UsersController {

    private Map<Long, User> users = new HashMap<>();


    @QueryMapping
    public List<User> users() {
        return users.values().stream().toList();
    }

    @QueryMapping
    public User getUserById(Long  id) {
        return users.get(id);
    }

    @PostConstruct
    public void setUpUsers(){
        var pAdmin = new Permission("Admin", 10);
        var pUser = new Permission("Admin", 5);
        var pRead = new Permission("Read", 1);

        var admPermissions = new ArrayList<Permission>();
        admPermissions.add(pUser);
        admPermissions.add(pAdmin);

        var usrPermissions = new ArrayList<Permission>();
        admPermissions.add(pUser);
        admPermissions.add(pRead);

        var admin = new User(1l, "Administrator", admPermissions);
        var user = new User(5l, "User", usrPermissions);

        users.put(1l, admin);
        users.put(2l, user);
    }


    record User(Long id, String name, List<Permission> permissions){}

    record Permission(String name, Integer rank){}
    
}
