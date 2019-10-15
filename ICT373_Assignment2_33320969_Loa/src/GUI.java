/*
Title: Assignment 2 GUI
Author: Loa Wilson James Metasurya
Date: 28/07/2019
Filename: GUI.java
Purpose: the GUI class is the main class of the whole program. It generates the GUI for the program as well as provide the necessary method for the
            program to properly function. The GUI class utilizes address, family, and person instances to perform the methods within the GUI class
            the GUI also includes validation method to properly check for errors when performing critical functions of the program. The GUI class
            creates the main look (GUI) of the program and provides the functions that enable users to interact properly with the program's interface
Assumptions/Conditions:
The GUI will mostly receive user input on data that needs to be inputted to establish the details to make a new person; this person cam then be 
added into the family tree in the GUI and reflected back in the treeView on the left side of the program. The GUI also accepts user input to update
members within the family tree and options to create, save, and load family trees. The GUI rely on user input so that functions can be executed and 
data can be inputted.
The GUI outputs the main interface of the program in which the user could interact with textfields, textareas, buttons, and the treeView. The GUI
generates a treeView to display the family tree of the current rootPerson of the family instance and generated the treeView based on the relatives
of the rootPerson. The GUI also outputs alerts that inform user when validation checks fail and the GUI also provides the full details of a person
that is currently selected/clicked in the treeView.
*/
import java.io.*;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToolBar;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
//ABOVE ARR ALL THE IMPORTED JAVAFX COMPONENTS
//GUI CLASS
public class GUI extends Application {
    //CURRENT FAMILY TREE IN USE
    private address defaultAddress = new address("","","","");
    private person defaultPerson = new person("","","","Male",defaultAddress,"");
    private family currentFamilyTree = new family(defaultPerson);
    //TEMPORARY STAGE TO CREATE NEW WINDOWS etc.
    private Stage tempStage;
    
    //VIEWMODE BOOLEAN TO SWITCH BETWEEN VIEWINGMODE AND EDIITNGMODE
    boolean viewingModeON = true;
    
    //TOOLBAR, TITLE,HEADER AND 3 BUTTON
    ToolBar viewingOrEditingToolbar = new ToolBar();
    Button viewingModeButton = new Button("Viewing Mode");
    Button editingModeButton = new Button("Editing Mode");
    
    //WELCOME LABEL
    Label welcome = new Label("Welcome to the Family Tree Program\n");
    
    //3 BUTTONS
    Button createTreeButton = new Button("Create family tree");
    Button saveTreeButton = new Button("Save family tree");
    Button loadTreeButton = new Button("Load family tree");
    //BUTTON HBOX
    HBox buttonHBox = new HBox(createTreeButton, saveTreeButton, loadTreeButton);
    
    //RIGHT SIDE DETAILS PANEL INTIALIZATION
    //PERSON INFO SECTION
    Label personHeader = new Label("Person Info:");
    
    //NAME DETAILS
    Label personName = new Label("Name: ");
    TextField personNameTextField = new TextField();
        
    //SURNAME DETAILS
    Label personSurname = new Label("Surname: ");
    TextField personSurnameTextField = new TextField();
        
    //MAIDENNAME DETAILS
    Label personMaidenName = new Label("Maiden Name: ");
    TextField personMaidenNameTextField = new TextField();
       
    //GENDER DETAILS
    Label personGender = new Label("Gender: ");
    ChoiceBox personGenderChoiceBox = new ChoiceBox();   
    
    //LIFE DESCRIPTION
    Label personLifeDescription = new Label("Life Description: ");
    TextArea personLifeDescriptionTextArea = new TextArea();
    
    //ADDRESS INFO SECTION
    Label addressHeader = new Label("Address Info:");
    
    //STREET NUMBER DETAILS
    Label streetNumberDescription = new Label("Street Number: ");
    TextField streetNumberDescriptionTextField = new TextField();
        
    //STREET NAME DETAILS
    Label streetNameDescription = new Label("Street Name: ");
    TextField streetNameDescriptionTextField = new TextField();
        
    //SUBURB DETAILS
    Label suburbDescription = new Label("Suburb: ");
    TextField suburbDescriptionTextField = new TextField();
        
    //POSTCODE DETAILS
    Label postcodeDescription = new Label("Postcode: ");
    TextField postcodeDescriptionTextField = new TextField();
        
    //RELATIVE INFO SECTION
    Label relativeHeader = new Label("Relative Info:");
    
    //FATHER DETAILS
    Label fatherDescription = new Label("Father: ");
    TextField fatherDescriptionTextField = new TextField();
        
    //MOTHER DETAILS
    Label motherDescription = new Label("Mother: ");
    TextField motherDescriptionTextField = new TextField();
        
    //SPOUSE DETAILS
    Label spouseDescription = new Label("Spouse: ");
    TextField spouseDescriptionTextField = new TextField();
        
    //CHILDREN/GRANDCHILDREN LABELS AND COMBOBOX DETAILS GRIDPANE
    Label childrenDescription = new Label("Children: ");
    ComboBox childrenComboBox = new ComboBox();
    Label grandChildrenDescription = new Label("Grand Children: ");
    ComboBox grandChildrenComboBox = new ComboBox();
    HBox childrenAndGrandChildrenLabelHBox = new HBox();
    HBox childrenAndGrandChildrenDescriptionHBox = new HBox();
    
