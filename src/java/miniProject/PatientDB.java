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
    

    public static ArrayList<Patient> findNewFriends(String username, String keyword)

            throws ClassNotFoundException, SQLException {
        
        Connection con = null;
        ArrayList<Patient> new_friends = new ArrayList<Patient>(); 
        PreparedStatement stmt = null;

        try {
            con = getConnection();

            if (keyword == "") {
                stmt = con.prepareStatement("SELECT * FROM Patient WHERE "
                        + "Patient.username <> ? AND Patient.username NOT IN ( " 
                        + "SELECT FRIEND_username FROM Friendship WHERE " 
                        + "FRIENDER_username = ?);");
                stmt.setString(1, username);
                stmt.setString(2, username);            
            }
            else {
                stmt = con.prepareStatement("SELECT * FROM Patient WHERE "
                        + "Patient.username <> ? AND Patient.username NOT IN ( " 
                        + "SELECT FRIEND_username FROM Friendship f WHERE " 
                        + "FRIENDER_username = ?) AND Patient.username LIKE ?;");
                stmt.setString(1, username);
                stmt.setString(2, username);    
                stmt.setString(3, "%"+keyword+"%");    
            }

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
            
           
        }
        finally {
            if (stmt != null) {
                stmt.close();
            }
            if (con != null) {
                con.close();
            }
        }
        
        return new_friends;
    }
           
    public static boolean createPatient(Patient P)
           throws SQLException, ClassNotFoundException {
       
        Connection con = null;
        PreparedStatement stmt = null;
        Boolean retval;
                        
        try {
            con = getConnection();
            con.setAutoCommit(false);
            con.setTransactionIsolation( Connection.TRANSACTION_SERIALIZABLE);
            if (P.getUsername() == null || P.getFirstName() == null || P.getGender() == null || P.getLastName() == null ||
                     P.getDOB() == null || P.getHomeProvince() == null || P.getHomeCity() == null || 
                    P.getHomePostalCode() == null || P.getHomeStreet() == null || P.getEmail() == null) {
                retval = false;
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
                retval = true;
            }
        } 
        catch (SQLException se) {
                con.rollback();
                retval = false;
        }
        finally {
            if (stmt != null) {
                stmt.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return retval;
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
                    + "Fr.FRIEND_username = P.username WHERE ? = Fr.FRIENDER_username;");
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
                    resultSet.getString("gender"),
                    resultSet.getString("last_name"),
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

    public static void newFriendship(String friender_username, String friend_username)
                throws ClassNotFoundException, SQLException {
        Connection con = null;
        PreparedStatement stmt = null;

        try {
            con = getConnection();
            con.setAutoCommit(false);
            con.setTransactionIsolation( Connection.TRANSACTION_SERIALIZABLE);
            stmt = con.prepareStatement("SELECT * FROM Friendship "
                            + "WHERE ? = FRIENDER_username AND ? = FRIEND_username");
            stmt.setString(1, friender_username);
            stmt.setString(2, friend_username);
            ResultSet resultSet = stmt.executeQuery();
            int count = 0;
            while (resultSet.next()) { count++; }

            if (count == 0) {
                    stmt = con.prepareStatement(
                                            "INSERT INTO Friendship VALUES (?,?);"
                               );
                    stmt.setString(1, friender_username);
                    stmt.setString(2, friend_username);
                    stmt.executeUpdate();
            }

        } 
        catch (SQLException se) {
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
    public static void removeFriend(String FRIENDER_username, String FRIEND_username)
                throws ClassNotFoundException, SQLException {
            Connection con = null;
            PreparedStatement stmt = null;

            try {
                    con = getConnection();
                    stmt = con.prepareStatement("SELECT * FROM Friendship "
                                    + "WHERE ? = FRIENDER_username AND ? = FRIEND_username");
                    stmt.setString(1, FRIENDER_username);
                    stmt.setString(2, FRIEND_username);
                    ResultSet resultSet = stmt.executeQuery();
                    int count = 0;
                    while (resultSet.next()) { count++; }

                    if (count == 1) {
                            stmt = con.prepareStatement(
                                        "DELETE FROM Friendship "
                                         + "WHERE ? = FRIENDER_username AND ? = FRIEND_username;"
                                       );
                            stmt.setString(1, FRIENDER_username);
                            stmt.setString(2, FRIEND_username);
                            stmt.executeUpdate();
                    }
            } 
            catch (Exception e) {
                throw new SQLException(e);
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

    
    public static void removeReview(int R_ID)
                throws ClassNotFoundException, SQLException {
            Connection con = null;
            PreparedStatement stmt = null;

            try {
                    con = getConnection();
                    stmt = con.prepareStatement("SELECT * FROM Review "
                                    + "WHERE ? = R_ID");
                    stmt.setInt(1, R_ID);
                    ResultSet resultSet = stmt.executeQuery();
                    int count = 0;
                    while (resultSet.next()) { count++; }

                    if (count == 0) {
                            stmt = con.prepareStatement(
                                        "DELETE FROM Review WHERE ? = R_ID;"
                                       );
                            stmt.setInt(1, R_ID);
                            stmt.executeUpdate();
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
}
    
