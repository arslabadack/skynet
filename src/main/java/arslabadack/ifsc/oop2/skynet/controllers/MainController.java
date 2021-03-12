package arslabadack.ifsc.oop2.skynet.controllers;

import arslabadack.ifsc.oop2.skynet.App;
import arslabadack.ifsc.oop2.skynet.db.PostDAO;
import arslabadack.ifsc.oop2.skynet.entities.Post;
import arslabadack.ifsc.oop2.skynet.entities.User;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class MainController {

	private static User user;

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
	Label lblLoggedUser;

	public static void setUser(User u) {
		user = u;
	}

	@FXML
	private void logout() {
		user = null;
		App.changeResizable();
		App.setRoot("login");
	}

	@FXML
	private void marketplace() {
		App.setRoot("marketplace");
	}

	@FXML
	private void events() {
		App.setRoot("events");
	}

	@FXML
	private void post() {
		App.setRoot("post");
	}

	public void userInfo(User u) {
		MainController.user = u;
		lblLoggedUser.setText(user.getUsername());
	}

	@FXML
	private void newPost() {
		String post = txtNewPost.getText();
		new PostDAO().persist(new Post(post));
	}
}