    //CREATING TREEVIEW
    TreeItem rootItem = new TreeItem("--No data found--");
    TreeView<String> treeView = new TreeView<String>();
    ScrollPane treeScrollPane = new ScrollPane(); //ADDING TREEVIEW SCROLLPANE
    
    //CREATING ADD RELATIVE AND UPDATE BUTTON AS WELL AS GRIDPANE
    MenuItem addFatherRelativeButton = new MenuItem("Add Father");
    MenuItem addMotherRelativeButton = new MenuItem("Add Mother");
    MenuItem addSpouseRelativeButton = new MenuItem("Add Spouse");
    MenuItem addChildRelativeButton = new MenuItem("Add Child");
    MenuButton addRelativeMenu = new MenuButton("Add Relative", null, addFatherRelativeButton, addMotherRelativeButton, addSpouseRelativeButton, addChildRelativeButton);
    Button updateButton = new Button("Update Detail");
    GridPane addRelativeButtonGrid = new GridPane();
    GridPane updateButtonGrid = new GridPane();

    //CREATING BOXES FOR TREEVIEW AS WELL AS DESCRIPTION/DETAILS
    VBox treeViewVBox = new VBox(treeView); //INSERTING TREEVIEW INTO A SECTION
    //LEFT SIDE DETAILS PANEL
    VBox detailsVBox = new VBox(personHeader,
                personName, personNameTextField,
                personSurname, personSurnameTextField,
                personMaidenName, personMaidenNameTextField,
                personGender, personGenderChoiceBox, 
                personLifeDescription, personLifeDescriptionTextArea, 
                updateButtonGrid
                );
    //RIGHT SIDE DETAILS PANEL
    VBox detailsVBox2 = new VBox(addressHeader,
                streetNumberDescription, streetNumberDescriptionTextField,
                streetNameDescription, streetNameDescriptionTextField,
                suburbDescription, suburbDescriptionTextField,
                postcodeDescription, postcodeDescriptionTextField,
                relativeHeader,
                fatherDescription, fatherDescriptionTextField,
                motherDescription, motherDescriptionTextField,
                spouseDescription, spouseDescriptionTextField,
                childrenAndGrandChildrenLabelHBox, childrenAndGrandChildrenDescriptionHBox,
                addRelativeButtonGrid
                );
    //COMBINING THE TREEVIEW, LEFT SIDE DETAILS PANEL, AND RIGHT SIDE DETAILS PANEL IN HBOX
    HBox treeAndDetailsHBox = new HBox(treeViewVBox, detailsVBox, detailsVBox2);
    
    //COMBINING ALL NODES AND CONTROLS INTO 1 VBOX
    VBox GUIVBox = new VBox(viewingOrEditingToolbar, welcome, buttonHBox, treeAndDetailsHBox);
    
    //DEFAULT SCENE FOR THE MAIN GUI WINDOW
    Scene defaultScene = new Scene(GUIVBox, 640,605); //SET NEW SCENE
    
    //-------------------------ADD MEMBER NEW WINDOW VARIABLES-------------------------
    //ADD NEW MEMBER LABEL
     Label addPersonLabel = new Label();
    //GRIDPANE FOR ALL THE INPUTS AND LABELS
    GridPane addPersonGrid = new GridPane();
    //FIRSTNAME INPUT
    Label addPersonName = new Label("First Name:");
    TextField addPersonNameInput = new TextField();
    //SURNAME INPUT
    Label addPersonSurname = new Label("Surname:");
    TextField addPersonSurnameInput = new TextField();
    //MAIDENNAME INPUT
    Label addPersonMaidenName = new Label("Maiden Name:");
    TextField addPersonMaidenNameInput = new TextField();
    //GENDER INPUT
    Label addPersonGender = new Label("Gender:");
    ChoiceBox addPersonGenderInput = new ChoiceBox();
    //ADDRESS HEADER
    Label addPersonAddressLabel = new Label("Address Details");
    //STREETNUMBER INPUT
    Label addPersonStreetNumber = new Label("Street Number:");
    TextField addPersonStreetNumberInput = new TextField();
    //STREETNAME INPUT
    Label addPersonStreetName = new Label("Street Name:");
    TextField addPersonStreetNameInput = new TextField();
    //SUBURB INPUT
    Label addPersonSuburb = new Label("Suburb:");
    TextField addPersonSuburbInput = new TextField();
    //POSTCODE INPUT
    Label addPersonPostcode = new Label("Postcode:");
    TextField addPersonPostcodeInput = new TextField();
    //LIFEDESCRIPTION INPUT
    Label addPersonLifeDescription = new Label("Life Description:");
    TextArea addPersonLifeDescriptionInput = new TextArea();
    //ADD MEMBER BUTTON
    Button addMemberConfirmButton = new Button("Add Member");
    //ADD MEMBER BUTTON STACKPANE
    StackPane addMemberConfirmButtonStackPane = new StackPane(addMemberConfirmButton);
    //COMBINE ALL INPUTS AND LABELS FOR NEW WINDOW ONE VBOX CONTAINER
    VBox rootPersonDetails = new VBox(addPersonLabel, addPersonGrid, addPersonLifeDescriptionInput, addMemberConfirmButtonStackPane);
    //CREATE NEW STAGE AND SCENE FOR THE NEW ADD MEMBER WINDOW
    Stage addPersonWindow = new Stage();//CREATE NEW STAGE
    Scene addPersonScene = new Scene(rootPersonDetails, 270, 460);
    
