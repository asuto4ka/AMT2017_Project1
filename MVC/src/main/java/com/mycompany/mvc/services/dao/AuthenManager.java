/*-----------------------------------------------------------------------------------
 File        : AuthenManagement.java
 Author(s)   : Schmidt Emmanuel, Zharkova Anastasia
 Date        : 19.10.2017
 Goal        : class containing all methods which are needed for authentification and
               autorization
 -----------------------------------------------------------------------------------*/
package com.mycompany.mvc.services.dao;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.SecretKeyFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.crypto.SecretKey;
import javax.ejb.Stateless;
import javax.sql.DataSource;


@Stateless
public class AuthenManager implements AuthenManagerLocal {
    
    @Resource(lookup = "java:/jdbc/amtproject")
    private DataSource dataSrc;

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    
    // fonction implementing autorization and authentification with hashed psw
    @Override
    public int login(String username, String password){
        int id = -1;
      // special functions wich allow to hass a password, and if something goes wrong
      // we'll have an exception. Two try and catch  first for hash the password
      // and seconde one for the connection       
         try {
            byte [] salt = username.getBytes();
            byte [] hashedPass = hashPassword(password.toCharArray(),salt,12,256);
            
           try (Connection connection = dataSrc.getConnection()) {
              PreparedStatement pstmt = connection.prepareStatement("SELECT * FROM users WHERE BINARY username=? AND password= ?");
              pstmt.setString(1, username);
              pstmt.setString(2, Arrays.toString(hashedPass));
              ResultSet rs = pstmt.executeQuery();
              System.out.println(Arrays.toString(hashedPass));
              Logger.getLogger(BooksManager.class.getName()).log(Level.SEVERE, null, Arrays.toString(hashedPass));
              if(rs.next()){
                 id = rs.getInt("user_id");
              }
           }
        } catch (SQLException e) {
            Logger.getLogger(BooksManager.class.getName()).log(Level.SEVERE, null, e);
        }
        return id;
    }
    
    
      // fonction wich will hash the password sha512
       public static byte[] hashPassword( final char[] password, final byte[] salt, final int iterations, final int keyLength ) {
      
      // special functions wich allow to hass a password, and if something goes wrong
      // we'll have a RunTimeException
       try {
           SecretKeyFactory skf = SecretKeyFactory.getInstance( "PBKDF2WithHmacSHA512" );
           PBEKeySpec spec = new PBEKeySpec( password, salt, iterations, keyLength );
           SecretKey key = skf.generateSecret( spec );
           byte[] res = key.getEncoded( );
           return res;
 
       } catch( NoSuchAlgorithmException | InvalidKeySpecException e ) {
           throw new RuntimeException( e );
       }
   }
}
