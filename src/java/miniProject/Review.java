/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package miniProject;

/**
 *
 * @author Francis
 */
public class Review {
    private String docUsername;
    private String patientUsername;
    private int reviewID;
    private int rating;
    private Boolean recommendation;
    private String comment;
    private String date;
    
    public Review(String docUsername, String patientUsername, int reviewID, int rating, 
            Boolean recommendation, String comment, String date)
    {
        this.docUsername = docUsername;
        this.patientUsername = patientUsername;
        this.reviewID = reviewID;
        this.rating = rating;
        this.recommendation = recommendation;
        this.comment = comment;
        this.date = date;
    }
    
    public Review(String docUsername, String patientUsername, int rating, 
            Boolean recommendation, String comment, String date)
    {
        this.docUsername = docUsername;
        this.patientUsername = patientUsername;
        this.rating = rating;
        this.recommendation = recommendation;
        this.comment = comment;
        this.date = date;
    }
    
    public String getDocUsername() { return docUsername; }
    public void setDocID(String docUsername) { this.docUsername = docUsername; }
    public String getPatientUsername() { return patientUsername; }
    public void setPatientUsername(String patientUsername) { this.patientUsername = patientUsername; }
    public int getReviewID() { return reviewID; }
    public void setReviewID(int reviewID) { this.reviewID = reviewID; }
    public int getRating() { return rating; }
    public void setRating(int rating) { this.rating = rating; }
    public Boolean getRecommendation() { return recommendation; }
    public void setRecommendation(Boolean recommendation) { this.recommendation = recommendation; }
    public String getComment() { return comment; }
    public void setComment(String comment) { this.comment = comment; }
    public String getDate() { return date; }
    public void setDate(String date) { this.date = date; }
}
