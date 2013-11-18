/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package miniProject;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import static miniProject.DoctorDBAO.getSpecializations;
import static miniProject.DoctorDBAO.getWorkAddresses;
import static miniProject.Query.getConnection;

/**
 *
 * @author jonathan
 */
public class PatientDB extends Query{
    public static ArrayList<Patient> findFriends(String username)
            throws ClassNotFoundException, SQLException {
        
        Connection con = null;
        ArrayList<Patient> friends = new ArrayList<Patient>();
        PreparedStatement stmt = null;
        try {
            con = getConnection();
            stmt = con.prepareStatement("SELECT * FROM Patient INNER JOIN "
                    + "Friendship ON Patient.username = Friendship.FRIENDER_Username "
                    + "WHERE FRIENDER_username = ?);");
            stmt.setString(1, username);
            ResultSet results = stmt.executeQuery();
            
            DateFormat df = new SimpleDateFormat("yyyy/MM/dd");  
            
            while(results.next()) {
                String date = df.format(results.getString("DoB"));                
                friends.add(new Patient(
                        results.getString("username"),
                        results.getString("first_name"),
                        results.getString("gender"),
                        results.getString("last_name"),
                        date,
                        results.getString("email"),
                        results.getString("province"),
                        results.getString("city"),
                        results.getString("postal_code"),
                        results.getString("street_address")
                ));
            }
            
            return friends;
           
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
    
    public static ArrayList<Patient> findNewFriends(String username)
            throws ClassNotFoundException, SQLException {
        
        Connection con = null;
        ArrayList<Patient> new_friends = new ArrayList<Patient>();
        PreparedStatement stmt = null;
                        
        try {
            con = getConnection();
            stmt = con.prepareStatement("SELECT * FROM Patient WHERE "
                    + "Patient.username <> ? AND Patient.username NOT IN ( " 
                    + "SELECT FRIEND_username FROM Friendship WHERE " 
                    + "FRIENDER_username = ?");
            stmt.setString(1, username);
            stmt.setString(2, username);            
            
            ResultSet results = stmt.executeQuery();
            
            DateFormat df = new SimpleDateFormat("yyyy/MM/dd");  
            
            while(results.next()) {
                String date = df.format(results.getString("DoB"));                
                new_friends.add(new Patient(
                        results.getString("username"),
                        results.getString("first_name"),
                        results.getString("gender"),
                        results.getString("last_name"),
                        date,
                        results.getString("email"),
                        results.getString("province"),
                        results.getString("city"),
                        results.getString("postal_code"),
                        results.getString("street_address")
                ));
            }
            
            return new_friends;
           
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
}
