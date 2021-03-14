package arslabadack.ifsc.oop2.skynet.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import arslabadack.ifsc.oop2.skynet.AlertUtil;
import arslabadack.ifsc.oop2.skynet.App;
import arslabadack.ifsc.oop2.skynet.db.PostDAO;
import arslabadack.ifsc.oop2.skynet.db.UserDAO;
import arslabadack.ifsc.oop2.skynet.entities.Events;
import arslabadack.ifsc.oop2.skynet.entities.Post;
import arslabadack.ifsc.oop2.skynet.entities.User;
import javafx.collections.FXCollections;
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
	private ListView<Post> listPosts;

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

			MarketplaceController controller = fxmlLoader.getController();
			controller.setUser(user);

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
		String newPost = txtNewPost.getText();

		if (newPost.isBlank()) {
			Alert alert = AlertUtil.error("ERROR", "ERROR", "Missing post", null);
			alert.showAndWait();
			return;
		}

		Post ps = new Post(newPost);
		new PostDAO().persist(ps);
		if (user.getPosts() == null) {
			user.setPosts(new ArrayList<Post>());
		}
		user.getPosts().add(ps);
		new UserDAO().persist(user);
		txtNewPost.clear();

		showPosts();
	}

	@FXML
	private void showPosts() {
		if (user == null)
			return;
		List<Post> userPosts = new ArrayList<>();
		for (Post ps : user.getPosts()) {
			userPosts.add(new Post(ps.getNewPost()));
		}
		listPosts.setItems(FXCollections.observableArrayList(userPosts));
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
	}
}
