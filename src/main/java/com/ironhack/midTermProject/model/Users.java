package com.ironhack.midTermProject.model;

//import com.ironhack.midTermProject.model.security.Role;

import javax.persistence.*;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Users {

    @Id
    @GeneratedValue
    protected Integer userId;
    @Column(name = "name")
    private String name;
//
//    private String password;
//
//    @Column(name = "username")
//    private String username;

//    @OneToMany(mappedBy ="user")
//    private List<Role> roles;


    public Users(Integer userId, String name) {
        this.userId = userId;
        this.name = name;
    }

    public Users() {
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Users(String name) {
         setName(name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

//    public String getUsername() {
//        return username;
//    }
//
//    public void setUsername(String username) {
//        this.username = username;
//    }
//
//    public String getPassword() {
//        return password;
//    }
//
//    public void setPassword(String password) {
//        this.password = password;
//    }

//    public List<Role> getRoles() {
//        return roles;
//    }

//    public void setRoles(List<Role> roles) {
//        this.roles = roles;
//    }
}
