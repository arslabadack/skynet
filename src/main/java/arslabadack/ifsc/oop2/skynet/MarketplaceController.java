package arslabadack.ifsc.oop2.skynet;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class MarketplaceController {

	@FXML
	private Button btnNewSale;
	
	@FXML
	private Button btnEditSale;
	
	@FXML
	private void logout() {
		App.changeResizable();
		App.setRoot("login");
	}
	
	@FXML
	private void back() {
		App.setRoot("main");
	}
	
	@FXML
	private void newSale() {
		Stage stage = new Stage();
		stage.setScene(FXMLUtil.loadScene("newSale"));
		stage.setResizable(false);
		stage.show();
	}
	
	@FXML
	private void editSale() {
		Stage stage = new Stage();
		stage.setScene(FXMLUtil.loadScene("editSale"));
		stage.setResizable(false);
		stage.show();
	}
}
