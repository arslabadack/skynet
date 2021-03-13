package arslabadack.ifsc.oop2.skynet.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import arslabadack.ifsc.oop2.skynet.AlertUtil;
import arslabadack.ifsc.oop2.skynet.App;
import arslabadack.ifsc.oop2.skynet.db.PostDAO;
import arslabadack.ifsc.oop2.skynet.entities.Post;
import arslabadack.ifsc.oop2.skynet.entities.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class MainController implements Initializable {

	@FXML
	private Button btnProfile;

	@FXML
	private Button btnMarketplace;

	@FXML
	private Button btnEvents;

	@FXML
	private Button btnPost;

	@FXML
	private TextField txtNewPost;

	@FXML
	private ListView<String> listPosts;

	@FXML
	private ListView<String> listBio;

	@FXML
	private Label lblLoggedUser;

	@FXML
	private Label lblFullName;

	@FXML
	private Label lblEmail;

	@FXML
	private Label lblBirthdate;

	@FXML
	private Label lblRelationship;
	
	private static User user;

	public static void setUser(User u) {
		user = u;
	}

	@FXML
	private void logout() {
		try {
			user = null;
			FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("login.fxml"));
			Scene scene = new Scene(fxmlLoader.load());
			Stage stage = (Stage) btnMarketplace.getScene().getWindow();
			stage.setScene(scene);
			stage.setResizable(false);
			stage.show();
		} catch (IOException e) {
			Alert alert = AlertUtil.error("ERROR", "failed to load a component", "Failed to load login scene", e);
			alert.showAndWait();
		}
	}

	@FXML
	private void marketplace() {
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("marketplace.fxml"));
			Scene scene = new Scene(fxmlLoader.load());
			Stage stage = (Stage) btnMarketplace.getScene().getWindow();
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
	private void events() {
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("events.fxml"));
			Scene scene = new Scene(fxmlLoader.load());
			Stage stage = (Stage) btnMarketplace.getScene().getWindow();
			stage.setScene(scene);
			stage.setResizable(true);
			stage.show();

			MainController controller = fxmlLoader.getController();
			controller.userInfo(user);
			EventsController.setUser(user);

		} catch (IOException e) {
			Alert alert = AlertUtil.error("ERROR", "failed to load a component", "Failed to load scene", e);
			alert.showAndWait();
		}
	}

	@FXML
	private void post() {
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("post.fxml"));
			Scene scene = new Scene(fxmlLoader.load());
			Stage stage = (Stage) btnMarketplace.getScene().getWindow();
			stage.setScene(scene);
			stage.setResizable(true);
			stage.show();

			MainController controller = fxmlLoader.getController();
			controller.userInfo(user);
			PostController.setUser(user);

		} catch (IOException e) {
			Alert alert = AlertUtil.error("ERROR", "failed to load a component", "Failed to load scene", e);
			alert.showAndWait();
		}
	}

	public void userInfo(User u) {
		MainController.user = u;
		lblLoggedUser.setText(user.getUsername());
		lblFullName.setText(user.getName());
		lblEmail.setText(user.getEmail());
		lblBirthdate.setText(user.getBirthdate());
		lblRelationship.setText(user.getRelationship());
	}

	@FXML
	private void newPost() {
		String post = txtNewPost.getText();
		new PostDAO().persist(new Post(post));
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		MainController.setUser(user);
	}
}
