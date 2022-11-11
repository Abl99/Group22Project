package application;

import javafx.stage.Stage;

import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;


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
    	
    	Atom element_information = new Atom();
    	
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
    	
    	Molecule molecule_information = new Molecule(molecule_input.getText());
    	
    	//check if molecule is valid
    	if (molecule_information.MoleculeCheck()) {
    		molecule_answer_1.setText(Double.toString(molecule_information.getMolecularWeight()));
    		
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
    void entered_chemical_equation(ActionEvent event) {

    	
    	
    	
    	/*
    	if (optionalCompletedChoiceBox.getValue() != null) {
        	//System.out.println("Quiz grade clicked");
        	Scene mainScene1 = applicationStage.getScene();
        	
        	int numberOfQuizzes = optionalCompletedChoiceBox.getValue();
        	int rowsCreated = 0;
        	
        	VBox quizGradeContainer = new VBox();
        	Label this_is_optional = new Label("This is Optional Quiz Grades (Out of 10)");
        	quizGradeContainer.getChildren().add(this_is_optional);
        	
        	ArrayList<TextField> optional_quiz_textfield = new ArrayList<TextField>();
        	while (rowsCreated < numberOfQuizzes) {
        		
            	HBox rowContainer = new HBox();
            	Label optional_quiz_label = new Label("Quiz grade");
            	TextField optional_quiz_textfield1 = new TextField();
            	optional_quiz_textfield.add(optional_quiz_textfield1);
            	
            	rowContainer.getChildren().addAll(optional_quiz_label,optional_quiz_textfield1);
            	rowsCreated++;
            	
            	quizGradeContainer.getChildren().add(rowContainer);
        	}
        	Label optional_error_label = new Label("");
        	
        	Button doneButton = new Button("Done");
        	doneButton.setOnAction(doneEvent -> calculateAverageQuizGrade(mainScene1, optional_quiz_textfield,optional_error_label));
        	quizGradeContainer.getChildren().addAll(optional_error_label,doneButton);
        	
        	Scene optional_quiz = new Scene(quizGradeContainer);
        	applicationStage.setScene(optional_quiz);
    	}else {
    		average_optional_quiz_grade.setText("No Quiz Input (Grade is 0)");
    		average_quiz_grade1 = 0;
    	}
    	 */
    }
}


