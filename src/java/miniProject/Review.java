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
    private int docID;
    private int patientID;
    private int reviewID;
    private int rating;
    private Boolean recommendation;
    private String comment;
    private String date;
    
    public Review(int docID, int patientID, int reviewID, int rating, 
            Boolean recommendation, String comment, String date)
    {
        this.docID = docID;
        this.patientID = patientID;
        this.reviewID = reviewID;
        this.rating = rating;
        this.recommendation = recommendation;
        this.comment = comment;
        this.date = date;
    }
    
    public int getDocID() { return docID; }
    public void setDocID(int docID) { this.docID = docID; }
    public int getPatientID() { return patientID; }
    public void setPatientID(int patientID) { this.patientID = patientID; }
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
