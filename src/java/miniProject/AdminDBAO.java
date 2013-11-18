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
 * @author ET
 */
public class AdminDBAO extends Query{

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

            if (count == 1) {
                stmt = con.prepareStatement("DELETE FROM Review WHERE ? = R_ID;");
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
    
    public static ArrayList<Review> queryReviews(boolean earlier, String dateinput, String keyword)
            throws ClassNotFoundException, SQLException {

        Connection con = null;
        PreparedStatement stmt = null;
        ArrayList<Review> reviews;

        try {
            con = getConnection();
            if (dateinput != "") {
                if (earlier) {
                    stmt = con.prepareStatement(
                            "SELECT * FROM Review " +
                            "WHERE comment_text LIKE ? " +
                            "AND ? > review_date");
                    stmt.setString(1, "%"+keyword+"%");
                    stmt.setString(2, dateinput);
                }
                else {
                    stmt = con.prepareStatement(
                    "SELECT * FROM Review " +
                    "WHERE comment_text LIKE ? " +
                    "AND ? < review_date");
                    stmt.setString(1, "%"+keyword+"%");
                    stmt.setString(2, dateinput);
                }
            }
            else {
                stmt = con.prepareStatement(
                "SELECT * FROM Review " +
                "WHERE comment_text LIKE ? ");   
                stmt.setString(1, "%"+keyword+"%");         
            }
            

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
}
