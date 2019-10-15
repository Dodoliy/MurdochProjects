/*
Title: Assignment 2 family class
Author: Loa Wilson James Metasurya
Date: 28/07/2019
Filename: family.java
Purpose: a family class creates an instance of a family that defines the rootPerson of this family instance as well as an arrayList which provides
            a list of person that is part of this family instance. The family class can add members into the family and also establsih relationships
            between relatives of persons within the familyList in this instance of family.
Assumptions/Conditions:
family class will receive inputs by declaring a personRoot as well as adding new members of person into the familyList of the family instance.
the function to add member also allows added members to establsih their relatives that might already exist within the familyList
family class uses a get method to output the root person.
*/
import java.io.Serializable;
import java.util.ArrayList;
public class family implements Serializable {
    /*
    Serializable is used to perform serialization, Java utilizes 
    serialization to create a version for a class so that data 
    can be serialized as well as de-serialized a class easily
    This way the instance persists even when the program is
    closed by saving the data into a file saved within a
    specified directory
    */
    private person rootPerson; //root person of the family tree
    ArrayList<person> familyList;
    //constructor
    family(person rp)
    {
        familyList = new ArrayList<>();
        this.rootPerson = rp;
    }
    //set root person of the family
    public void setRootPerson(person rp)
    {
        this.rootPerson = rp;
        if(familyList.size()>0) //check if family is not empty
        {
            familyList.set(0, rootPerson);
        }
        else //if family is empty
        {
            familyList.add(rootPerson);
        }
    }
    //get the root person of the family
    public person getRootPerson()
    {
        return this.rootPerson;
    }
    //add a member to the family
    public void addMember(person newFamilyMember, String typeOfPerson, String personSearch)
    {
        int index = 0;
        for(int i=0;i<familyList.size();i++)//loop through to find the person's index within the family list
        {
            String tempSearch = familyList.get(i).getFirstName()+" "+familyList.get(i).getLastName();
            if(tempSearch.equals(personSearch))
            {
                index = i;
            }    
        }
        if(typeOfPerson.equals("Child")) //if new member is a child of the person
        {
            familyList.get(index).addChild(newFamilyMember);//add child 
        }
        else if(typeOfPerson.equals("Mother"))// if new member is a mother
        {
            familyList.get(index).setMother(newFamilyMember); //add mother
        }
        else if(typeOfPerson.equals("Father"))//if new member is a father
        {
            familyList.get(index).setFather(newFamilyMember);//add father
        }
        else if(typeOfPerson.equals("Spouse")) //if ne wmember is a spouse
        {
            familyList.get(index).setSpouse(newFamilyMember);//add spouse
        }
        
       familyList.add(newFamilyMember);//add new member to the family list
    }
    //set member which is only called when adding direct relatives of the root person
    public void addMemberFromRoot(person newFamilyMember, String typeOfPerson)
    {
        if(typeOfPerson.equals("Child")) //if new member is a child of the person
        {
            rootPerson.addChild(newFamilyMember); // add child
        }
        else if(typeOfPerson.equals("Mother"))// if new member is a mother
        {
            rootPerson.setMother(newFamilyMember); //add mother
        }
        else if(typeOfPerson.equals("Father"))//if new member is a father
        {
            rootPerson.setFather(newFamilyMember);//add father
        }
        else if(typeOfPerson.equals("Spouse")) //if new member is a spouse
        {
            rootPerson.setSpouse(newFamilyMember);//add spouse
        }
        
       familyList.add(newFamilyMember);//add new member to the family list
    }
}
