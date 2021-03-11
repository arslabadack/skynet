package arslabadack.ifsc.oop2.skynet.controllers;

import arslabadack.ifsc.oop2.skynet.AlertUtil;
import arslabadack.ifsc.oop2.skynet.App;
import arslabadack.ifsc.oop2.skynet.FXMLUtil;
import arslabadack.ifsc.oop2.skynet.db.UserDAO;
import arslabadack.ifsc.oop2.skynet.entities.User;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginController {
	@FXML
	private Button btnLogin;

	@FXML
	private Button btnRegisterScreen;

	@FXML
	private TextField txtUsername;

	@FXML
	private PasswordField txtPassword;

	@FXML
	private void logged() {
		String username = txtUsername.getText();
		String password = txtPassword.getText();

		if (username.isBlank()) {
			Alert alert = AlertUtil.error("ERROR", "ERROR", "Missing username", null);
			alert.showAndWait();
			return;
		}
		if (password.isBlank()) {
			Alert alert = AlertUtil.error("ERROR", "ERROR", "Missing password", null);
			alert.showAndWait();
			return;
		}
		
		User user = new UserDAO().get(username);
		if (user == null) {
			Alert alert = AlertUtil.error("ERROR", "ERROR", "Invalid username or password", null);
			alert.showAndWait();
			return;
		}
		if (!user.getPassword().contentEquals(password)) {
			Alert alert = AlertUtil.error("ERROR", "ERROR", "Invalid username or password", null);
			alert.showAndWait();
			return;
		}
		
		new UserDAO().persist(user);
		App.changeResizable();
		App.setRoot("main");
		MainController controller = FXMLUtil.getMainController();
	//	controller.updateUserInfo(user);

		App.changeResizable();
		App.setRoot("main");
	}

	@FXML
	private void register() {
		Stage stage = new Stage();
		stage.setScene(FXMLUtil.loadScene("register"));
		stage.setResizable(false);
		stage.show();
	}

	@FXML
	private void exit() {
		Platform.exit();
	}
}
