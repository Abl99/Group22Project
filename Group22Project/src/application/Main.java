package application;
	
import java.io.FileInputStream;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Main extends Application {
	
	private String limiting_reagent = "N/A";
	private String theoretical_yield = "N/A";
	private String percent_yield = "N/A";
	
	private String balanced_or_not = "No Reaction Yet";
	
	@Override
	public void start(Stage primaryStage) {
		try {
			FXMLLoader loader = new FXMLLoader();
			AnchorPane root = loader.load(new FileInputStream("src/application/Project_View.fxml"));
			Project_View_Controller controller = (Project_View_Controller)loader.getController();
			
			controller.applicationStage = primaryStage;
			
			Scene scene = new Scene(root,912,460);
			primaryStage.setScene(scene);
			primaryStage.setTitle("Group 22 Project");
			
			controller.update_limiting_reagent(limiting_reagent);
			controller.update_theoretical_yield(theoretical_yield);
			controller.update_percent_yield(percent_yield);
			
			controller.update_balanaced(balanced_or_not);
			
			primaryStage.show();	
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	//Reaction Output Methods
	
	public void setBalanced (String balanced) {
		this.balanced_or_not = balanced;
	}
	
    protected void update_limiting_reagent(String limiting_reactant) {
    	this.limiting_reagent = limiting_reactant;
    }
    
    protected void update_theoretical_yield(String theoretical_yields) {
    	this.theoretical_yield = theoretical_yields;
    }
    
    protected void update_percent_yield(String percent_yields) {
    	this.percent_yield = percent_yields;
    }
}