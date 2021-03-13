package arslabadack.ifsc.oop2.skynet;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

public class App extends Application {

	@Override
	public void start(Stage stage) {

		try {
			FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("login.fxml"));
			Scene scene = new Scene(fxmlLoader.load());
			stage.setScene(scene);
			stage.setResizable(false);
			stage.setTitle("Skynet");
			stage.show();
		} catch (IOException e) {
			Alert alert = AlertUtil.error("ERROR", "failed to load a component", "Failed to load login scene", e);
			alert.showAndWait();
		}
	}
}