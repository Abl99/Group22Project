package application;
	
import java.io.FileInputStream;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			FXMLLoader loader = new FXMLLoader();
			AnchorPane root = loader.load(new FileInputStream("src/application/Project_View.fxml"));
			Project_View_Controller controller = (Project_View_Controller)loader.getController();
			controller.applicationStage = primaryStage;
			
			Scene scene = new Scene(root,912,450);
			primaryStage.setScene(scene);
			primaryStage.setTitle("Group 22 Project");
			primaryStage.show();	
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}