/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package miniProject;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import static miniProject.Query.getConnection;

/**
 *
 * @author jonathan
 */
public class DoctorDBAO extends Query {
    public static ArrayList<Doctor> getAllDoctors()
            throws ClassNotFoundException, SQLException {
        
        Connection con = null;
        ArrayList<Doctor> doctors = new ArrayList<Doctor>();
        Statement stmt = null;
        try {
            con = getConnection();
            stmt = con.createStatement();
            ResultSet results = stmt.executeQuery("SELECT * FROM Doctor");
            
            DateFormat df = new SimpleDateFormat("yyyy/MM/dd");  
            
            while(results.next()) {
                String date = df.format(results.getDate("DoB"));
                String username = results.getString("username");
                Doctor doc = new Doctor(
                    username,
                    results.getString("first_name"),
                    results.getString("last_name"),
                    results.getString("gender"),
                    date,
                    results.getInt("license_year"),
                    results.getString("province"),
                    results.getString("city"),
                    results.getString("postal_code"),
                    results.getString("street_address")                        
                );
                
                doc.setWorkAddress(getWorkAddresses(username));
                doc.setSpecialization(getSpecializations(username));
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
        return doctors;
        
    }
    
    
    public static ArrayList<WorkAddress> getWorkAddresses(String username)
            throws ClassNotFoundException, SQLException {
        Connection con = null;
        PreparedStatement stmt = null;
        ArrayList<WorkAddress> ret;
        try {
            con = getConnection();
            stmt = con.prepareStatement("SELECT * FROM Work_Address WA inner join "
                    + "Account A ON A.username = WA.username WHERE ? = A.username;");
            stmt.setString(1, username);
            
            ResultSet resultSet = stmt.executeQuery();
            
            ret = new ArrayList<WorkAddress>();
            while (resultSet.next()) {
                WorkAddress wa = new WorkAddress(
                    resultSet.getInt("D_ID"),
                    resultSet.getString("province"),
                    resultSet.getString("city"),
                    resultSet.getString("postal_code"),
                    resultSet.getString("street_address")
                );
                ret.add(wa);
            }
            return ret;
        } finally {
            if (stmt != null) {
                stmt.close();
            }
            if (con != null) {
                con.close();
            }
        }
    }
    
    public static ArrayList<String> getSpecializations(String username)
            throws ClassNotFoundException, SQLException {
        Connection con = null;
        PreparedStatement stmt = null;
        ArrayList<String> ret;
        try {
            con = getConnection();
            stmt = con.prepareStatement("SELECT * FROM Doctor_Specialization DS "
                    + "INNER JOIN Account A ON A.username = DS.D_username "
                    + "WHERE ? = A.username;");
            
            stmt.setString(1, username);
            ResultSet resultSet = stmt.executeQuery();
            
            ret = new ArrayList<String>();
            while (resultSet.next()) {
                String specialization = resultSet.getString("area");
                ret.add(specialization);
            }
            return ret;
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
