package arslabadack.ifsc.oop2.skynet.controllers;

import java.util.ArrayList;
import java.util.List;

import arslabadack.ifsc.oop2.skynet.AlertUtil;
import arslabadack.ifsc.oop2.skynet.App;
import arslabadack.ifsc.oop2.skynet.db.MarketplaceDAO;
import arslabadack.ifsc.oop2.skynet.db.UserDAO;
import arslabadack.ifsc.oop2.skynet.entities.Marketplace;
import arslabadack.ifsc.oop2.skynet.entities.User;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class MarketplaceController {

	private static User user;

	@FXML
	private Button btnSaveSale;

	@FXML
	private Button btnRemoveSale;

	@FXML
	private Button btnBack;

	@FXML
	private TextField txtProductName;

	@FXML
	private TextField txtProductPrice;

	@FXML
	private TextArea txtProductDescription;

	@FXML
	private ListView<String> listAllSales;

	@FXML
	private ListView<String> listMySales;

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
	private void back() {
		App.setRoot("main");
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

	@FXML
	private void showMySales() {
		if (user == null)
			return;
		List<String> myProducts = new ArrayList<>();
		for (Marketplace p : user.getProducts()) {
			myProducts.add(p.getProductName());
			myProducts.add(p.getProductPrice());
			myProducts.add(p.getProductDescription());
		}
		listMySales.setItems(FXCollections.observableArrayList(myProducts));
	}

	@FXML
	private void removeProducts() {
		String productName = listMySales.getSelectionModel().getSelectedItem();
		Marketplace product = new MarketplaceDAO().get(productName);
		user.getProducts().remove(product);
		new UserDAO().persist(user);
		showMySales();
	}
}
