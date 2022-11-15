package application;

import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;


public class Project_View_Controller{
	public Stage applicationStage;
	
	//---------------------------------------------------------
	
	//ELEMENT VALUES 
    @FXML
    private TextField element_Input;
    
    @FXML
    private Label element_name;

    @FXML
    private Label element_symbol;

    @FXML
    private Label element_answer_1;

    @FXML
    private Label element_answer_2;

    @FXML
    private Label element_answer_3;

    @FXML
    private Label element_answer_4;
    
    @FXML
    private Button enter_element_input;
    
    @FXML
    private Label error_element_label;

    
    @FXML
    void entered_element_input(ActionEvent event) {
    	
    	Atom element_information = new Atom();
    	
    	if (element_information.Test(element_Input.getText()).equals("invalid element")) {
    		error_element_label.setText("Invalid Element");
    		
    		//clear inputs
    		element_symbol.setText("");
    		element_answer_1.setText("");
    		element_answer_2.setText("");
    		element_answer_3.setText("");
    		element_answer_4.setText("");
    		return;
    	}else {
    		error_element_label.setText("");
    	}

    	//Input should be valid beyond this
    	String element = element_Input.getText();
    	
    	//Atomic Name
    	if (element_information.getElement(element).equals("invalid element")){
    		
    		element = element.substring(0,1).toUpperCase() + element.substring(1).toLowerCase();
    		element_name.setText(element);
    		
    	}else {
    		String my_element = element_information.getElement(element);
    		my_element = my_element.substring(0,1).toUpperCase() + my_element.substring(1).toLowerCase();
    		element_name.setText(my_element);
    	}
    	
    	//Atomic Symbol
    	if (element_information.getSymbol(element).equals("invalid element")) {
    		
    		element = element.substring(0,1).toUpperCase() + element.substring(1).toLowerCase();
    		element_symbol.setText(element);
    		
    	}else {
    		element_symbol.setText(element_information.getSymbol(element));
    	}
    	
    	//Atomic Number
    	element_answer_1.setText(element_information.getAtomicNumber(element));
    	
    	//Atomic Weight
    	element_answer_2.setText(element_information.getAtomicWeight(element));
    	
    	//Category (Sample)
    	String category = element_information.getCategory(element);
    	category = category.substring(0,1).toUpperCase() + category.substring(1).toLowerCase();
    	element_answer_3.setText(category);
    	
    	//Density (Sample)
    	element_answer_4.setText(element_information.getDensity(element));
    }

	//---------------------------------------------------------
    
    //MOLECULE VALUES
    
    @FXML
    private TextField molecule_input;

    @FXML
    private Button enter_molecule_input;

    @FXML
    private Label molecule_answer_1;

    //Only molecule_answer_1 and molecule_NAME is used, answer 2 - 4 are empty
    @FXML
    private Label molecule_answer_2;

    @FXML
    private Label molecule_answer_3;

    @FXML
    private Label molecule_answer_4;
    
    @FXML
    private Label error_molecule_label;
    
    @FXML
    private Label molecule_NAME;
    
    @FXML
    void entered_molecule_input(ActionEvent event) {
    	
    	if (molecule_input.getText().equals("") || molecule_input.getText().isEmpty()) {
    		
    		error_molecule_label.setText("Invalid Molecule");
    		
    		//clear inputs
    		molecule_NAME.setText("");
    		molecule_answer_1.setText("");
    		return;
    	}else {
    		error_molecule_label.setText("");
    	}
    	
    	//prevents an error if input is only numbers
    	String test = molecule_input.getText().toLowerCase().replaceAll("[a-z]", "");
    	
    	String test2 = molecule_input.getText().toLowerCase().replaceAll("[0-9]", "");
    	
    	System.out.println(test);
    	if (test.length() >= 1 && test2.length() == 0) {
    		error_molecule_label.setText("Invalid Molecule");
    		molecule_NAME.setText("");
    		molecule_answer_1.setText("");
    		return;
    	}
    	
    	
    	Molecule molecule_information = new Molecule(molecule_input.getText());
    	
    	//check if molecule is valid
    	if (molecule_information.MoleculeCheck()) {
    		
    		//rounds the answer
    		molecule_answer_1.setText(String.format("%.4f", molecule_information.getMolecularWeight()));
   
    		molecule_NAME.setText(molecule_information.MoleculeName());

    	}else {
    		error_molecule_label.setText("Invalid Molecule");
    		molecule_NAME.setText("");
    		molecule_answer_1.setText("");
    		return;
    	}
    	
    }
    
    //---------------------------------------------------------

    //CHEMICAL EQUATION

    @FXML
    private TextField number_of_products;

    @FXML
    private TextField number_of_rectants;

    @FXML
    private Button enter_chemical_equation;
    
    @FXML
    private Label reaction_error_label;
    
    //This label should return whether the reaction inputed was balanced or not
    @FXML
    private Label reaction_balanced;
    
