package application;

import java.util.ArrayList;


import javafx.scene.Node;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import javafx.scene.layout.HBox;

import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Calculate_Reaction_TextFields extends TextField_Validity_Check{
	//TextField_Validity_Check extended reaction so almost every method written so far is available 
	
	private int gram_blank_boxes = 0;
	
	//private int gram_invalid_input_errors = 0;
	
	private int molecule_related_errors = 0;
	
	//Takes all the Hboxes from the Node Array, and makes a H box Array list from them
	private ArrayList<HBox> Node_to_Hbox_Array(ArrayList<Node> node_array) {
		
		ArrayList<HBox> Hboxes = new ArrayList<HBox>();
		
		for (Node x : node_array) {
		    
	    	try {
	    		HBox y = (HBox) x;
	    		Hboxes.add(y);
	    	}catch (ClassCastException e) {
	    		continue;
	    	}
	    }
		return Hboxes;
	}
	
	//Takes all the text fields from an H box Array and makes a Text box Array list from them
	private ArrayList<TextField> Hbox_to_TextField_Array (ArrayList<HBox> Hboxes){
		
		ArrayList<TextField> my_textfields = new ArrayList<TextField>();
		
	    for (HBox x : Hboxes) {
	    	
	    	for (int y = 0; y < x.getChildren().size();y++) {
	    		
	    		try {
	    			my_textfields.add((TextField) x.getChildren().get(y));
		    	}catch (ClassCastException e) {
		    		continue;
		    	}
	    	}
	    }
	    
		return my_textfields;
	}
	
	//Main method
	public void Textfield_Calculator(Stage reaction_stage, ArrayList<Integer> amounts, VBox main_box, VBox grams, Label errorLabel){
		
		//The Stage scene, is the stage of the project
		
		boolean valid_input = true;
		
		//will track the errors the user made 
		ArrayList<String> error_list = new ArrayList<String>();
		
		//-------------------------  MOLECULES ------------------------------- 
		//ArrayList for all items in the main_box (the box where number of and molecules are)
	    ArrayList<Node> main_box_array = new ArrayList<Node>(main_box.getChildren());
	   
	    //I need to get to the H boxes then get the TextFields from them
	    ArrayList<HBox> Hboxes = Node_to_Hbox_Array(main_box_array);
	    
	    //Now I want to get the text fields from the H boxes
	    //System.out.println(Hboxes.get(0).getChildren());
	    ArrayList<TextField> my_textfields = Hbox_to_TextField_Array(Hboxes);

	    //Now I have all the text fields that I use
	    //Now I make a String Array list to hold all of the user text field inputs
	    ArrayList<String> user_inputs = new ArrayList<String>();
	    
	    for (TextField x : my_textfields) {
	    	
	    	//From TextField_Validity_Check, it will return false if a text field is null and that text field will not be recorded
	    	valid_input = check_if_null(x);
	    	
	    	if (valid_input) {
	    		user_inputs.add(x.getText());
	    	}else {
	    		user_inputs.add("null value");
	    		error_list.add("1x Empty TextField in Molecules Section");
	    		molecule_related_errors ++;
	    	}
	    }
	    //There should be no null values in ArrayList<String> user_input 
	    
	    //I need to sort out the string array list user_inputs into the following four array lists
	    
	    ArrayList<Integer> reaction_amount_molecules = new ArrayList<Integer>();
	    ArrayList<Integer> product_amount_molecules = new ArrayList<Integer>();
	    
	    ArrayList<String> reaction_molecules = new ArrayList<String>();
	    ArrayList<String> product_molecules = new ArrayList<String>();
	    
	    //for reaction_amount_molecules
	    for (int x = 0; x < amounts.get(0)*2; x += 2) {
	    	
	    	//From TextField_Validity_Check
	    	valid_input = check_if_int(user_inputs.get(x));
	    	
	    	if (valid_input) {
	    		reaction_amount_molecules.add(Integer.parseInt(user_inputs.get(x)));
	    	}else {
	    		
	    		//avoid repeat errors
	    		if (!user_inputs.get(x).equals("null value")) {
	    			error_list.add("1x Invalid Reaction Amount");
	    			molecule_related_errors ++;
	    		}
	    	}
	    }
	    
	    //for reaction_molecules
	    for (int x = 1; x < amounts.get(0)*2; x += 2) {
	    	
	    	
	    	//reaction_molecules.add(user_inputs.get(x));
	    	
	    	//From TextField_Validity_Check
	    	valid_input = molecule_check(user_inputs.get(x));
	 
	    	if (valid_input) {
	    		reaction_molecules.add(user_inputs.get(x));
	    	}else {
	    		
	    		//avoid repeat errors
	    		if (!user_inputs.get(x).equals("null value")) {
	    			error_list.add("1x Invalid Reaction Molecule");
	    			molecule_related_errors ++;
	    		}
	    	}
	    }
	    
	    //for product_amount_molecules
	    for (int x = 0; x < amounts.get(1)*2; x += 2) {
	    	
	    	//From TextField_Validity_Check
	    	valid_input = check_if_int(user_inputs.get(x + amounts.get(0)*2));
	    	
	    	if (valid_input) {
	    		product_amount_molecules.add(Integer.parseInt(user_inputs.get(x + amounts.get(0)*2)));
	    	}else {
	    		
	    		//avoid repeat errors
	    		if (!user_inputs.get(x + amounts.get(0)*2).equals("null value")) {
	    			error_list.add("1x Invalid Product Amount");
	    			molecule_related_errors ++;
	    		}
	    	}
	    }
	    
	    //for product_molecules
	    for (int x = 1; x < amounts.get(1)*2; x += 2) {
	    	
	    	//product_molecules.add(user_inputs.get(x + amounts.get(0)*2));
	    	

	    	//From TextField_Validity_Check
	    	valid_input = molecule_check(user_inputs.get(x + amounts.get(0)*2));
	    	
	    	if (valid_input) {
	    		product_molecules.add(user_inputs.get(x + amounts.get(0)*2));
	    	}else {
	    		
	    		//avoid repeat errors
	    		if (!user_inputs.get(x + amounts.get(0)*2).equals("null value")) {
	    			error_list.add("1x Invalid Product Molecule");
	    			molecule_related_errors ++;
	    		}
	    	}
	    }
	    
	   //---------------------------- GRAMS ---------------------------------- 
	    //ArrayList for all items in grams 
	    ArrayList<Node> grams_array = new ArrayList<Node>(grams.getChildren());

	    //Need to get the Hboxes from the Node ArrayList
	    ArrayList<HBox> gram_Hboxes = Node_to_Hbox_Array(grams_array);
	    
	    //Now I want to get the text fields from the H boxes
	    ArrayList<TextField> gram_textfields = Hbox_to_TextField_Array(gram_Hboxes);

	    //Now textfields to individual strings
	    ArrayList<String> gram_inputs = new ArrayList<String>();
	    
	    for (TextField x : gram_textfields) {
	    	
	    	valid_input = check_if_null(x);
	    	
	    	if (valid_input) {
	    		gram_inputs.add(x.getText());
	    	}else {
	    		gram_inputs.add("null value");
	    		error_list.add("1x Empty TextField in Gram Section");
	    		gram_blank_boxes ++;
	    	}
	    	
	    }
	    
	    //I need to separate the gram reactants from the gram products as a double
	    ArrayList<Double> gram_reactants = new ArrayList<Double>();
	    ArrayList<Double> gram_products = new ArrayList<Double>();
	    
		for (int x = 0; x < amounts.get(0); x++) {
			
			//From TextField_Validity_Check
			valid_input = check_if_double(gram_inputs.get(x));
			
			if (valid_input) {
				gram_reactants.add(Double.parseDouble(gram_inputs.get(x)));	
			}else {
				
				//prevent repeat errors
				if(!gram_inputs.get(x).equals("null value")) {
					error_list.add("1x Invalid Reactant Gram Amount");
					//gram_invalid_input_errors ++;
				}
			}
		}
		    
		for (int x = 0; x < amounts.get(1); x++) {
			
			//From TextField_Validity_Check
			valid_input = check_if_double(gram_inputs.get(x + amounts.get(0)));
			
			if (valid_input) {
				gram_products.add(Double.parseDouble(gram_inputs.get(x + amounts.get(0))));
			}else {
				
				//prevent repeat errors
				if (!gram_inputs.get(x + amounts.get(0)).equals("null value")) {
					error_list.add("1x Invalid Product Gram Amount");	
					//gram_invalid_input_errors ++;
				}
			}
		}
	    
	  //---------------------------- ERRORS ---------------------------------- 
		
	  //THERE ARE NO ERRORS
	  if (error_list.size() == 0) {
		  
		  //close the reaction window
		  reaction_stage.close();
		  
		  //prepare to make a new project_view window with only 1 label changed (balanced)
		  Stage primaryStage = new Stage();
		  Main test = new Main();
		  
		  //Check if balanced or not using Reactions class
		  Reaction test_the_chemical_equation = new Reaction(
				  reaction_amount_molecules,reaction_molecules,
				  product_amount_molecules,product_molecules,
				  gram_reactants,gram_products);
		  
		  //----------------------- REACTION STRING -----------------------------------
		  test.setReaction(test_the_chemical_equation.toString());
		  
		  
		  
		  //----------------------- BALANCED -----------------------------------
		  //test_the_chemical_equation contains every relevant array list in the proper order;
		  boolean balanced_or_not = test_the_chemical_equation.balancedReaction();
		  
		  if (balanced_or_not) {
			  test.setBalanced("Balanced!");
		  }else {
			  test.setBalanced("Not Balanced");
		  }
		//----------------------- LIMITING REAGENT -----------------------------------
		  
		  String limiting_reagents = "";
		  limiting_reagents = test_the_chemical_equation.getLimitingReagent().get(0);
		  test.update_limiting_reagent(limiting_reagents);
		  
		  
		//----------------------- THEORETICAL YIELD -----------------------------------
		  
		  String theoretical_yield = "";
		  
		  ArrayList<String> theorYield = test_the_chemical_equation.theoreticalYield();
		  for (int index = 0; index < theorYield.size(); index = index +2) {
			  theoretical_yield = theoretical_yield + String.format("%.2f g of %s \n",Double.parseDouble(theorYield.get(index+1)),theorYield.get(index));
		  }
		  
		  test.update_theoretical_yield(theoretical_yield);
		  
		  
		//----------------------- PERCENT YIELD -----------------------------------
		  
		  String percent_yield = "";
		  ArrayList<String> percYield = test_the_chemical_equation.yieldPercent();
		  for (int index = 0; index < percYield.size(); index = index +2) {
			  percent_yield = percent_yield + String.format("%.2f %s of %s \n",Double.parseDouble(percYield.get(index+1)),"%",percYield.get(index));
		  }
		  test.update_percent_yield(percent_yield);
		  
		  test.start(primaryStage);
	
		  
	  //This else if is where the user has left the gram inputs empty
	  }else if (molecule_related_errors == 0 && gram_blank_boxes == (amounts.get(0) + amounts.get(1)))  {
		  
		  //close the reaction window
		  reaction_stage.close();
		  
		  //prepare to make a new project_view window with only 1 label changed (balanced)
		  Stage primaryStage = new Stage();
		  Main test = new Main();
		  
		  //Check if balanced or not using Reactions class
		  Reaction test_the_chemical_equation = new Reaction(
				  reaction_amount_molecules,reaction_molecules,
				  product_amount_molecules,product_molecules,
				  gram_reactants,gram_products);
		  
		  //----------------------- REACTION STRING -----------------------------------
		  test.setReaction(test_the_chemical_equation.toString());
		  
		  
		  //----------------------- BALANCED -----------------------------------
		  //test_the_chemical_equation contains every relevant array list in the proper order;
		  boolean balanced_or_not = test_the_chemical_equation.balancedReaction();
		  
		  if (balanced_or_not) {
			  test.setBalanced("Balanced!");
		  }else {
			  test.setBalanced("Not Balanced");
		  }
		  
		  test.start(primaryStage);
		  
	
	  //THERE IS AT LEAST 1 ERROR
	  }else {
		  
		  //Count the errors
		  ArrayList<String> accumulated_errors = new ArrayList<String>();
		  
		  //Molecule possible errors
		  int null_molecule_textfield = 0;
		  int invalid_reaction_amount = 0;
		  int invalid_product_amount = 0;
		  
		  //Gram possible errors
		  int null_gram_textfield = 0;
		  int invalid_reactant_gram_amount = 0;
		  int invalid_product_gram_amount = 0;
		  
		  //All these if statements are just for counting errors so that user gets a cleaner error message
		  for (String x : error_list) {
			  
			  if (x.equals("1x Empty TextField in Molecules Section")) {
				  null_molecule_textfield++;
				  
			  }else if (x.equals("1x Invalid Reaction Amount")) {
				  invalid_reaction_amount++;
				  
			  }else if (x.equals("1x Invalid Product Molecule")) {
				  invalid_product_amount++;
				  
			  }else if (x.equals("1x Empty TextField in Gram Section")) {
				  null_gram_textfield++;
				  
			  }else if (x.equals("1x Invalid Reactant Gram Amount")) {
				  invalid_reactant_gram_amount++;
				  
			  }else if (x.equals("1x Invalid Product Gram Amount")) {
				  invalid_product_gram_amount++;
			  } 
		  }
		  
		  if (!(null_molecule_textfield == 0)){
			  accumulated_errors.add(String.format("%dx Empty TextField in Molecules Section", null_molecule_textfield));
		  }
		  
		  if (!(invalid_reaction_amount == 0)){
			  accumulated_errors.add(String.format("%dx Invalid Reaction Amount", invalid_reaction_amount));
		  }
		  
		  if (!(invalid_product_amount == 0)){
			  accumulated_errors.add(String.format("%dx Invalid Product Molecule", invalid_product_amount));
		  }
		  
		  if (!(null_gram_textfield == 0)){
			  accumulated_errors.add(String.format("%dx Empty TextField in Gram Section", null_gram_textfield));
		  }
		  
		  if (!(invalid_reactant_gram_amount == 0)){
			  accumulated_errors.add(String.format("%dx Invalid Reactant Gram Amount", invalid_reactant_gram_amount));
		  }
		  
		  if (!(invalid_product_gram_amount == 0)){
			  accumulated_errors.add(String.format("%dx Invalid Product Gram Amount", invalid_product_gram_amount));
		  }
		  
		  //Prints the number of each error (i.e. so the user can make the proper adjustment)
		  
		  errorLabel.setText("Error(s): " + accumulated_errors.toString());
		  
		  //reset
		  gram_blank_boxes = 0;
		  //gram_invalid_input_errors = 0;
		  molecule_related_errors = 0;
		  
	  }  
	}	
}
