package arslabadack.ifsc.oop2.skynet.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import arslabadack.ifsc.oop2.skynet.AlertUtil;
import arslabadack.ifsc.oop2.skynet.App;
import arslabadack.ifsc.oop2.skynet.db.UserDAO;
import arslabadack.ifsc.oop2.skynet.entities.User;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginController implements Initializable {
	@FXML
	private Button btnLogin;

	@FXML
	private Button btnRegisterScreen;

	@FXML
	private TextField txtUsername;

	@FXML
	private PasswordField txtPassword;
	
	private static User user;

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
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("main.fxml"));
			Scene scene = new Scene(fxmlLoader.load());
			Stage stage = (Stage) btnLogin.getScene().getWindow();
			stage.setScene(scene);
			stage.setResizable(true);
			stage.show();

			MainController controller = fxmlLoader.getController();
			controller.userInfo(user);
			MainController.setUser(user);

		} catch (IOException e) {
			Alert alert = AlertUtil.error("ERROR", "failed to load a component", "Failed to load scene", e);
			alert.showAndWait();
		}

	}

	@FXML
	private void register() {

		try {
			Stage stage = new Stage();
			FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("register.fxml"));
			Scene scene = new Scene(fxmlLoader.load());
			stage.setScene(scene);
			stage.setResizable(false);
			stage.setTitle("Skynet");
			stage.show();
		} catch (IOException e) {
			Alert alert = AlertUtil.error("ERROR", "failed to load a component", "Failed to load scene", e);
			alert.showAndWait();
		}
	}

	@FXML
	private void exit() {
		Platform.exit();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
	}
}
