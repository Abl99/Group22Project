package application;

import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

//Needs to be committed

public class ProjectController {
	public Stage applicationStage;
	
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
    private Button enter_chemical_equation;

    @FXML
    private Button enter_element_input;

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
    private TextField molecule_input;

    @FXML
    private TextField number_of_products;

    @FXML
    private TextField number_of_rectants;

    @FXML
    void entered_chemical_equation(ActionEvent event) {

    }

    @FXML
    void entered_element_input(ActionEvent event) {

    }

    @FXML
    void entered_molecule_input(ActionEvent event) {

    }
}