    //-------------------SAVE FAMILY WINDOW---------------------------
    Label saveFileName = new Label("Name of file: ");
    TextField saveFileNameInput = new TextField();
    Button saveFileNameButton = new Button("Save");
    StackPane saveFileNameButtonStackPane = new StackPane(saveFileNameButton);
    VBox saveFileVBox = new VBox(saveFileName, saveFileNameInput, saveFileNameButtonStackPane);
    Scene saveFamilyScene = new Scene(saveFileVBox, 150, 70);
    Stage saveFamilyWindow = new Stage();
    
    public static void main(String[] args) {
        Application.launch(args);    
    }
    
    //METHOD THAT STARTS THE GUI CREATION
    @Override
    public void start(Stage MainTreeGUI) throws Exception {
        MainTreeGUI.setTitle("Family Tree Program");
        viewing_editingMode(true);//viewing mode initially
        viewingModeButton.setDisable(true);//set viewing mode disabled until a tree started
        editingModeButton.setDisable(true);//set editing mode disabled until a tree started
        //INITIALIZING HOW THE GUI LOOKS
        //TOOLBAR
        viewingOrEditingToolbar.getItems().add(viewingModeButton);
        viewingOrEditingToolbar.getItems().add(editingModeButton);
        viewingOrEditingToolbar.setPadding(new Insets(0,0,0,0));
        
        //WELCOME LABEL FONT AND PADDING
        welcome.setPadding(new Insets(10,0,0,10));
        welcome.setFont(new Font("Century", 36));
        
        //3 BUTTON CSS
        createTreeButton.setStyle("-fx-margin: 10 20 10 20");
        saveTreeButton.setStyle("-fx-margin: 10 20 10 20");
        loadTreeButton.setStyle("-fx-margin: 10 20 10 20");
        buttonHBox.setSpacing(7);
        buttonHBox.setPadding(new Insets(10,0,0,10));
   
        
        //PERSON HEADER FONT
        personHeader.setFont(new Font("Times New Roman", 18));
        
        //GENDER CHOICES
        personGenderChoiceBox.getItems().add("Male");
        personGenderChoiceBox.getItems().add("Female");
        
        //LIFE DESCRIPTION FORMATTING
        personLifeDescriptionTextArea.setPrefWidth(50);
        personLifeDescriptionTextArea.setPrefHeight(182);
        
        //ADDRESS INFO HEADEER
        addressHeader.setFont(new Font("Times New Roman", 18));
        
        //RELATIVE INFO HEADER
        relativeHeader.setFont(new Font("Times New Roman", 18));
        relativeHeader.setPadding(new Insets(10,0,0,0));
        
        //SET RELATIVE TEXTFIELDS UNEDITABLE
        fatherDescriptionTextField.setDisable(true);
        motherDescriptionTextField.setDisable(true);
        spouseDescriptionTextField.setDisable(true);
        
       //GRANDCHILDREN AND CHILDREN GRID FORMATTING
        childrenAndGrandChildrenLabelHBox.getChildren().add(childrenDescription);
        childrenAndGrandChildrenLabelHBox.getChildren().add(grandChildrenDescription);
        childrenAndGrandChildrenDescriptionHBox.getChildren().add(childrenComboBox);
        childrenAndGrandChildrenDescriptionHBox.getChildren().add(grandChildrenComboBox);
        childrenComboBox.setPrefWidth(20);
        grandChildrenComboBox.setPrefWidth(20);
        childrenAndGrandChildrenLabelHBox.setSpacing(30);
        childrenAndGrandChildrenDescriptionHBox.setSpacing(30);
        
        //TREEVIEW INITIAL ROOT ITEM
        treeView.setRoot(rootItem);
        //treeView.setPrefHeight(529);
        treeScrollPane.setContent(treeView);
 
        //ADD RELATIVE AND UPDATE BUTTON FORMATTING
        addFatherRelativeButton.setStyle("-fx-font-family:'Arial'; -fx-font-weight:bold;");
        addMotherRelativeButton.setStyle("-fx-font-family:'Arial'; -fx-font-weight:bold;");
        addSpouseRelativeButton.setStyle("-fx-font-family:'Arial'; -fx-font-weight:bold;");
        addChildRelativeButton.setStyle("-fx-font-family:'Arial'; -fx-font-weight:bold;");
        updateButton.setStyle("-fx-font-family:'Arial'; -fx-font-size: 17px; -fx-font-weight:bold;");
        addRelativeButtonGrid.setPadding(new Insets(35,0,0,30));
        updateButtonGrid.setPadding(new Insets(30,0,0,10));
        addRelativeButtonGrid.add(addRelativeMenu, 1,0,1,1);
        updateButtonGrid.add(updateButton, 0, 0, 1, 1);

        //TREE AND DETAILS CONTAINER FORMATTING
        treeAndDetailsHBox.setSpacing(30);
        treeAndDetailsHBox.setPadding(new Insets(15,0,0,0));
        
        //SHOWING THE GUI STAGE FOR THE MAIN PRIMARY GUI
        MainTreeGUI.setScene(defaultScene); //SET SCENE
        MainTreeGUI.setResizable(false);
        this.tempStage = MainTreeGUI; //creat temp main GUI to be used in functions
        MainTreeGUI.show();
        
        //---------------NEW ADDMEMBER POPUP WINDOW GUI CONTROLS AND FORMATTING-------------
        //ADD PERSON LABEL 
        addPersonLabel.setFont(new Font("Century", 24));
        addPersonLabel.setPadding(new Insets(10,0,0,10));
        //LABEL AND INPUT GRID 
        addPersonGrid.setPadding(new Insets(10,0,0,10));
        addPersonGrid.setHgap(10);
        addPersonGrid.setVgap(5);
        //GENDER INPUTS
        addPersonGenderInput.getItems().add("Male");
        addPersonGenderInput.getItems().add("Female");
        addPersonGenderInput.getSelectionModel().selectFirst();
        //LIFE DESCRIPTION TEXT AREA HEIGHT
        addPersonLifeDescriptionInput.setPrefHeight(100);
               
        //ADD ALL LABELS AND INPUTS INTO A GRID TO ARRANGE
        addPersonGrid.add(addPersonName, 0, 0);
        addPersonGrid.add(addPersonNameInput, 2, 0);
        addPersonGrid.add(addPersonSurname, 0, 1);
        addPersonGrid.add(addPersonSurnameInput, 2, 1);
        addPersonGrid.add(addPersonMaidenName, 0, 2);
        addPersonGrid.add(addPersonMaidenNameInput, 2, 2);
        addPersonGrid.add(addPersonGender, 0, 3);
        addPersonGrid.add(addPersonGenderInput, 2, 3);
        addPersonGrid.add(addPersonAddressLabel, 0, 4);
        addPersonGrid.add(addPersonStreetNumber, 0, 5);
        addPersonGrid.add(addPersonStreetNumberInput, 2, 5);
        addPersonGrid.add(addPersonStreetName, 0, 6);
        addPersonGrid.add(addPersonStreetNameInput, 2, 6);
        addPersonGrid.add(addPersonSuburb, 0, 7);
        addPersonGrid.add(addPersonSuburbInput, 2, 7);
        addPersonGrid.add(addPersonPostcode, 0, 8);
        addPersonGrid.add(addPersonPostcodeInput, 2, 8);
        addPersonGrid.add(addPersonLifeDescription, 0, 9);
        //ADD MEMBER STACKPANE PADDING
        addMemberConfirmButtonStackPane.setPadding(new Insets(10,0,0,0));
        //ADD PERSON WINDOW
        addPersonWindow.setScene(addPersonScene);
        //MODALITY AND OWNER SET
        addPersonWindow.initModality(Modality.WINDOW_MODAL);
        addPersonWindow.initOwner(tempStage);
        //SET WHERE WINDOW WILL APPEAR 
        addPersonWindow.setX(tempStage.getX() +200);
        addPersonWindow.setY(tempStage.getY() +100);
        
        //SAVE FAMILY WINDOW
        saveFamilyWindow.setTitle("Save Family");
        saveFamilyWindow.setScene(saveFamilyScene);
        //-------------------INTERACTIONS WITH CONTROLS (EVENT HANDLING)-----------------------------
        //GO TO VIEW MODE
        viewingModeButton.setOnAction(e ->
        {
            viewing_editingMode(true);
        });
        //GO TO EDITING MODE
        editingModeButton.setOnAction(e ->
        {
            viewing_editingMode(false);
        });
        
        //CREATE A NEW FAMILY TREE/ADDING ROOT PERSON
        createTreeButton.setOnAction(e -> //WHEN THE CREATE TREE BUTTON IS CLICKED ADD NEW PERSON OF ROOT TYPE
        {
            addPersonWindow("Root");
            clearAddNewPersonInput(); 
            viewing_editingMode(true);
        }); 
        //SAVE THE CURRENT FAMILY TREE TO A FILE
        saveTreeButton.setOnAction(e ->
        {
            saveFamilyTreeWindow();
            viewing_editingMode(true);
        });
        //LOAD A FAMILY TREE FROM A FILE
        loadTreeButton.setOnAction(e ->
        {
            loadFamilyTree();
            viewing_editingMode(true);
        });
        //ADD FATHER BUTTON
        addFatherRelativeButton.setOnAction(e ->
        {
            addPersonWindow("Father");
            clearAddNewPersonInput(); 
        });
        //ADD MOTHER BUTTON
        addMotherRelativeButton.setOnAction(e ->
        {
            addPersonWindow("Mother");
            clearAddNewPersonInput(); 
        });
        //ADD CHILD BUTTON
        addChildRelativeButton.setOnAction(e ->
        {
            addPersonWindow("Child");
            clearAddNewPersonInput(); 
        });
        //ADD SPOUSE BUTTON
        addSpouseRelativeButton.setOnAction(e ->
        {
            addPersonWindow("Spouse");
            clearAddNewPersonInput(); 
        });
        //UPDATE DETAILS BUTTON
        updateButton.setOnAction(e ->
        {
            updateMember();
        });
        
    }
    
