package application;

import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;


public class ProjectController {
	public Stage applicationStage;
	
	//---------------------------------------------------------
	
	//ELEMENT VALUES 
    @FXML
    private TextField element_Input;

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
    private Label element_NAME;

    
    @FXML
    void entered_element_input(ActionEvent event) {
    	
    	Valid_Atoms element_information = new Valid_Atoms();
    	
    	if (element_information.Test(element_Input.getText()).equals("invalid element")) {
    		error_element_label.setText("Invalid Element");
    		
    		//clear inputs
    		element_NAME.setText("");
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
    	
    	//Atomic Symbol
    	if (element_information.getSymbol(element).equals("invalid element")) {
    		
    		element = element.substring(0,1).toUpperCase() + element.substring(1).toLowerCase();
    		element_NAME.setText(element);
    		
    	}else {
    		element_NAME.setText(element_information.getSymbol(element));
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

    	Valid_Molecules molecule_information = new Valid_Molecules(molecule_input.getText());
    	
    	//Molecule Name
    	molecule_NAME.setText(molecule_information.getMoleculeName());
    	
    	//Atomic Weight
    	molecule_answer_2.setText(molecule_information.getMoleculeWeight());
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
    void entered_chemical_equation(ActionEvent event) {

    }


}


