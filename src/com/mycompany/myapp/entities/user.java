/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities;

/**
 *
 * @author Yosrio
 */
public class user {
     private int id;

    private String userName;
    private String username_canonical;
    private String email;
    private String email_canonical;
    private String password;
    private String roles;

    public user(int id, String userName, String username_canonical, String email, String email_canonical, String password, String roles) {
        this.id = id;
        this.userName = userName;
        this.username_canonical = username_canonical;
        this.email = email;
        this.email_canonical = email_canonical;
        this.password = password;
        this.roles = roles;
    }

    public user(int id) {
        this.id = id;
    }

    public user(int id, String username) {
        this.id = id;
        this.userName = username;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUsername_canonical() {
        return username_canonical;
    }

    public void setUsername_canonical(String username_canonical) {
        this.username_canonical = username_canonical;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail_canonical() {
        return email_canonical;
    }

    public void setEmail_canonical(String email_canonical) {
        this.email_canonical = email_canonical;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "user{" + "id=" + id + ", userName=" + userName + ", username_canonical=" + username_canonical + ", email=" + email + ", email_canonical=" + email_canonical + ", password=" + password + ", roles=" + roles + '}';
    }
}
