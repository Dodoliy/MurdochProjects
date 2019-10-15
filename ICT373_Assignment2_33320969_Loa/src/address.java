/*
Title: Assignment 2 address class
Author: Loa Wilson James Metasurya
Date: 28/07/2019
Filename: address.java
Purpose: the address class creates an instance of address that provides address details that is to be used as part of a person instance. the person 
         instance will be composed of an address instance which provides address details for that particular and functions to set() and get() details
Assumptions/Conditions:
address class will receive inputs that declare streetNumber, streetName, suburb, and postalCode
address can use get methods to output/print the address variables that exist within the instance
*/
import java.io.Serializable;
public class address implements Serializable {
    /*
    Serializable is used to perform serialization, Java utilizes 
    serialization to create a version for a class so that data 
    can be serialized as well as de-serialized a class easily
    This way the instance persists even when the program is
    closed by saving the data into a file saved within a
    specified directory
    */
    private String streetNumber;
    private String streetName;
    private String suburb;
    private String postalCode;
    //CONSTRUCTOR
    address(String snum, String sname, String suburb, String pc)
    {
        this.streetNumber = snum;
        this.streetName = sname;
        this.suburb = suburb;
        this.postalCode = pc;
    }
    //get street number
    public String getStreetNumber()
    {
        return streetNumber;
    }
    //get street name
    public String getStreetName()
    {
        return streetName;
    }
    //get suburb
    public String getSuburb()
    {
        return suburb;
    }
    //get postal code
    public String getPostalCode()
    {
        return postalCode;
    }
    //set street number
    public void setStreetNumber(String snum)
    {
        this.streetNumber = snum;
    }
    //set street name
    public void setStreetName(String sname)
    {
        this.streetName = sname;
    }
    //set suburb
    public void setSuburb(String suburb)
    {
        this.suburb = suburb;
    }
    //set postal code
    public void setPostalCode(String pc)
    {
        this.postalCode = pc;
    }
}