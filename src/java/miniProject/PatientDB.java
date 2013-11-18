/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package miniProject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import static miniProject.Query.getConnection;

/**
 *
 * @author jonathan
 */
public class PatientDB {
    
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

    public static void newFriendship(String friender_username, String friend_username)
                throws ClassNotFoundException, SQLException {
            Connection con = null;
            PreparedStatement stmt = null;

            try {
                    con = getConnection();
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

            } finally {
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
