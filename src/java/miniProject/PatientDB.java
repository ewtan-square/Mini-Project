/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package miniProject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import static miniProject.Query.getConnection;

/**
 *
 * @author jonathan
 */
public class PatientDB extends Query {
    
    public static boolean createPatient(Patient P)
           throws SQLException, ClassNotFoundException
    {
       
        Connection con = null;
        PreparedStatement stmt = null;
                        
        try {
            con = getConnection();
            if (P.getUsername() == null || P.getFirstName() == null || P.getGender() == null || P.getLastName() == null ||
                     P.getDOB() == null || P.getHomeProvince() == null || P.getHomeCity() == null || 
                    P.getHomePostalCode() == null || P.getHomeStreet() == null || P.getEmail() == null) {
                return false;
            }
            else {
                stmt = con.prepareStatement("INSERT INTO Patient "
                        
                        + "VALUES (?,?,?,?,?,?,?,?,?,?)");
                stmt.setString(1, P.getUsername());
                stmt.setString(2, P.getFirstName());
                stmt.setString(3, P.getLastName());
                stmt.setString(4, P.getEmail());
                stmt.setString(5, P.getDOB());
                stmt.setString(6, P.getGender());
                stmt.setString(7, P.getHomeProvince());
                stmt.setString(8, P.getHomeCity());
                stmt.setString(9, P.getHomePostalCode());
                stmt.setString(10, P.getHomeStreet());
                stmt.executeUpdate();
                return true;
            }
        } 
        catch (Exception e) {
            if (e instanceof SQLException) {
                throw new SQLException(e);
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
        return false;
    }
    public static ArrayList<Patient> getAllPatients()
            throws ClassNotFoundException, SQLException {
        
        Connection con = null;
        ArrayList<Patient> patients = new ArrayList<Patient>();
        Statement stmt = null;
        try {
            con = getConnection();
            stmt = con.createStatement();
            ResultSet results = stmt.executeQuery("SELECT * FROM Patient");
            
            DateFormat df = new SimpleDateFormat("yyyy/MM/dd");  
            
            while(results.next()) {
                String date = df.format(results.getDate("DoB"));
                String username = results.getString("username");
                Patient pat = new Patient(
                    username,
                    results.getString("first_name"),
                    results.getString("last_name"),
                    results.getString("gender"),
                    date,
                    results.getString("gender"),
                    results.getString("province"),
                    results.getString("city"),
                    results.getString("postal_code"),
                    results.getString("street_address")
                );
                
                pat.setFriends(getFriends(username));
                patients.add(pat);
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
        return patients;
        
    }
    public static ArrayList<Patient> getFriends(String username)
            throws ClassNotFoundException, SQLException {
        Connection con = null;
        PreparedStatement stmt = null;
        ArrayList<Patient> ret;
        try {
            con = getConnection();
            stmt = con.prepareStatement("SELECT * FROM Friendship Fr INNER JOIN Patient P ON "
                    + "Fr.FRIENDER_username = P.username WHERE ? = Fr.FRIENDER_username;");
            stmt.setString(1, username);
            ResultSet resultSet = stmt.executeQuery();
            
            DateFormat df = new SimpleDateFormat("yyyy/MM/dd");  
            ret = new ArrayList<Patient>();
            while (resultSet.next()) {
                String date = df.format(resultSet.getDate("DoB"));
                String friendName = resultSet.getString("username");
                Patient pat = new Patient(
                    friendName,
                    resultSet.getString("first_name"),
                    resultSet.getString("last_name"),
                    resultSet.getString("gender"),
                    date,
                    resultSet.getString("gender"),
                    resultSet.getString("province"),
                    resultSet.getString("city"),
                    resultSet.getString("postal_code"),
                    resultSet.getString("street_address")
                );
                ret.add(pat);
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
        return ret;
    }

}
