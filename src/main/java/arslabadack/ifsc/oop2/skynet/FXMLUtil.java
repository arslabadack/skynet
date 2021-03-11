package arslabadack.ifsc.oop2.skynet;

import java.io.IOException;

import arslabadack.ifsc.oop2.skynet.controllers.MainController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;

public class FXMLUtil {
	private static MainController mainController = null;

	public static Scene loadScene(String fxml) {
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
			Scene scene = new Scene(fxmlLoader.load());
			if (fxmlLoader.getController() instanceof MainController)
				mainController = fxmlLoader.getController();
			else
				mainController = null;
			return scene;
		} catch (IOException eIO) {
			Alert alert = AlertUtil.error("ERROR", "failed to load a component",
					"Failed to load a scene" + fxml, eIO);
			alert.showAndWait();
			return null;
		} catch (IllegalStateException eIllegalState) {
			Alert alert = AlertUtil.error("Erro", "Erro - Unknow file",
					"Failed to load a scene" + fxml, eIllegalState);
			alert.showAndWait();
			return null;
		}
	}

	public static MainController getMainController() {
		return mainController;
	}

}
