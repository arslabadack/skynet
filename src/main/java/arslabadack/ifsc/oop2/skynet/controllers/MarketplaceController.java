package arslabadack.ifsc.oop2.skynet.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import arslabadack.ifsc.oop2.skynet.AlertUtil;
import arslabadack.ifsc.oop2.skynet.App;
import arslabadack.ifsc.oop2.skynet.db.MarketplaceDAO;
import arslabadack.ifsc.oop2.skynet.db.UserDAO;
import arslabadack.ifsc.oop2.skynet.entities.Marketplace;
import arslabadack.ifsc.oop2.skynet.entities.User;
import javafx.collections.FXCollections;
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


public class MarketplaceController implements Initializable {

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
	
	private static User user;
	

	@FXML
	private void logout() {
		try {
			user = null;
			FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("login.fxml"));
			Scene scene = new Scene(fxmlLoader.load());
			Stage stage = (Stage) btnSaveSale.getScene().getWindow();
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
			Stage stage = (Stage) btnSaveSale.getScene().getWindow();
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
		user.getProducts().add(new Marketplace(productName, productPrice, productDescription));
		new UserDAO().persist(user);
		
		showMySales();

	}
	
	@FXML
	private void showMySales() {
		if (user == null)
			return;
		List<String> userProducts = new ArrayList<>();
		for (Marketplace p : user.getProducts()) {
			userProducts.add(p.getProductName());
			userProducts.add(p.getProductPrice());
			userProducts.add(p.getProductDescription());
		}
		listMySales.setItems(FXCollections.observableArrayList(userProducts));
	}

	@FXML
	private void removeProducts() {
		String productSelected = listMySales.getSelectionModel().getSelectedItem();
		Marketplace product = new MarketplaceDAO().get(productSelected);
		user.getProducts().remove(product);
		new UserDAO().persist(user);
		showMySales();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		user.getUsername();
		
	}

}
