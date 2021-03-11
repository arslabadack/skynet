package arslabadack.ifsc.oop2.skynet.controllers;

import arslabadack.ifsc.oop2.skynet.App;
import javafx.fxml.FXML;

public class editSaleController {

	@FXML
	private void logout() {
		App.changeResizable();
		App.setRoot("login");
	}

}
