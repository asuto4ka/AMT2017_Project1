/*
-----------------------------------------------------------------------------------
 File        : User.java
 Author(s)   : Schmidt Emmanuel, Zharkova Anastasia
 Date        : 19.10.2017
 Goal        : user class describing user's attribute
 ----------------------------------------------------------------------------------- 
 */
package com.mycompany.mvc.model;


public class User {
    
    private final long id;
    private final String username;
    private final String password;

    //getteurs
    public long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    //constructor
    public User(long id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }
    
    
    
}
