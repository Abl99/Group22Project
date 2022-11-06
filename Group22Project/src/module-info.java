module Group22Project {
	requires javafx.controls;
	requires javafx.fxml;
	requires javafx.graphics;
	requires java.json;
	
	opens application to javafx.graphics, javafx.fxml;
}
