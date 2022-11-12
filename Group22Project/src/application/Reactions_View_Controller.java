package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class Reactions_View_Controller extends Project_View_Controller{
	public Stage applicationStage;
	//I plan to make some methods in reaction_view so I can get useful information 

    @FXML
    private Button test_button;

    @FXML
    private Label test_label;

    @FXML
    void test(ActionEvent event) {

    	test_label.setText("It works!");
    }

}