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
public class Doctor {
    private int docID;
    private String firstName;
    private String gender;
    private String lastName;
    private String dateOfBirth;
    private int licenseYear;
    private String homeProvince;
    private String homeCity;
    private String homePostalCode;
    private String homeStreet;
    private ArrayList<WorkAddress> workAddresses;
    private ArrayList<Patient> patients;
    private ArrayList<Review> reviews;
    private ArrayList<String> specializations;
    
    public Doctor(int docID,String firstName, String lastName, String gender, String dateOfBirth,
            int licenseYear, String homeProvince, String homeCity, String homePostalCode, String homeStreet, 
            ArrayList<WorkAddress> workAddresses, ArrayList<Patient> patients, ArrayList<Review> reviews,
            ArrayList<String> specializations)
    {
        this.docID = docID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.licenseYear = licenseYear;
        this.homeCity = homeCity;
        this.homeProvince = homeProvince;
        this.homePostalCode = homePostalCode;
        this.homeStreet = homeStreet;
        this.workAddresses = workAddresses;
        this.patients = patients;
        this.reviews = reviews;
        this.specializations = specializations;
    }
    
    public int getDocID() { return docID; }
    public void setDocID(int docID) { this.docID = docID; }
    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }
    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }
    public String getDOB() { return dateOfBirth; }
    public void setDOB(String DOB) { this.dateOfBirth = DOB; }
    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }
    public int getLicenseYear() { return licenseYear; }
    public void setLicenseYear(int licenseYear) { this.licenseYear = licenseYear; }
    public String getHomeCity() { return homeCity; }
    public void setHomeCity(String homeCity) { this.homeCity = homeCity; }
    public String getHomeProvince() { return homeProvince; }
    public void setHomeProvince(String province) { this.homeProvince = province; }
    public String getHomePostalCode() { return homePostalCode; }
    public void setHomePostalCode(String homePostalCode) { this.homePostalCode = homePostalCode; }
    public String getHomeStreet() { return homeStreet; }
    public void setHomeStreet(String homeStreet) { this.homeStreet = homeStreet; }
    public ArrayList<WorkAddress> getWorkAddresses() { return workAddresses; }
    public void addWorkAddress(WorkAddress workAddress) { this.workAddresses.add(workAddress); }
    public ArrayList<Patient> getPatients() { return patients; }
    public void addPatient(Patient patient) { this.patients.add(patient); }
    public ArrayList<Review> getReviews() { return reviews; }
    public void addReview(Review review) { this.reviews.add(review); }
    public ArrayList<String> getSpecializations() { return specializations; }
    public void addSpecialization(String specialization) { this.specializations.add(specialization); }
}
