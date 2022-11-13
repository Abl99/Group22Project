	package application;
	
	import java.util.ArrayList;
	
	import javafx.event.ActionEvent;
	import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
	import javafx.scene.control.Label;
	import javafx.scene.control.TextField;
	import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
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
	    
	    protected Scene getReactionScene () {
	 	   //TODO edit the visual parameters of every item to make it look nicer
	     	// collect the numbers related to the reaction
	     	ArrayList<Integer> numProdReact = get_amount();
	 	    int numReact = numProdReact.get(0);
	 	    int numReactAdded = 1;
	 	    int numProd = numProdReact.get(1);
	 	    int numProdAdded = 1;
	 	    
	 	    // make the outermost container
	 	    VBox outerBox = new VBox();
	 	    
	 	    // make a title/instructional label and add it to the outer box
	 	    Label title = new Label();
	 	    	title.setText("<insert title>");
	 	    outerBox.getChildren().add(title);
	 		
	 		//make a label and box for the '-->'
	 	    Label arrow = new Label();
	     		arrow.setText("-->");
	     	HBox arrowBox = new HBox();
	     	arrowBox.getChildren().add(arrow);
	 	    
	     	//add all the components of the chemical equation to an array in the right order
	     	ArrayList<HBox> reactionScheme = new ArrayList<HBox>();
	 		
	 	    // make the secondary container that holds the text fields
	 	    TextField coeff = new TextField();
	 	    TextField molecule = new TextField();
	 	    HBox secondary = new HBox();
	 	    secondary.getChildren().addAll(coeff,molecule);
	     	
	     	reactionScheme.add(secondary);
	     	while (numReactAdded < numReact) {
	     		
	     	    // make a label and box for the '+'
	     	    Label plus = new Label();
	     	    	plus.setText("+");
	     		HBox plusBox = new HBox();
	     		plusBox.getChildren().add(plus);
	     		
	     		reactionScheme.add(plusBox);
	     		
	 		    // make the secondary container that holds the text fields
	 		    TextField coeff2 = new TextField();
	 		    TextField molecule2 = new TextField();
	 		    HBox secondary2 = new HBox();
	 		    secondary2.getChildren().addAll(coeff2,molecule2);
	     		
	     		reactionScheme.add(secondary2);
	     		numReactAdded ++;
	     	}
	 		reactionScheme.add(arrowBox);
	 		
	 	    // make the secondary container that holds the text fields
	 	    TextField coeff3 = new TextField();
	 	    TextField molecule3 = new TextField();
	 	    HBox secondary3 = new HBox();
	 	    secondary3.getChildren().addAll(coeff3,molecule3);
	 		
	 		reactionScheme.add(secondary3);
	     	while (numProdAdded < numProd) {
	     		
	     	    // make a label and box for the '+'
	     	    Label plus2 = new Label();
	     	    	plus2.setText("+");
	     		HBox plusBox2 = new HBox();
	     		plusBox2.getChildren().add(plus2);
	     		
	     		reactionScheme.add(plusBox2);
	     		
	 		    // make the secondary container that holds the text fields
	 		    TextField coeff4 = new TextField();
	 		    TextField molecule4 = new TextField();
	 		    HBox secondary4 = new HBox();
	 		    secondary4.getChildren().addAll(coeff4,molecule4);
	     		
	     		reactionScheme.add(secondary4);
	     		numProdAdded ++;
	     	}
	     	
	     	// add the reaction components from the array to primary HBoxes and add these HBoxes to the outer box
	     	int itemsAdded = 0;
	     	while (itemsAdded < reactionScheme.size()) {
	 	    	int count = 0;
	 	    	HBox primary = new HBox();
	     		// TODO check if more than 5 items fit in a primary box nicely
	 	    	while (count < 5 && itemsAdded < reactionScheme.size()) {
	     			primary.getChildren().add(reactionScheme.get(itemsAdded));
	     			itemsAdded ++;
	     			count ++;
	     		}
	 	    	outerBox.getChildren().add(primary);
	     	}
	     	
	     	// make and add the enter/return button
	     	Button enterButton = new Button();
	     		enterButton.setText("enter");
	     	outerBox.getChildren().add(enterButton);
	     	
	     	Scene ReactionScene = new Scene(outerBox);
	     	applicationStage.setScene(ReactionScene);
	     	return ReactionScene;

	     }
	  
	}