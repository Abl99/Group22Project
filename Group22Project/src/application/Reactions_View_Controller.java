package application;

import java.io.IOException;
import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.scene.Node;

public class Reactions_View_Controller extends Project_View_Controller{
	public Stage applicationStage;
	//I plan to make some methods in reaction_view so I can get useful information 

	@FXML
    private Button enter_button;

    //Reaction Section
    
    @FXML
    private AnchorPane reaction_anchor_1;

    @FXML
    private AnchorPane reaction_anchor_2;

    @FXML
    private AnchorPane reaction_anchor_3;

    @FXML
    private AnchorPane reaction_anchor_4;

    @FXML
    private AnchorPane reaction_anchor_5;
    
    

    
    
    //Product Section
    
    @FXML
    private AnchorPane product_anchor_1;

    @FXML
    private AnchorPane product_anchor_2;

    @FXML
    private AnchorPane product_anchor_3;

    @FXML
    private AnchorPane product_anchor_4;

    @FXML
    private AnchorPane product_anchor_5;
    
    
    @FXML
    void enter_chemical_equation(ActionEvent event) throws IOException {
    
    	
    	//need to change the scene back to Project_View
    	//Used https://www.youtube.com/watch?v=hcM-R-YOKkQ&t=125s for the code to do this
    	Parent root = FXMLLoader.load(getClass().getResource("Project_View.fxml"));
    	Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    	Scene scene = new Scene(root);
    	stage.setScene(scene);
    	stage.show();
    	
    
    }
    
    
    //Need to make a class where I can get anchor panes for reactions
    protected ArrayList<AnchorPane> get_Reaction_Pane(){
    	
    	ArrayList<AnchorPane> test = new ArrayList<AnchorPane>();
		test.add(reaction_anchor_1);
		test.add(reaction_anchor_2);
		test.add(reaction_anchor_3);
		test.add(reaction_anchor_4);
		test.add(reaction_anchor_5);
		
    	return test;	
    }
    
    //Need to make a class where I can get anchor panes for reactions
    protected ArrayList<AnchorPane> get_Product_Pane(){
    	
    	ArrayList<AnchorPane> test = new ArrayList<AnchorPane>();
		test.add(product_anchor_1);
		test.add(product_anchor_2);
		test.add(product_anchor_3);
		test.add(product_anchor_4);
		test.add(product_anchor_5);
		
    	return test;	
    }
    
	//removes unused panels
    protected void remove_Panels(Reactions_View_Controller controller, int number_of_reactants, int number_of_products) {
	
    	ArrayList<AnchorPane> testing = controller.get_Reaction_Pane();
    	
    	//How many react anchor panes will be present
    	for (int x = number_of_reactants; x < testing.size();x++) {
    		controller.hide_pane(testing.get(x));
    	}
    	
    	ArrayList<AnchorPane> testing2 = controller.get_Product_Pane();
    	
    	//How many product anchor panes will be present
    	for (int x = number_of_products; x < testing2.size();x++) {
    		controller.hide_pane(testing2.get(x));
    	}
    	
	}
	
    //Used this link for the code on how to hide anchor panes
    //http://www.java2s.com/example/java/javafx/hide-javafx-anchor.html
    
    //turns an anchor pane invisible 
    protected void hide_pane(AnchorPane x) {
    	x.setVisible(false);
    	x.setManaged(false);
    	x.setMinWidth(0);
    	x.setMaxWidth(0);
    }

}