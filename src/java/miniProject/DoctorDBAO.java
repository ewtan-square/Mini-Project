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
import java.util.Date;
import java.text.SimpleDateFormat;
import static miniProject.Query.getConnection;

/**
 *
 * @author jonathan
 */
public class DoctorDBAO extends Query {
    
    public static boolean createDoctor(Doctor D)
           throws SQLException, ClassNotFoundException
    {
       
        Connection con = null;
        PreparedStatement stmt = null;
                        
        try {
            con = getConnection();
            if (D.getUsername() == null || D.getFirstName() == null || D.getGender() == null || D.getLastName() == null ||
                     D.getDOB() == null || D.getHomeProvince() == null || D.getHomeCity() == null || 
                    D.getHomePostalCode() == null || D.getHomeStreet() == null) {
                return false;
            }
            else {
                stmt = con.prepareStatement("INSERT INTO Doctor "
                        + "(username, "
                        + "first_name, "
                        + "last_name, "
                        + "license_year, "
                        + "DoB, "
                        + "gender, "
                        + "province, "
                        + "city, "
                        + "postal_code, "
                        + "street_address) "
                        + "VALUES (?,?,?,?,?,?,?,?,?,?)");
                stmt.setString(1, D.getUsername());
                stmt.setString(2, D.getFirstName());
                stmt.setString(3, D.getLastName());
                stmt.setInt(4, D.getLicenseYear());
                stmt.setString(5, D.getDOB());
                stmt.setString(6, D.getGender());
                stmt.setString(7, D.getHomeProvince());
                stmt.setString(8, D.getHomeCity());
                stmt.setString(9, D.getHomePostalCode());
                stmt.setString(10, D.getHomeStreet());
                stmt.executeUpdate();
                return true;
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
                doctors.add(doc);
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
    
    public static ArrayList<Doctor> queryDoctor(String username)
            throws ClassNotFoundException, SQLException {
        
        Connection con = null;
        ArrayList<Doctor> doctors = new ArrayList<Doctor>();
        PreparedStatement stmt = null;
        try {
            con = getConnection();
            stmt = con.prepareStatement("SELECT * FROM Doctor WHERE ? = username;");
            stmt.setString(1, username);
            ResultSet results = stmt.executeQuery();
            
            DateFormat df = new SimpleDateFormat("yyyy/MM/dd");  
            
            while(results.next()) {
                String date = df.format(results.getDate("DoB"));
                //String username = results.getString("username");
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
                doctors.add(doc);
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
                    + "Account A ON A.username = WA.D_username WHERE ? = A.username;");
            stmt.setString(1, username);
            
            ResultSet resultSet = stmt.executeQuery();
            
            ret = new ArrayList<WorkAddress>();
            while (resultSet.next()) {
                WorkAddress wa = new WorkAddress(
                    resultSet.getString("username"),
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
    

    public static ArrayList<Review> getDoctorReviews(String username)
            throws ClassNotFoundException, SQLException {
        
        Connection con = null;
        PreparedStatement stmt = null;
        ArrayList<Review> reviews;
        
        try {
            con = getConnection();
            stmt = con.prepareStatement("SELECT * FROM Review WHERE D_username = ?;");
            stmt.setString(1, username);
            
            ResultSet results = stmt.executeQuery();
            reviews = new ArrayList<Review>();
            DateFormat df = new SimpleDateFormat("yyyy/MM/dd"); 
            
            while(results.next()) {
                String date = df.format(results.getDate("review_date"));
                reviews.add(new Review(
                        results.getString("D_username"),
                        results.getString("P_username"),
                        results.getInt("R_ID"),
                        results.getInt("Rating"),
                        results.getBoolean("recommendation"),
                        results.getString("comment_text"),
                        date
                ));
            }
            
            return reviews;
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
            

    public static void newWorkAddress(String username, WorkAddress wa)
            throws ClassNotFoundException, SQLException {
        Connection con = null;
        PreparedStatement stmt = null;

        try {
            con = getConnection();
            stmt = con.prepareStatement("SELECT * FROM Work_Address "
                    + "WHERE ? = D_username AND ? = province AND ? = city AND ? = postal_code AND ? = street_address;");
            stmt.setString(1, username);
            stmt.setString(2, wa.getProvince());
            stmt.setString(3, wa.getCity());
            stmt.setString(4, wa.getPostalCode());
            stmt.setString(5, wa.getStreet());
            ResultSet resultSet = stmt.executeQuery();
            int count = 0;
            while (resultSet.next()) { count++; }
            
            if (count == 0) {
                stmt = con.prepareStatement(
                            "INSERT INTO Work_Address VALUES (?,?,?,?,?);"
                       );
                stmt.setString(1, username);
                stmt.setString(2, wa.getProvince());
                stmt.setString(3, wa.getCity());
                stmt.setString(4, wa.getPostalCode());
                stmt.setString(5, wa.getStreet());
                stmt.executeUpdate();
            }

        } finally {
            if (stmt != null) {
                stmt.close();
            }
            if (con != null) {
                con.close();
            }
        }
    }
    
    public static void newDoctorReview(String docUsername, String patUsername, 
            int rating, Boolean recommendation, String comments)
            throws ClassNotFoundException, SQLException {
        Connection con = null;
        PreparedStatement stmt = null;

        try {
            con = getConnection();
            con.setAutoCommit(false);
            con.setTransactionIsolation( Connection.TRANSACTION_SERIALIZABLE);
            stmt = con.prepareStatement("INSERT INTO Review VALUES (?,?,?,?,?,?,?);");
            DateFormat df = new SimpleDateFormat("yyyy/MM/dd"); 
            Date date = new Date();
            
            
            
            stmt.setString(1, docUsername);
            stmt.setString(2, patUsername);
            stmt.setNull(3, 3);
            stmt.setString(4, df.format(date));
            stmt.setInt(5, rating);
            stmt.setBoolean(6, recommendation);
            stmt.setString(7, comments);
            stmt.executeUpdate();

        } catch(SQLException se){
            con.rollback();
        } finally {
            if (stmt != null) {
                stmt.close();
            }
            if (con != null) {
                con.close();
            }
        }
    }
    

    public static void newSpecialization(String username, String area)
            throws ClassNotFoundException, SQLException {
        Connection con = null;
        PreparedStatement stmt = null;

        try {
            con = getConnection();
            con.setAutoCommit(false);
            con.setTransactionIsolation( Connection.TRANSACTION_SERIALIZABLE);
            stmt = con.prepareStatement("SELECT * FROM Doctor_Specialization "
                    + "WHERE ? = D_username AND ? = area;");
            stmt.setString(1, username);
            stmt.setString(2, area);
            ResultSet resultSet = stmt.executeQuery();
            int count = 0;
            while (resultSet.next()) { count++; }
            
            if (count == 0) {
            stmt = con.prepareStatement(
                        "INSERT INTO Doctor_Specialization VALUES (?,?);"
                   );
            stmt.setString(1, username);
            stmt.setString(2, area);
            stmt.executeUpdate();
            }
            else {
                
            }

        } catch(SQLException se){
            con.rollback();
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
