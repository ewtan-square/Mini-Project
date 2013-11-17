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

/**
 *
 * @author jonathan
 */
public class UserDB extends Query{

    
    public static String getAccountType(String username)
            throws ClassNotFoundException, SQLException {
        
        Connection con = null;
        PreparedStatement stmt = null;
                        
        try {
            con = getConnection();
            
            stmt = con.prepareStatement("SELECT ID FROM Account WHERE "
                    + "username = ?;");
            
            stmt.setString(1, username);
            ResultSet resultSet = stmt.executeQuery();
            
            int account_id = 0;
            
            while(resultSet.next()) {
                account_id = resultSet.getInt("ID");
            }
            
            if (account_id == 0) {
                return "";
            }
            
            stmt = con.prepareStatement("SELECT P_ID FROM Patient WHERE P_ID = ?;");
            stmt.setInt(1, account_id);
            
            resultSet = stmt.executeQuery();
            
            if (resultSet.next()) {
                return "patient";
            }
            
            stmt = con.prepareStatement("SELECT D_ID FROM Doctor WHERE D_ID = ?;");
            stmt.setInt(1, account_id);
            resultSet = stmt.executeQuery();
                    
            if (resultSet.next()) {
                return "doctor";
            }
            
            stmt = con.prepareStatement("SELECT ID FROM Administrator WHERE ID = ?;");
            stmt.setInt(1, account_id);
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

