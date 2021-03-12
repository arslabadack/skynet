package arslabadack.ifsc.oop2.skynet.controllers;

import arslabadack.ifsc.oop2.skynet.App;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class PostController {

	@FXML
	private Button btnSavePost;

	@FXML
	private Button btnDeletePost;

	@FXML
	private TextField txtPostId;

	@FXML
	private TextArea txtPostContent;

	@FXML
	private ListView<String> listPost;
	
	@FXML
	private void logout() {
		App.changeResizable();
		App.setRoot("login");
	}


}
