/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package miniProject;

import org.apache.commons.codec.digest.DigestUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.Arrays;
import static miniProject.Query.getConnection;

/**
 *
 * @author jonathan
 */
public class UserDB extends Query{
    
    public static String getHash(String password, String salt) 
                        throws NoSuchAlgorithmException, UnsupportedEncodingException {
       
       String saltedHashed;
       saltedHashed = DigestUtils.shaHex(password+salt).toString();
       return saltedHashed;
    }
    
    public static boolean createUser(String username, String password)
           throws SQLException, NoSuchAlgorithmException, ClassNotFoundException, UnsupportedEncodingException
    {
       
        Connection con = null;
        PreparedStatement stmt = null;
                        
        try {
            con = getConnection();
            if (username != null || password != null) {
                
                // Uses a secure Random not a simple Random
                SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
                // Salt generation 64 bits long
                byte[] bSalt = new byte[8];
                random.nextBytes(bSalt);
                
                String salt = bSalt.toString();
                // Digest computation
                String hashedPassword = getHash(password,salt);
                
                stmt = con.prepareStatement("INSERT INTO Account (username, password, salt) VALUES (?,?,?)");
                stmt.setString(1, username);
                stmt.setString(2, hashedPassword);
                stmt.setString(3, salt);
                stmt.executeUpdate();
                return true;
            }
            else {
                return false;
            }
        } 
        finally {
            if (stmt != null) {
                stmt.close();
            }
            if (con != null) {
                con.close();
            }
        }
    }
    
    public static ArrayList<String> authenticate(String username, String password)
           throws SQLException, NoSuchAlgorithmException, UnsupportedEncodingException, ClassNotFoundException {
       
        Connection con = null;
        PreparedStatement stmt = null;
        

        try {
            if (username.equals("") || password.equals("")) {
                return null;
            }
            con = getConnection();
            
            stmt = con.prepareStatement("SELECT password, salt FROM Account WHERE username = ?");
            stmt.setString(1, username);
            ResultSet resultSet = stmt.executeQuery();
            
            resultSet = stmt.executeQuery();
            String actualpassword, salt;
            if (resultSet.next()) {
                actualpassword = resultSet.getString("password");
                salt = resultSet.getString("salt");
                // DATABASE VALIDATION
                if (actualpassword == null || salt == null) {
                    throw new SQLException("Database inconsistant Salt or Digested Password altered");
                }
                if (resultSet.next()) { // Should not append, because login is the primary key
                    throw new SQLException("Database inconsistent two CREDENTIALS with the same LOGIN");
                }
            } 
            else {
                return null;
            }
            
            // Get the hashed password
            String hashedInputPassword = getHash(password, salt);
            // Compute the new digest/inputted password
            String actualPassword = actualpassword;
            
            ArrayList<String> tmp = new ArrayList<String>();
            tmp.add(hashedInputPassword);
            tmp.add(actualPassword);
            return tmp;
            
            //return Arrays.equals(proposedDigest, byteActualDigest);
        } catch (Exception ex){
            throw new SQLException("Database inconsistant Salt or Digested Password altered");
        }
        finally{
            if (stmt != null) {
                stmt.close();
            }
            if (con != null) {
                con.close();
            }
        }
    }
   
    public static String getAccountType(String username)
            throws ClassNotFoundException, SQLException {
        
        Connection con = null;
        PreparedStatement stmt = null;
                        
        try {
            con = getConnection();
            
            stmt = con.prepareStatement("SELECT * FROM Account WHERE "
                    + "username = ?;");
            
            stmt.setString(1, username);
            ResultSet resultSet = stmt.executeQuery();
            
            stmt = con.prepareStatement("SELECT * FROM Patient WHERE username = ?;");
            stmt.setString(1, username);
            
            resultSet = stmt.executeQuery();
            
            if (resultSet.next()) {
                return "patient";
            }
            
            stmt = con.prepareStatement("SELECT * FROM Doctor WHERE username = ?;");
            stmt.setString(1, username);
            resultSet = stmt.executeQuery();
                    
            if (resultSet.next()) {
                return "doctor";
            }
            
            stmt = con.prepareStatement("SELECT * FROM Administrator WHERE username = ?;");
            stmt.setString(1, username);
            resultSet = stmt.executeQuery();
            
            if (resultSet.next()) {
                return "admin";
            }
            
            return "";
        } finally {
            if (stmt != null) {
                stmt.close();
            }
            if (con != null) {
                con.close();
            }
        }
    }
}

