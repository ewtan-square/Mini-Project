/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package miniProject;

import java.util.ArrayList;

/**
 *
 * @author Francis
 */
public class Patient {
    private int patientID;
    private String username;
    private String firstName;
    private String gender;
    private String lastName;
    private String dateOfBirth;
    private String email;
    private String homeProvince;
    private String homeCity;
    private String homePostalCode;
    private String homeStreet;
    private ArrayList<Doctor> doctors;
    private ArrayList<Patient> friends;
    private ArrayList<Review> reviews;
    
    public Patient(int patientID, String username, String firstName, String gender, String lastName, String dateOfBirth,
            String email, String homeProvince, String homeCity, String homePostalCode, String homeStreet, 
            ArrayList<Doctor> doctors, ArrayList<Patient> friends, ArrayList<Review> reviews)
    {
        this.patientID = patientID;
        this.username = username;
        this.firstName = firstName;
        this.gender = gender;
        this.lastName = lastName;
        this.email = email;
        this.homeProvince = homeProvince;
        this.homeCity = homeCity;
        this.homePostalCode = homePostalCode;
        this.homeStreet = homeStreet;
        this.dateOfBirth = dateOfBirth;
        this.doctors = doctors;
        this.friends = friends;
        this.reviews =reviews;
    }
    
    public int getPatientID() { return patientID; }
    public void setPatientID(int patientID) { this.patientID = patientID; }
    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }
    public String getDOB() { return dateOfBirth; }
    public void setDOB(String DOB) { this.dateOfBirth = DOB; }
    public String getEmail() { return email; }
    public void setEmail(String email) {this.email = email; }
    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }
    public String getHomeCity() { return homeCity; }
    public void setHomeCity(String homeCity) { this.homeCity = homeCity; }
    public String getHomeProvince() { return homeProvince; }
    public void setHomeProvince(String province) { this.homeProvince = province; }
    public String getHomePostalCode() { return homePostalCode; }
    public void setHomePostalCode(String homePostalCode) { this.homePostalCode = homePostalCode; }
    public String getHomeStreet() { return homeStreet; }
    public void setHomeStreet(String homeStreet) { this.homeStreet = homeStreet; }
    public ArrayList<Doctor> getDoctors() { return doctors; }
    public void addDoctor(Doctor doctor) { this.doctors.add(doctor); }
    public ArrayList<Patient> getFriends() { return friends; }
    public void addFriends(Patient patient) { this.friends.add(patient); }
    public ArrayList<Review> getReviews() { return reviews; }
    public void addReview(Review review) { this.reviews.add(review); }
}
