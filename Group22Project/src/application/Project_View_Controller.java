package application;

import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;


public class Project_View_Controller {
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
    	
    	//Can adjust reaction view dimensions if I want it to show up smaller since there can be less panes
    	int default_width = 1161;
    	int default_height = 452;
    	
    	//The number of reactions and products
    	int number_of_reactions = Integer.parseInt(number_of_rectants.getText());
    	
    	String np = number_of_products.getText();
    	int number_of_products = Integer.parseInt(np);
    	
    	
    	
    	//I need to figure out how to hide panes that are yet to made in the reaction_view
    	
    	//I used the video below, to get the code to move to another scene
    	//https://www.youtube.com/watch?v=XCgcQTQCfJQ
    	
    	FXMLLoader loader = new FXMLLoader();
    	loader.setLocation(getClass().getResource("Reactions_View.fxml"));
    	Parent Reaction_Views = loader.load();
    	
    	Scene Reaction_Scene = new Scene(Reaction_Views);
    	
    	//controller access
    	Reactions_View_Controller reaction_controller = loader.getController();
    	
    	/*IMPORTANT
    	 * 
    	 * I WILL NEED TO CHANGE THE TEXTFIELD INTO A DROP DOWN BOX GOING 1 TO 5 FOR REACTIONS AND PRODUCTS
    	 * SINCE THI MODEL CAN'T SUPPORT IT IF IT GOES HIGHER THAN 5
    	 */
    	
    	//removes any unused panels (i.e. if user only wants 1 react pane, then only 1 will be present)
    	reaction_controller.remove_Panels(reaction_controller, number_of_reactions, number_of_products);
    	
    	//Gets information on stage
    	Stage scene = (Stage) ((Node) event.getSource()).getScene().getWindow();
    	
    	
    	scene.setScene(Reaction_Scene);
    	
    	//disables maximizing
    	scene.resizableProperty().setValue(false);

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
    
}


