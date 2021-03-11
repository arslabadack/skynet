package arslabadack.ifsc.oop2.skynet.controllers;

import arslabadack.ifsc.oop2.skynet.AlertUtil;
import arslabadack.ifsc.oop2.skynet.App;
import arslabadack.ifsc.oop2.skynet.db.MarketplaceDAO;
import arslabadack.ifsc.oop2.skynet.entities.Marketplace;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class NewSaleController {

	@FXML
	private Button btnSale;

	@FXML
	private TextField txtProductName;

	@FXML
	private TextField txtProductPrice;

	@FXML
	private TextArea txtProductDescription;

	@FXML
	private void logout() {
		App.changeResizable();
		App.setRoot("login");
	}

	@FXML
	private void newSale() {
		String productName = txtProductName.getText();
		String productPrice = txtProductPrice.getText();
		String productDescription = txtProductDescription.getText();

		if (productName.isBlank()) {
			Alert alert = AlertUtil.error("ERROR", "ERROR", "Missing product name", null);
			alert.showAndWait();
			return;
		}
		if (productPrice.isBlank()) {
			Alert alert = AlertUtil.error("ERROR", "ERROR", "Missing product price", null);
			alert.showAndWait();
			return;
		}
		if (productDescription.isBlank()) {
			Alert alert = AlertUtil.error("ERROR", "ERROR", "Missing product description", null);
			alert.showAndWait();
			return;
		}

		new MarketplaceDAO().persist(new Marketplace(productName, productPrice, productDescription));

		AlertUtil.info("DONE", "DONE", "Product added").show();
	}

}