    //METHOD TO OPEN A POPUP WINDOW THAT ALLOWS USER INPUT FOR NEW MEMBER
    public void addPersonWindow(String personType)
           { 
               addPersonLabel.setText("Add New "+personType);
               //NEW WINDOW TITLE AND SCENE
               addPersonWindow.setTitle("Add New "+personType);
               
               //ACTION EVENT FOR WHEN ADD MEMBER CONFIRMATION BUTTON IS CLICKED
               addMemberConfirmButton.setOnAction(e -> saveNewPerson(personType));
               addPersonWindow.setResizable(false);
               addPersonWindow.show();
           }
    //METHOD TO SAVE/CONFIRM THE ADDED NEW PERSON
    public void saveNewPerson(String personType)
    {
        if(emptyInputValidationCheck()) //VALIDATION CHECK FOR EMPTY INPUT 
        {
            try
            {
                String tempStringGender = addPersonGenderInput.getValue().toString(); //convert gender input into String data
                //CREATING A PERSON INSTANCE TO BE ADDED INTO THE FAMILYLIST
                person newPerson = new person(addPersonNameInput.getText(), addPersonSurnameInput.getText(),
                                                addPersonMaidenNameInput.getText(), tempStringGender, new address(
                                                addPersonStreetNumberInput.getText(), addPersonStreetNameInput.getText(), 
                                                addPersonSuburbInput.getText(), addPersonPostcodeInput.getText()),
                                                addPersonLifeDescriptionInput.getText());
                
                if(personType.equals("Root"))//IF THIS ADDEDD PERSON IS THE ROOT PERSON
                {
                    currentFamilyTree = null;
                    currentFamilyTree = new family(newPerson); //create a new family tree
                    currentFamilyTree.familyList.add(newPerson); //add the new person as the root of the family tree
                }
                else
                {   
                    TreeItem selectedTreeItem = treeView.getSelectionModel().getSelectedItem(); //get the currently selected item in the treeview
                    if(selectedTreeItem == null || selectedTreeItem == treeView.getRoot()) //if null(nothing) or the root is selected
                    {
                        currentFamilyTree.addMemberFromRoot(newPerson, personType);//then add a the family member as direct relative of root person
                    }
                    else //if root is not selected
                    {
                        Object value = selectedTreeItem.getValue(); 
                        String personSearch = value.toString();//get the name of the current selected person
                        currentFamilyTree.addMember(newPerson, personType, personSearch);//add a member as a relative
                    }
                }
                addPersonWindow.close(); //close the add person window
                updateFamilyTree(); //update the family tree to display changes
            }
            catch(Exception e) //catch any exception
            {
                System.out.println(e); //print the exception
            }
        }
        else
        {
            Alert emptyInputAlert = new Alert(Alert.AlertType.ERROR); //send out an alert for empty input validation check or person existing already
            emptyInputAlert.setHeaderText("Not all data has been inputted OR person already exist within the family!");
            emptyInputAlert.showAndWait();
        }
    }
    
