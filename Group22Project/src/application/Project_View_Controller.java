package application;

import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;


public class Project_View_Controller{
	public Stage applicationStage;
	
	private String balanced;
	
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
    		element_name.setText("");
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
    	
    	TextField_Validity_Check molecule_test = new TextField_Validity_Check();
    	//check if molecule is valid
    	if (molecule_test.molecule_check(molecule_input.getText())) {
    		
    		Molecule molecule_information = new Molecule(molecule_input.getText());
    		
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
    
    @FXML
    void entered_chemical_equation(ActionEvent event) throws IOException{
    	
    	boolean valid_rectants = true;
    	boolean valid_products = true;
    	
    	TextField_Validity_Check testing_for_input_errors = new TextField_Validity_Check();
    	
    	//check if input is null
    	valid_rectants = testing_for_input_errors.check_if_null(number_of_rectants);
    	valid_products = testing_for_input_errors.check_if_null(number_of_products);
    	
    	if (!valid_rectants || !valid_products) {
    		reaction_error_label.setText("Add Numerical Inputs");
    		return;
    	}
    	
    	//check if input is a number
    	valid_rectants = testing_for_input_errors.check_if_int(number_of_rectants.getText());
    	valid_products = testing_for_input_errors.check_if_int(number_of_products.getText());
    	
    	if (!valid_rectants || !valid_products) {
    		//not an integer
    		reaction_error_label.setText("Add Numerical Inputs");
    		return;
    	}
    	
    	//input should not be 0 on either side
    	if ((Integer.parseInt(number_of_rectants.getText()) == 0) || (Integer.parseInt(number_of_products.getText()) == 0)) {
    		reaction_error_label.setText("Cannot have 0");
    		return;
    	}
    	//beyond this, the input should be valid

    	//wipe the error message
    	reaction_error_label.setText("");
    	
    	
    	
    	//I used the video below, to get the code to move from one scene to the next
    	//https://www.youtube.com/watch?v=XCgcQTQCfJQ
    	
    	Stage scene = (Stage) ((Node) event.getSource()).getScene().getWindow();
    	
    	//I added the stage of this class to be carried over to Reaction_View_Controller;
    	Reaction_View_Controller new_scene = new Reaction_View_Controller();
    	
    	Scene Reacton_Scene = new_scene.getChemicalEquation(scene, get_amount());
    	
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
    
  //---------------------------------------------------------
    //Reaction OUTPUTS
    
    @FXML
    private Label limiting_reagent;
    
    @FXML
    private Label theoretical_yield;
    
    @FXML
    private Label percent_yield;
    
    @FXML
    private Label reaction_balanced;
    
    
    protected void update_limiting_reagent(String limiting_reactant) {
    	limiting_reagent.setText(limiting_reactant);
    }
    
    protected void update_theoretical_yield(String theoretical_yields) {
    	theoretical_yield.setText(theoretical_yields);
    }
    
    protected void update_percent_yield(String percent_yields) {
    	percent_yield.setText(percent_yields);
    }
    
    protected void update_balanaced(String balanced) {
    	reaction_balanced.setText(balanced);
    }
    
    //---------------------------------------------------------
    //Exit Program
    
    @FXML
    private Button exit_button;
    
    @FXML
    void exit_program(ActionEvent event) {
    	
    	applicationStage.close();
    	
    }
    
}


