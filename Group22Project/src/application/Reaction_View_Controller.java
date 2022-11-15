package application;

import java.util.ArrayList;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class Reaction_View_Controller {
	 //This method will create an H box and return it
    protected HBox get_HBox_Container() {	
 	    TextField quantity = new TextField();
 	    	quantity.setPrefSize(40.0, 25.0);
 	    TextField molecule = new TextField();
 	    	molecule.setPrefSize(80.0,25.0);
 	    HBox new_box = new HBox();
 	    	new_box.setPadding(new Insets(5,20,5,20));
 	    new_box.getChildren().addAll(quantity,molecule);
    	
 	    return new_box;
    }
    
    //Enter a sign to get an HBox with that sign
    protected HBox get_Sign_Box(String sign) {
 	    Label new_label = new Label();
 			new_label.setText(sign);
 		HBox signBox = new HBox();
	    	signBox.setPadding(new Insets(5,10,5,10));
 		signBox.getChildren().add(new_label);
 		
 		return signBox;
    }
    
    //Makes an HBox with a label and text field
    protected HBox get_gram_container(String item, int number, boolean product) {
    	HBox my_HBox = new HBox();
	    	my_HBox.setPadding(new Insets(10,10,10,10));
    	Label test = new Label();
    		test.setPadding(new Insets(0,5,0,0));
    	if (product) {
        	test.setText(item + Integer.toString(number) + " ");
    	}else {
    		test.setText(item + Integer.toString(number));
    	}
    	TextField gram = new TextField();
    		gram.setPrefSize(70.0, 25.0);	
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
    	chemical_equation.add(get_Sign_Box("Reaction 1:"));
    	chemical_equation.add(get_HBox_Container());
    	
    	//Setting up reactions part of gram array
    	gram_array.add(get_Sign_Box("-------- Reaction Grams: --------"));
    	gram_array.add(get_gram_container("Reactant ", 1, false));
    	
    	while (number_of_reactions > reactions_added) {
    		reactions_added++;
    		
    		chemical_equation.add(get_Sign_Box("+"));
    		chemical_equation.add(get_Sign_Box(String.format("Reaction %d:", reactions_added)));
    		chemical_equation.add(get_HBox_Container());
    		
    		gram_array.add(get_gram_container("Reactant ", (reactions_added), false));
    		
    	}
    	chemical_equation.add(get_Sign_Box("-->"));
    	
    	//expecting at least 1 product to begin
    	chemical_equation.add(get_Sign_Box("Product 1:"));
    	chemical_equation.add(get_HBox_Container());
    	
    	//Setting up products part of gram array
    	gram_array.add(get_Sign_Box("-------- Product Grams: --------"));
    	gram_array.add(get_gram_container("Product ", 1, true));
    	
    	while (number_of_products > products_added) {
    		chemical_equation.add(get_Sign_Box("+"));
    		chemical_equation.add(get_Sign_Box(String.format("Product %d:", products_added)));
    		chemical_equation.add(get_HBox_Container());
    		
    		gram_array.add(get_gram_container("Product ", (products_added+1), true));
    		
    		products_added++;	
    	}
    	
    	//V for # and molecule
    	VBox main_box = new VBox();
	    	main_box.setPadding(new Insets(20,20,20,20));
	    	main_box.setAlignment(Pos.CENTER);
	    	
    	//Added overall label
	    main_box.getChildren().add(get_Sign_Box("Please Enter Your Values"));
	    
	    //Add chemical equation to main_box
    	main_box.getChildren().addAll(chemical_equation);
    	
    	//Enter button
     	Button enterButton = new Button();
 			enterButton.setText("Enter"); 
    	main_box.getChildren().add(enterButton);
    	
    	//VBox for grams
    	VBox grams = new VBox();
	    	grams.setPadding(new Insets(20,20,20,20));
    	grams.getChildren().addAll(gram_array);
    	
    	//overall H box
    	HBox overall_box = new HBox();
    	overall_box.getChildren().addAll(main_box,grams);
    	
    	//Scroll main that encompasses all 
    	ScrollPane scroll = new ScrollPane();
    	
    	//This is to control the screen size when the amount of molecules is small (don't need a lot of 
    	//space for a chemical equation of 2)
    	ArrayList<Integer> scroll_pref = scrollpanepreferences(get_amount);
    	
    	//Please don't change the pref size of scroll box
    	scroll.setPrefSize(scroll_pref.get(0), scroll_pref.get(1));
    	scroll.setContent(overall_box);
    	
    	
    	//When enter button is pressed, I will need to make a method that will get all the text field information
    	Calculate_Reaction_TextFields test = new Calculate_Reaction_TextFields();
    	enterButton.setOnAction(doneEvent -> test.Textfield_Calculator(numProdReact, main_box,grams));
    	
    	//Thinking of moving all the scene stuff here and making this method void
     	//Scene ReactionScene = new Scene(overall_box);
    	Scene ReactionScene = new Scene(scroll);
     	return ReactionScene;
    }
    
    //This class sets the size of the reaction view
    //It decides this by using the amount of reactants and products in the chemical equation and will shorten the screen
    //if there would be too much empty space
    
    protected ArrayList<Integer> scrollpanepreferences (ArrayList<Integer> get_amount) {
    	
    	ArrayList<Integer> scroll_pref = new ArrayList<Integer>();
    	
    	//The width will stay the same, only the length changes when the number of molecules
    	//in the chemical equation is increased
		scroll_pref.add(445);
    	
    	//smallest chemical equation
    	if ((get_amount.get(0) + get_amount.get(1)) == 2) {
    		scroll_pref.add(250);
    		
    	}else if((get_amount.get(0) + get_amount.get(1)) == 3 ){
    		scroll_pref.add(350);
    		
    	}else if((get_amount.get(0) + get_amount.get(1)) == 4 ){
    		scroll_pref.add(450);
    		
    	}else if((get_amount.get(0) + get_amount.get(1)) == 5 ){
    		scroll_pref.add(550);
    		
    	}else if((get_amount.get(0) + get_amount.get(1)) == 6 ){
    		scroll_pref.add(610);
    	
    	//largest size before scroll wheel will need to be used
    	}else if ((get_amount.get(0) + get_amount.get(1)) >= 7) {
    		scroll_pref.add(700);
    	}

    	return scroll_pref;

    }
 
}