    //METHOD TO UPDATE THE MEMBERS/PERSONS WITHIN THE FAMILY LIST
    public void updateMember()
    {
        TreeItem selectedTreeItem = treeView.getSelectionModel().getSelectedItem(); //GET THE CURRENT SELECTED ITEM
        String personSearch = selectedTreeItem.getValue().toString(); //get the person's name
        if(updateMember_EmptyInputValidationCheck()) //VALIDATION CHECK FOR EMPTY INPUT
        {
            try
            {
                for(int i=0;i<currentFamilyTree.familyList.size();i++)//loop around the current family list
                {   //GET THE NAME OF A PERSON
                    String temp = currentFamilyTree.familyList.get(i).getFirstName()+" "+currentFamilyTree.familyList.get(i).getLastName();
                    if(temp.equals(personSearch))//IF THE SELECTED PERSON MATCHES A CERTAIN PERSON WITHIN THE FAMILY LIST
                    {
                        String tempStringGender = personGenderChoiceBox.getValue().toString();//get gender string
                        currentFamilyTree.familyList.get(i).setFirstName(personNameTextField.getText());//set first name
                        currentFamilyTree.familyList.get(i).setLastName(personSurnameTextField.getText());//set last name
                        currentFamilyTree.familyList.get(i).setMarriageSurname(personMaidenNameTextField.getText());//set maiden name
                        currentFamilyTree.familyList.get(i).setGender(tempStringGender);//set the gender
                        currentFamilyTree.familyList.get(i).setLifeDescription(personLifeDescriptionTextArea.getText());//set life description
                        currentFamilyTree.familyList.get(i).getAddress().setStreetNumber(streetNumberDescriptionTextField.getText());//set street number
                        currentFamilyTree.familyList.get(i).getAddress().setStreetName(streetNameDescriptionTextField.getText());//set street name
                        currentFamilyTree.familyList.get(i).getAddress().setSuburb(suburbDescriptionTextField.getText());//set suburb
                        currentFamilyTree.familyList.get(i).getAddress().setPostalCode(postcodeDescriptionTextField.getText());//set postal code
                    }
                }
                updateFamilyTree(); //update the family tree to reflect updated member
            }
            catch(Exception e)//catch any exception
            {
                System.out.println(e);
            }
        }
        else
        {
            Alert emptyInputAlert = new Alert(Alert.AlertType.ERROR);//send an alert when exception occurs
            emptyInputAlert.setHeaderText("Ensure all data has been inputted!");
            emptyInputAlert.showAndWait();
        }
    }
    //FUNCTION TO UPDATE THE DETAILS PANEL IN THE RIGHT SIDE ACCORDINGLY BASED ON THE CURRENT FAMILY LIUST
    public void updateDetailsPanel(TreeItem T)
    {
        String selectedPersonName = T.getValue().toString(); //get the selected person's name
        address a1 = new address("","","",""); //default address
        person selectedPerson = new person("","","","Male",a1,""); //default person
        
        for(int i=0;i<currentFamilyTree.familyList.size();i++) //loop through the family tree family list
        {   //get the name of the person in the list
            String searchPerson = currentFamilyTree.familyList.get(i).getFirstName()+" "+currentFamilyTree.familyList.get(i).getLastName();
            if(searchPerson.equals(selectedPersonName))//if name of selected person matches the current for loop person
            {
                selectedPerson = currentFamilyTree.familyList.get(i);//get the current person in the loop
            }
        }
        personNameTextField.setText(selectedPerson.getFirstName());//set textfield value as person first name
        personSurnameTextField.setText(selectedPerson.getLastName());//set textfield value as person last name
        personMaidenNameTextField.setText(selectedPerson.getMarriageSurname());//set textfield value as person maiden name
        if(selectedPerson.getGender().equals("Male"))//if person is male
        {
            personGenderChoiceBox.getSelectionModel().select(0);//select the male choicebox option
        }
        else
        {
            personGenderChoiceBox.getSelectionModel().select(1);//select the female choicebox option
        }
        personLifeDescriptionTextArea.setText(selectedPerson.getLifeDescription());//set textfield value as person life description
        streetNumberDescriptionTextField.setText(selectedPerson.getAddress().getStreetNumber());//set textfield value as person street num
        streetNameDescriptionTextField.setText(selectedPerson.getAddress().getStreetName());//set textfield value as person street name
        suburbDescriptionTextField.setText(selectedPerson.getAddress().getSuburb());//set textfield value as person suburb
        postcodeDescriptionTextField.setText(selectedPerson.getAddress().getPostalCode());//set textfield value as person postcode
        
        if(selectedPerson.getFather()!=null)//if person has a father
        {   //set textfield with father name
            fatherDescriptionTextField.setText(selectedPerson.getFather().getFirstName()+" "+selectedPerson.getFather().getLastName());
        }
        else
        {   //set father textfield to ""
            fatherDescriptionTextField.setText("");
        }
        if(selectedPerson.getMother()!=null)//if person has a mother
        {   //set textfield with mother name
            motherDescriptionTextField.setText(selectedPerson.getMother().getFirstName()+" "+selectedPerson.getMother().getLastName());
        }
        else
        {   //set mother to ""
            motherDescriptionTextField.setText("");
        }
        if(selectedPerson.getSpouse()!=null) //if person has a spouse
        {   //set textfield with spouse name
            spouseDescriptionTextField.setText(selectedPerson.getSpouse().getFirstName()+" "+selectedPerson.getSpouse().getLastName());
        }
        else
        {   //set spouse to ""
            spouseDescriptionTextField.setText("");
        }
        childrenComboBox = null;
        childrenComboBox = new ComboBox(); //create new combobox for children
        grandChildrenComboBox = null;
        grandChildrenComboBox = new ComboBox(); //create new grandchildren combobox
        for(int i=0;i<selectedPerson.getChildren().size();i++) //loop through the selected person's children 
        {
            childrenComboBox.getItems().add(selectedPerson.getChildren().get(i).getFirstName()); //get children's name
            childrenComboBox.getItems();
            if(selectedPerson.getChildren().get(i).getChildren().size()>0)//if person's children has children (grandchildren)
            {
                for(int j=0;j<selectedPerson.getChildren().get(i).getChildren().size();j++) //loop through grandchildren
                {
                    grandChildrenComboBox.getItems().add(selectedPerson.getChildren().get(i).getChildren().get(j).getFirstName());//add grand children
                }
            }
        }
        childrenComboBox.setPrefWidth(20);
        grandChildrenComboBox.setPrefWidth(20);
        childrenAndGrandChildrenDescriptionHBox.getChildren().set(0, childrenComboBox);//add combobox back to container
        childrenAndGrandChildrenDescriptionHBox.getChildren().set(1, grandChildrenComboBox);//add combobox back to container
    }
    //FUNCTION TO UPDATE THE TREEVIEW AND FAMILY TREE
    public void updateFamilyTree()
    {
          treeView = null;
          treeView = new TreeView(); //create new treeview
          //get the current root person treeItem
          TreeItem rootPersonItem = new TreeItem(currentFamilyTree.getRootPerson().getFirstName()+" "+currentFamilyTree.getRootPerson().getLastName());
          treeView.setRoot(rootPersonItem); //add new root person treeItem as root of treeview
          person treeRootPerson = currentFamilyTree.getRootPerson(); //get current root person in the familylist
          
          if(treeRootPerson.getFather()!=null || treeRootPerson.getMother()!=null)//if person has a mother or father
          { //DISPLAY BRANCH OF "PARENTS" AND DISPLAY MOTHER AND/OR FATHER NAME
              TreeItem rootParents = new TreeItem("Parents");
              if(treeRootPerson.getFather()!=null)
              rootParents.getChildren().add(new TreeItem(treeRootPerson.getFather().getFirstName()+" "+treeRootPerson.getFather().getLastName()));
              if(treeRootPerson.getMother()!=null)
              rootParents.getChildren().add(new TreeItem(treeRootPerson.getMother().getFirstName()+" "+treeRootPerson.getMother().getLastName()));
              rootPersonItem.getChildren().add(rootParents);
          }
          spouseAndChildrenUpdate(treeRootPerson, rootPersonItem); //loop infintely for children branches as long as it exists
          //UPDATE THE GUI CONTAINERS
          treeViewVBox.getChildren().set(0, treeView);
          //TREEVIEW LISTENER TO HANDLE WHEN THE TREEITEMS ARE CLICKED TO UPDATE THE DETAILS PANNEL
          treeView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> updateDetailsPanel(newValue));
    }
    //FUNCTION TO GET SPOUSE AND CHILDREN OF CURRENT PERSON IN THE TREE
    public void spouseAndChildrenUpdate(person currentPerson, TreeItem currentTree) //gets treeItem and person parameter to work with
    {
          if(currentPerson.getSpouse()!=null)//if current person has a spouse 
          {   //add a spouse branch and print the spouse name
              TreeItem spouseTree= new TreeItem("Spouse");
              spouseTree.getChildren().add (new TreeItem(currentPerson.getSpouse().getFirstName()+" "+currentPerson.getSpouse().getLastName()));
              currentTree.getChildren().add(spouseTree);
          }
          
          if(currentPerson.getChildren().size()>0)//if current person has children
          { //add the children branch and add child treeItem into the treeView
              TreeItem childTree = new TreeItem("Children");
              currentTree.getChildren().add(childTree);
            for(int i=0;i<currentPerson.getChildren().size();i++)//loop around the list of children
            {  //get the name of children and add the treeitem into the treeview
                TreeItem child = new TreeItem(currentPerson.getChildren().get(i).getFirstName()+" "+currentPerson.getChildren().get(i).getLastName());
                childTree.getChildren().add(child); //add the grandchildren
                //if current person's children has children (grandchildren)
                if(currentPerson.getChildren().get(i).getChildren().size()>0 || currentPerson.getChildren().get(i).getSpouse()!=null)
                {
                    person temp = currentPerson.getChildren().get(i);//get the current children
                    spouseAndChildrenUpdate(temp, child); //use recursion to do this method agian to continously get childrens of children
                }
            }
          }
    }
    //FUNCTION TO SAVE THE FAMILY TREE
    public void saveFamilyTreeWindow()
    {
        if(currentFamilyTree.getRootPerson().getFirstName()!="")//IF ROOT PERSON EXISTS
        {
            saveFamilyWindow.show();//show the new save window
            saveFileNameButton.setOnAction(e -> //ON BUTTON CLICK OF SAVE FAMILY TREE BUTTON
            {
                String filename = saveFileNameInput.getText(); //get the filename input
                saveFamilyTree(filename); //run function using filename parameter
            });
        }
        else
        {   //DISPLAY ALERT THAT ROOT PERSON DOES NOT EXIST/TREE DOES NOT EXIST
            Alert emptyInputAlert = new Alert(Alert.AlertType.ERROR);
            emptyInputAlert.setHeaderText("There is no family tree to save!");
            emptyInputAlert.showAndWait();
        }
    }
    
    public void saveFamilyTree(String s)
    {      
        if(s.trim().equals("")) //trin the filename input
        {   //alert if no input entered
            Alert emptyInputAlert = new Alert(Alert.AlertType.ERROR);
            emptyInputAlert.setHeaderText("Please enter a filename!");
            emptyInputAlert.showAndWait();         
        }
        else
        {
            String filename = s;
            FileOutputStream file_output; //start file output stream
            ObjectOutputStream object_output; //start object output stream 
            
            try
            {
                File fileDir = new File("FamilyTree"); //set directory name
                if(!fileDir.exists()) //if file directory doesn't exist
                {
                    if(fileDir.mkdir())
                    {
                        //System.out.println("Directory created");
                    }
                }
                else//directory already exists
                {
                    //System.out.println("Directory already exists");
                }
                file_output = new FileOutputStream("FamilyTree/"+filename+".famt"); //create new directory in project folder "FamilyTree" and filename with .famt extension
                object_output = new ObjectOutputStream(file_output);//object output stream
                object_output.writeObject(currentFamilyTree);//write the current family tree into the file
            }
            catch(FileNotFoundException e)//file not found exception
            {
                System.out.println(e);
            }
            catch(IOException ex)//IO exception
            {
                System.out.println(ex);
            }
            saveFamilyWindow.close();//close window
            saveFileNameInput.setText("");//set textfield to blank
        }
                   
    }
    //FUNCTION TO LOAD A FAMILY TREE FROM A .famt FILE
    public void loadFamilyTree()
    {
        String filePath;
        FileInputStream file_input;
        ObjectInputStream object_input;
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(new File(System.getProperty("user.dir")+"/FamilyTree"));//define initial directory for file choosing
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Family Tree","famt");//default file extension is set to .famt
        fileChooser.setSelectedExtensionFilter(extFilter); //set the extension filter
        File selectedFile = fileChooser.showOpenDialog(tempStage);//open dialog for file chooser
        filePath = selectedFile.getPath();//get file path
        try
        {
            file_input = new FileInputStream(filePath); //file input stream
            object_input = new ObjectInputStream(file_input); //object input stream
            currentFamilyTree = (family)object_input.readObject(); //read the current file for "family" instance
            object_input.close();//close objet input stream
            updateFamilyTree();//update family tree based on loaded family tree
        }
        catch(FileNotFoundException e)//file not found exception
        {
            System.out.println(e);
        }
        catch(IOException ex)//io exception
        {   //display alert that there was something wrong
            Alert emptyInputAlert = new Alert(Alert.AlertType.ERROR);
            emptyInputAlert.setHeaderText("Unable to load family tree, please load a .famt file!");
            emptyInputAlert.showAndWait();
        }
        catch(ClassNotFoundException exc)//class not found exception
        {
            System.out.println(exc);
        }
    }
    //FUNCTION TO SWTICH BETWEEN EDITING AND VIEWING MODE
    public void viewing_editingMode(boolean condition)
    {
        viewingModeON = condition;
        if(viewingModeON)//IF VIEWING MODE IS ON
        {//DISABLE/ENABLE BUTTONS/TEXTFIELDS/CHOICEBOXS APPROPRIATELY
            viewingModeButton.setDisable(true);
            editingModeButton.setDisable(false);
            personNameTextField.setDisable(true);
            personSurnameTextField.setDisable(true);
            personMaidenNameTextField.setDisable(true);
            personNameTextField.setDisable(true);
            personGenderChoiceBox.setDisable(true);
            personLifeDescriptionTextArea.setDisable(true);
            streetNameDescriptionTextField.setDisable(true);
            streetNumberDescriptionTextField.setDisable(true);
            suburbDescriptionTextField.setDisable(true);
            postcodeDescriptionTextField.setDisable(true);
            updateButton.setDisable(true);
            childrenComboBox.setDisable(false);
            grandChildrenComboBox.setDisable(false);
            addRelativeMenu.setDisable(true);     
        }
        else
        {//EDITING MODE DISABLE/ENABLE BUTTONS/TEXTFIELDS/CHOICEBOXS APPROPRIATELY
            editingModeButton.setDisable(true);
            viewingModeButton.setDisable(false);
            personNameTextField.setDisable(false);
            personSurnameTextField.setDisable(false);
            personMaidenNameTextField.setDisable(false);
            personNameTextField.setDisable(false);
            personGenderChoiceBox.setDisable(false);
            personLifeDescriptionTextArea.setDisable(false);
            streetNameDescriptionTextField.setDisable(false);
            streetNumberDescriptionTextField.setDisable(false);
            suburbDescriptionTextField.setDisable(false);
            postcodeDescriptionTextField.setDisable(false);
            updateButton.setDisable(false);
            childrenComboBox.setDisable(false);
            grandChildrenComboBox.setDisable(false);
            addRelativeMenu.setDisable(false); 
        }
    }
    //FUNCTION TO RUN VALIDATION CHECK FOR EMPTY INPUT WHEN ADDING NEW PERSON MEMBER
    public boolean emptyInputValidationCheck()
    {
        if(addPersonNameInput.getText().trim().equals(""))
        {
            return false;
        }
        if(addPersonSurnameInput.getText().trim().equals(""))
        {
            return false;
        }
        if(addPersonStreetNumber.getText().trim().equals(""))
        {
            return false;
        }
        if(addPersonStreetName.getText().trim().equals(""))
        {
            return false;
        }
        if(addPersonSuburb.getText().trim().equals(""))
        {
            return false;
        }
        if(addPersonPostcode.getText().trim().equals(""))
        {
            return false;
        }
        for(int i=0;i<currentFamilyTree.familyList.size();i++)
        {
            String temp = currentFamilyTree.familyList.get(i).getFirstName()+" "+currentFamilyTree.familyList.get(i).getLastName();
            String searchTerm = addPersonNameInput.getText().trim()+" "+addPersonSurnameInput.getText().trim();
            if(temp.equals(searchTerm))
            {
                 return false;
            }
        }
        
        return !addPersonLifeDescriptionInput.getText().trim().equals("");
    }
    //FUNCTION TO RUN VALIDATION CHECK FOR EMPTY INPUT WHEN UPDATING PERSON MEMBER
    public boolean updateMember_EmptyInputValidationCheck()
    {
        if(personNameTextField.getText().trim().equals(""))
        {
            return false;
        }
        if(personSurnameTextField.getText().trim().equals(""))
        {
            return false;
        }
        if(personMaidenNameTextField.getText().trim().equals(""))
        {
            return false;
        }
        if(streetNumberDescriptionTextField.getText().trim().equals(""))
        {
            return false;
        }
        if(streetNameDescriptionTextField.getText().trim().equals(""))
        {
            return false;
        }
        if(suburbDescriptionTextField.getText().trim().equals(""))
        {
            return false;
        }
        if(postcodeDescriptionTextField.getText().trim().equals(""))
        {
            return false;
        }
        return !personLifeDescriptionTextArea.getText().trim().equals("");
    }
    //CLEAR THE ADD NEW PERSON INPUT FOR NEXT USE
    public void clearAddNewPersonInput()
    {
        addPersonNameInput.setText("");
        addPersonSurnameInput.setText("");
        addPersonMaidenNameInput.setText("");
        addPersonGenderInput.getSelectionModel().selectFirst();
        addPersonStreetNumberInput.setText("");  
        addPersonStreetNameInput.setText("");
        addPersonSuburbInput.setText("");
        addPersonPostcodeInput.setText(""); 
        addPersonLifeDescriptionInput.setText(""); 
    }
}
