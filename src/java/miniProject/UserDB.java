/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package miniProject;

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
    
    private final static int ITERATION_NUMBER = 1000;
    
    public byte[] getHash(int iterationNb, String password, String salt) 
                        throws NoSuchAlgorithmException, UnsupportedEncodingException {
       MessageDigest digest = MessageDigest.getInstance("SHA-256");
       digest.reset();
       byte[] byteSalt = salt.getBytes("UTF-8");
       digest.update(byteSalt);
       byte[] input = digest.digest(password.getBytes("UTF-8"));
       for (int i = 0; i < iterationNb; i++) {
           digest.reset();
           input = digest.digest(input);
       }
       return input;
    }
    
    public boolean createUser(String username, String password)
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
                byte[] bDigest = getHash(ITERATION_NUMBER,password,salt);
                String sDigest = bDigest.toString();
                
                stmt = con.prepareStatement("INSERT INTO Account (username, password, salt) VALUES (?,?,?)");
                stmt.setString(1, username);
                stmt.setString(2, sDigest);
                stmt.setString(3, salt);
                
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
    
    public boolean authenticate(String username, String password)
           throws SQLException, NoSuchAlgorithmException, UnsupportedEncodingException {
       
        Connection con = null;
        PreparedStatement stmt = null;
        

        try {
            if (username.equals("") || password.equals("")) {
                return false;
            }
            
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
                return false;
            }
            
            // Get the hashed password
            byte[] byteActualDigest = actualpassword.toString().getBytes("UTF-8");
            // Compute the new digest/inputted password
            byte[] proposedDigest = getHash(ITERATION_NUMBER, password, salt);

            return Arrays.equals(proposedDigest, byteActualDigest);
        } catch (IOException ex){
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
                return "Admin";
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

