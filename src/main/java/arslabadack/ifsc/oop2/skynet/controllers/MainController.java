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
	private Button btnMarketplace;

	@FXML
	private Button btnEvents;

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

	@FXML
	private Button btnSavePost;

	@FXML
	private Button btnDeletePost;

	@FXML
	private Button btnClear;

	@FXML
	private TextField txtPostTitle;

	@FXML
	private TextField txtPostContent;

	@FXML
	private Label lblPostContent;

	@FXML
	private Label lblMyPostContent;

	@FXML
	private ListView<String> listPosts;

	@FXML
	private ListView<String> listMyPosts;

	private boolean updating = false;

	private static User user;

	public static void setUser(User u) {
		user = u;
	}

	@FXML
	private void clearFields() {
		txtPostTitle.clear();
		txtPostContent.clear();
		txtPostTitle.setDisable(false);

		defaultLabel();
		showMyPosts();
		showAllPosts();
	}

	private void defaultLabel() {
		lblPostContent.setText("Post content");
		lblMyPostContent.setText("Post content");
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
			Stage stage = (Stage) btnEvents.getScene().getWindow();
			stage.setScene(scene);
			stage.setResizable(true);
			stage.show();

			EventsController controller = fxmlLoader.getController();
			controller.setUser(user);

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
		String postTitle = txtPostTitle.getText();
		String postContent = txtPostContent.getText();

		if (postTitle.isBlank()) {
			Alert alert = AlertUtil.error("ERROR", "ERROR", "Missing title", null);
			alert.showAndWait();
			return;
		}
		if (postContent.isBlank()) {
			Alert alert = AlertUtil.error("ERROR", "ERROR", "Missing post", null);
			alert.showAndWait();
			return;
		}

		Post ps = new Post(postTitle, postContent);
		new PostDAO().persist(ps);
		if (!updating) {
			if (user.getPosts() == null) {
				user.setPosts(new ArrayList<Post>());
			}
			user.getPosts().add(ps);
			new UserDAO().persist(user);
		} else {
			user = new UserDAO().get(user.getUsername());
		}

		clearFields();
	}

	@FXML
	private void showMyPosts() {
		if (user == null)
			return;
		List<String> userPosts = new ArrayList<>();
		for (Post pst : user.getPosts()) {
			userPosts.add(pst.getPostTitle());
		}
		listMyPosts.setItems(FXCollections.observableArrayList(userPosts));
	}

	@FXML
	private void showAllPosts() {
		if (user == null)
			return;
		List<String> allPosts = new ArrayList<>();
		for (Post pst : new PostDAO().getAll()) {
			allPosts.add(pst.getPostTitle());
		}
		listPosts.setItems(FXCollections.observableArrayList(allPosts));
	}

	@FXML
	private void updateDescription() {
		String postSelected = listMyPosts.getSelectionModel().getSelectedItem();
		Post post = new PostDAO().get(postSelected);
		txtPostTitle.setText(post.getPostTitle());
		txtPostTitle.setDisable(true);
		lblMyPostContent.setText(post.getNewPost());
		btnDeletePost.setDisable(false);
		updating = true;
	}
	
	@FXML
	private void updateAllDescription() {
		String postSelected = listPosts.getSelectionModel().getSelectedItem();
		Post post = new PostDAO().get(postSelected);
		lblPostContent.setText(post.getNewPost());
	}

	@FXML
	private void removePosts() {
		String postSelected = listMyPosts.getSelectionModel().getSelectedItem();
		Post post = new PostDAO().get(postSelected);
		for (int i = 0; i < user.getPosts().size(); i++) {
			if (user.getPosts().get(i).getPostTitle().contentEquals(postSelected)) {
				user.getPosts().remove(i);
			}
		}
		new PostDAO().remove(post);
		new UserDAO().persist(user);
		clearFields();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		btnDeletePost.setDisable(true);
	}
}
