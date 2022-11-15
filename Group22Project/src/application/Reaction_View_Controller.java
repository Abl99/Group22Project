package application;

import java.util.ArrayList;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class Reaction_View_Controller {
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
    
    protected Scene getChemicalEquation (ArrayList<Integer> get_amount) {
		
    	ArrayList<Integer> numProdReact = get_amount;
    	
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
}
