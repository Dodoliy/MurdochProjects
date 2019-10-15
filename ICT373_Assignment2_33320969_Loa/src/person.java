/*
Title: Assignment 2 person class
Author: Loa Wilson James Metasurya
Date: 28/07/2019
Filename: person.java
Purpose: the person class creates an instance of person that provides details about the person, address, as well as relatives associated to that 
        instance of person. the person instance is composed of an address instance which provides detail about a person's address. A person also 
        has variables that can be modified that define the person's direct relatives.
Assumptions/Conditions:
person class will receive inputs that declare firstName, lastName, marriageSurname, gender, address, lifeDescription, mother, father, spouse
and listOfChildren. A person class will also be able to add children to themselves as well as modify their own relatives through set methods
when need be.
person can use it's get methods to output all the variables/details that exist within the instance of person including the address instance it is
composed of as well as the arraylist "listOfChildren"
*/
import java.io.Serializable;
import java.util.ArrayList;
public class person implements Serializable{
    /*
    Serializable is used to perform serialization, Java utilizes 
    serialization to create a version for a class so that data 
    can be serialized as well as de-serialized a class easily
    This way the instance persists even when the program is
    closed by saving the data into a file saved within a
    specified directory
    */
    private String firstName;
    private String lastName;
    private String marriageSurname;
    private String gender;
    private address address;
    private String lifeDescription;
            person mother;
            person father;
            person spouse;
    private ArrayList<person> listOfChildren = new ArrayList<>();
    //person constructor
    public person(String fname, String lname, String mname, String gender, address address, String ld)
    {
        this.firstName = fname;
        this.lastName = lname;
        this.marriageSurname = mname;
        this.gender = gender;
        this.address = address;
        this.lifeDescription = ld;
    }
    //get first name
    public String getFirstName()
    {
        return firstName;
    }
    //get last name
    public String getLastName()
    {
        return lastName;
    }
    //get maiden name
    public String getMarriageSurname()
    {
        return marriageSurname;
    }
    //get gender
    public String getGender()
    {
        return gender;
    }
    //get address
    public address getAddress()
    {
        return address;
    }
    //get life description
    public String getLifeDescription()
    {
        return lifeDescription;
    }
    //set first name
    public void setFirstName(String fname)
    {
        this.firstName = fname;
    }
    //set last name
    public void setLastName(String lname)
    {
        this.lastName = lname;
    }
    //set maiden name
    public void setMarriageSurname(String mname)
    {
        this.marriageSurname = mname;
    }
    //set gender
    public void setGender(String gen)
    {
        String tempGender = gen;
        if(tempGender =="Male" || tempGender =="Female")
        {
            this.gender = tempGender;
        }
        else
        {
            this.gender="Male"; //if value is neither M/F set the gender to default male 'M'
        }
    }
    //set address
    public void setAddress (address address)
    {
        this.address = address;
    }
    //set life description
    public void setLifeDescription(String ld)
    {
        this.lifeDescription = ld;
    }
    
    //METHODS TO GET/SET FATHER/MOTHER/CHILDREN
    //get mother
    public person getMother()
    {
        return mother;
    }
    //get father
    public person getFather()
    {
        return father;
    }
    //get spouse
    public person getSpouse()
    {
        return spouse;
    }
    //get list of children
    public ArrayList<person> getChildren()
    {
        return listOfChildren;
    }
    //set this mother
    public void setMother(person mother)
    {
        if(this.getFather()!=null)//if this person has a father
        {   //set mother as father's spouse and copy list of children
            mother.spouse = this.getFather();
            this.getFather().spouse = mother;
            mother.listOfChildren = this.getFather().getChildren();
        }
        else
        {   //else just add this as a child to the mother
            mother.addChildNormal(this);
        }
        this.mother = mother; //set this mother to parameter mother
    }
    //set this father
    public void setFather(person father)
    {
        if(this.getMother()!=null)//if this person has a mother
        {   //set father as mother's spouse and copy list of children
            father.spouse = this.getMother();
            this.getMother().spouse = father;
            father.listOfChildren = this.getMother().getChildren();
        }
        else
        {   //else just add this as a child to the father
            father.addChildNormal(this);
        }
        this.father = father; //set this person's father as parameter father
    }
    //set this spouse
    public void setSpouse(person spouse)
    {
        for(int i=0;i<this.getChildren().size();i++)//loop through this person's list of children
        {
            if(this.getChildren().get(i).getFather()!=null)//if children has a father
            {
                this.getChildren().get(i).setMother(spouse);//set spouse as mother
            }
            else
            {
                this.getChildren().get(i).setFather(spouse);//else set spouse as father
            }
        }
        spouse.listOfChildren = this.getChildren();//copy list of children to spouse
        spouse.spouse = this;//set spouse's spouse as this person
        this.spouse = spouse;//set this person's spouse as parameter spouse
    }
    //function to add children
    public void addChild(person child)
    {
        if(this.gender == "Male")//if this person is male
        {
            child.father = this; //child's father is this person
            if(this.getSpouse()!=null) //if this person has a spouse
            child.mother = this.getSpouse(); //child's mother is this person's spouse
        }
        else
        {   //else if this person is anything but "Male"
            child.mother = this; //child's mother is this person
            if(this.getSpouse()!=null)//if this person has a spouse
            child.father = this.getSpouse();//child's father is this person's spouse
        }
        
        listOfChildren.add(child);//add child to the list of children
        if(this.getSpouse()!=null)//if spouse exists
        this.getSpouse().listOfChildren = this.getChildren();//copy list of children
    }
    //normal function add child normally for parents
    public void addChildNormal(person child)
    {
        listOfChildren.add(child);
    }
}
