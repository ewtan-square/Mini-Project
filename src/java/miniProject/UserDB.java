/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package miniProject;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import static miniProject.Query.getConnection;

/**
 *
 * @author jonathan
 */
public class UserDB extends Query{
    
    public static String getAccountSalt(String username) 
            throws ClassNotFoundException, SQLException {
        
        Connection con = null;
        PreparedStatement stmt = null;
                        
        try {
            con = getConnection();
            
            stmt = con.prepareStatement("SELECT salt FROM Account WHERE "
                    + "username = ?;");
            
            stmt.setString(1, username);
            ResultSet resultSet = stmt.executeQuery();
            
            if (resultSet.next()) {
                return resultSet.getString("salt");
            }
            
            return "";
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

