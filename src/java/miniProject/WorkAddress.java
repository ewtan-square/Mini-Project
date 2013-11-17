/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package miniProject;

/**
 *
 * @author Francis
 */
public class WorkAddress {
    private int docID;
    private String province;
    private String city;
    private String postalCode;
    private String street;
    
    public WorkAddress(int docID, String province, String City, String postalCode, String street)
    {
        this.docID=docID;
        this.province=province;
        this.city=city;
        this.postalCode=postalCode;
        this.street=street;
    }
    
    public int getDocID() { return docID; }
    public void setDocID(int docID) { this.docID = docID; }
    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }
    public String getProvince() { return province; }
    public void setProvince(String province) { this.province = province; }
    public String getPostalCode() { return postalCode; }
    public void setPostalCode(String postalCode) { this.postalCode = postalCode; }
    public String getStreet() { return street; }
    public void setStreet(String street) { this.street = street; }
}
