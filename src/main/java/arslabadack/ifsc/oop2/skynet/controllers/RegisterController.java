package arslabadack.ifsc.oop2.skynet.controllers;

import arslabadack.ifsc.oop2.skynet.AlertUtil;
import arslabadack.ifsc.oop2.skynet.App;
import arslabadack.ifsc.oop2.skynet.db.UserDAO;
import arslabadack.ifsc.oop2.skynet.entities.User;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class RegisterController {

	@FXML
	private Button btnRegister;

	@FXML
	private TextField txtRegisterUsername;

	@FXML
	private TextField txtRegisterName;

	@FXML
	private TextField txtRegisterEmail;

	@FXML
	private PasswordField txtRegisterPassword;

	@FXML
	private PasswordField txtRegisterPasswordConfirmation;

	@FXML
	private TextField txtRegisterBirthdate;

	@FXML
	private TextField txtRegisterRelationship;

	@FXML
	private void back() {
		App.setRoot("login");
	}

	@FXML
	private void exit() {
		Platform.exit();
	}
	
	@FXML
	private void close() {
		Stage stage = (Stage) txtRegisterUsername.getScene().getWindow();
		stage.close();
	}

	@FXML
	private void newUser() {
		String username = txtRegisterUsername.getText();
		String name = txtRegisterName.getText();
		String email = txtRegisterEmail.getText();
		String password = txtRegisterPassword.getText();
		String birthdate = txtRegisterBirthdate.getText();
		String relationship = txtRegisterRelationship.getText();

		if (username.isBlank()) {
			Alert alert = AlertUtil.error("ERROR", "ERROR", "Missing username", null);
			alert.showAndWait();
			return;
		}
		if (name.isBlank()) {
			Alert alert = AlertUtil.error("ERROR", "ERROR", "Missing name", null);
			alert.showAndWait();
			return;
		}
		if (email.isBlank()) {
			Alert alert = AlertUtil.error("ERROR", "ERROR", "Missing e-mail", null);
			alert.showAndWait();
			return;
		}
		if (password.isBlank()) {
			Alert alert = AlertUtil.error("ERROR", "ERROR", "Missing password", null);
			alert.showAndWait();
			return;
		}
		if (birthdate.isBlank()) {
			Alert alert = AlertUtil.error("ERROR", "ERROR", "Missing birthdate", null);
			alert.showAndWait();
			return;
		}
		if (relationship.isBlank()) {
			Alert alert = AlertUtil.error("ERROR", "ERROR", "Missing relationship status", null);
			alert.showAndWait();
			return;
		}

		User u = new UserDAO().get(username);
		if (u != null) {
			Alert alert = AlertUtil.error("ERROR", "ERROR", "Username already exists", null);
			alert.showAndWait();
			return;
		}
		new UserDAO().persist(new User(username, name, email, password, birthdate, relationship));

		AlertUtil.info("DONE", "DONE", "You are registered in Skynet").show();
		
		close();
	}

}
