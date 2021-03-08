package arslabadack.ifsc.oop2.skynet;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class RegisterController {

	@FXML
	private Button btnBack;

	@FXML
	private void back() {
		App.setRoot("login");
	}

	@FXML
	private void exit() {
		Platform.exit();
	}

}
