package arslabadack.ifsc.oop2.skynet.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import arslabadack.ifsc.oop2.skynet.AlertUtil;
import arslabadack.ifsc.oop2.skynet.App;
import arslabadack.ifsc.oop2.skynet.entities.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class PostController implements Initializable {

	@FXML
	private Button btnSavePost;

	@FXML
	private Button btnDeletePost;
	
	@FXML
	private Button btnBack;

	@FXML
	private TextField txtPostId;

	@FXML
	private TextArea txtPostContent;

	@FXML
	private ListView<String> listPost;
	
	private User user;
	
	@FXML
	private void logout() {
		try {
			user = null;
			FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("login.fxml"));
			Scene scene = new Scene(fxmlLoader.load());
			Stage stage = (Stage) btnSavePost.getScene().getWindow();
			stage.setScene(scene);
			stage.setResizable(false);
			stage.show();
		} catch (IOException e) {
			Alert alert = AlertUtil.error("ERROR", "failed to load a component", "Failed to load login scene", e);
			alert.showAndWait();
		}
	}

	@FXML
	private void back() {
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("main.fxml"));
			Scene scene = new Scene(fxmlLoader.load());
			Stage stage = (Stage) btnSavePost.getScene().getWindow();
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

	public static void setUser(User user) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}


}
