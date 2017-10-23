/*-----------------------------------------------------------------------------------
 File        : AuthenManagementLocal.java
 Author(s)   : Schmidt Emmanuel, Zharkova Anastasia
 Date        : 19.10.2017
 Goal        : interface which is gonna be implemented by AuthenManager
 -----------------------------------------------------------------------------------*/ 
package com.mycompany.mvc.services.dao;

import javax.ejb.Local;


@Local
public interface AuthenManagerLocal {
    
    int login(String username, String password);
    
}