    @FXML
    void entered_chemical_equation(ActionEvent event) throws IOException{

    	String test1 = number_of_rectants.getText().toLowerCase().replaceAll("[0-9]", "");
    	String test2 = number_of_products.getText().toLowerCase().replaceAll("[0-9]", "");
    	
    	//check for null on reacts
    	if (number_of_rectants.getText().equals("") || number_of_rectants.getText().isEmpty()) {
    		reaction_error_label.setText("Add Numerical Inputs");
    		return;
    	}
    	
    	//check for null on products
    	if (number_of_products.getText().equals("") || number_of_products.getText().isEmpty()) {
    		reaction_error_label.setText("Add Numerical Inputs");
    		return;
    	}
    	
    	//ensures that there are numbers only in an input
    	if (test1.length() >= 1 || test2.length() >= 1) {
    		reaction_error_label.setText("Add Numerical Inputs");
    		return;
    	}
    	
    	//wipe the error message
    	reaction_error_label.setText("");

    	//I decided to make a new scene for the Reactions class since it appeared to be quite complicated and 
    	//also required formatting 
    	
    	//I used the video below, to get the code to move from one scene to the next
    	//https://www.youtube.com/watch?v=XCgcQTQCfJQ
    	
    	Reaction_View_Controller new_scene = new Reaction_View_Controller();
    	
    	Scene Reacton_Scene = new_scene.getChemicalEquation(get_amount());
    	
    	Stage scene = (Stage) ((Node) event.getSource()).getScene().getWindow();
    	
    	scene.setScene(Reacton_Scene);
    	scene.show();

    	
    }
    
    //This method will provide the text field amounts 
    protected ArrayList<Integer> get_amount(){
    	
    	ArrayList<Integer> amount = new ArrayList<Integer>();
    	
    	//Get the amount of each as an int. 
    	amount.add(Integer.parseInt(number_of_rectants.getText()));
    	amount.add(Integer.parseInt(number_of_products.getText()));
    	
    	return amount;
    }
    
    /*
    //This method will create an H box and return it
    protected HBox get_HBox_Container() {
    	
 	    TextField quantity = new TextField();
 	    
 	    TextField molecule = new TextField();
 	    
 	    HBox new_box = new HBox();
 	    new_box.getChildren().addAll(quantity,molecule);

    	return new_box;
    }
    
    //Enter a sign to get an HBox with that sign
    protected HBox get_Sign_Box(String sign) {
    	
 	    Label new_label = new Label();
 		new_label.setText(sign);
 		
 		HBox signBox = new HBox();
 		signBox.getChildren().add(new_label);
 		
 		return signBox;
    }
    
    //Makes an HBox with a label and text field
    protected HBox get_gram_container(String item, int number, boolean product) {
		
    	HBox my_HBox = new HBox();
    	
    	Label test = new Label();
    	if (product) {
        	test.setText(item + Integer.toString(number) + " ");
    	}else {
    		test.setText(item + Integer.toString(number));
    	}
    	
    	TextField gram = new TextField();
    	
    	my_HBox.getChildren().addAll(test,gram);
    	
    	return my_HBox;	
    }
    
    protected Scene getChemicalEquation () {
		
    	ArrayList<Integer> numProdReact = get_amount();
    	
    	int number_of_reactions = numProdReact.get(0);
    	int number_of_products = numProdReact.get(1);
    	
    	int reactions_added = 1;
    	int products_added = 1;
    	
    	//The main equation (for number of molecules and molecules)
    	ArrayList<HBox> chemical_equation = new ArrayList<HBox>();
    	
    	//For grams
    	ArrayList<HBox> gram_array = new ArrayList<HBox>();
    	
    	//expecting at least 1 reactions to begin
    	chemical_equation.add(get_HBox_Container());
    	
    	//Setting up reactions part of gram array
    	gram_array.add(get_Sign_Box("-------- Reaction Grams: --------"));
    	gram_array.add(get_gram_container("Reactant ", 1, false));
    	gram_array.add(get_Sign_Box(""));
    	
    	while (number_of_reactions > reactions_added) {
    		
    		chemical_equation.add(get_Sign_Box("+"));
    		chemical_equation.add(get_HBox_Container());
    		
    		gram_array.add(get_gram_container("Reactant ", reactions_added, false));
    		
    		//Added this for appearance reasons
    		if (!(reactions_added == number_of_reactions - 1)) {
    			gram_array.add(get_Sign_Box(""));
    		}
    		
    		reactions_added++;
    	}
    	chemical_equation.add(get_Sign_Box("-->"));
    	
    	//expecting at least 1 product to begin
    	chemical_equation.add(get_HBox_Container());
    	
    	//Setting up products part of gram array
    	gram_array.add(get_Sign_Box("-------- Product Grams: --------"));
    	gram_array.add(get_gram_container("Product ", 1, true));
    	gram_array.add(get_Sign_Box(""));
    	
    	while (number_of_products > products_added) {
    		chemical_equation.add(get_Sign_Box("+"));
    		chemical_equation.add(get_HBox_Container());
    		
    		gram_array.add(get_gram_container("Product ", products_added, true));
    		gram_array.add(get_Sign_Box(""));
    		
    		products_added++;	
    	}
    	
    	//V for # and molecule
    	VBox main_box = new VBox();
    	
    	//Added label
    	Label title = new Label();
    	title.setText("Please enter your values");
    	main_box.getChildren().add(title);
    	
    	main_box.getChildren().addAll(chemical_equation);
    	
     	Button enterButton = new Button();
 		enterButton.setText("enter");   	
    	main_box.getChildren().add(enterButton);
    	
    	//VBox for grams
    	VBox grams = new VBox();
    	grams.getChildren().addAll(gram_array);
    	
    	//overall H box
    	HBox overall_box = new HBox();
    	overall_box.getChildren().addAll(main_box,grams);
    	
    	//When enter button is pressed, I will need to make a method that will get all the text field information
    	Calculate_Reaction_TextFields test = new Calculate_Reaction_TextFields();
    	enterButton.setOnAction(doneEvent -> test.Textfield_Calculator(numProdReact, main_box,grams));
    	
    	//Thinking of moving all the scene stuff here and making this method void
     	Scene ReactionScene = new Scene(overall_box);
     	return ReactionScene;
    }
    */
}


