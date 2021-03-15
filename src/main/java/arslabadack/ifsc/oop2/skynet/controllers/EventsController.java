package arslabadack.ifsc.oop2.skynet.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import arslabadack.ifsc.oop2.skynet.AlertUtil;
import arslabadack.ifsc.oop2.skynet.App;
import arslabadack.ifsc.oop2.skynet.db.EventsDAO;
import arslabadack.ifsc.oop2.skynet.db.MarketplaceDAO;
import arslabadack.ifsc.oop2.skynet.db.UserDAO;
import arslabadack.ifsc.oop2.skynet.entities.Events;
import arslabadack.ifsc.oop2.skynet.entities.Marketplace;
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

public class EventsController implements Initializable {

	@FXML
	private Button btnCreateEvent;

	@FXML
	private Button btnDeleteEvent;

	@FXML
	private Button btnBack;

	@FXML
	private Button btnConfirm;

	@FXML
	private TextField txtEventName;

	@FXML
	private TextField txtEventDate;

	@FXML
	private TextField txtEventLocal;

	@FXML
	private TextField txtEventDescription;

	@FXML
	private ListView<String> listEvents;

	@FXML
	private ListView<String> listMyEvents;

	@FXML
	private Label lblEventName;

	@FXML
	private Label lblEventDate;

	@FXML
	private Label lblEventLocal;

	@FXML
	private Label lblEventDescription;

	private User user;

	private boolean updating = false;

	public void setUser(User u) {
		user = u;
	}

	@FXML
	private void clearFields() {
		txtEventName.clear();
		txtEventDate.clear();
		txtEventLocal.clear();
		txtEventDescription.clear();
		txtEventName.setDisable(false);

		defaultLabel();
		showEvents();
	}

	private void defaultLabel() {
		lblEventName.setText("Event name");
		lblEventDate.setText("Date");
		lblEventLocal.setText("Local");
		lblEventDescription.setText("Description");
	}

	@FXML
	private void logout() {
		try {
			user = null;
			FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("login.fxml"));
			Scene scene = new Scene(fxmlLoader.load());
			Stage stage = (Stage) btnCreateEvent.getScene().getWindow();
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
			Stage stage = (Stage) btnCreateEvent.getScene().getWindow();
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
	private void newEvent() {
		String eventName = txtEventName.getText();
		String eventDate = txtEventDate.getText();
		String eventLocal = txtEventLocal.getText();
		String eventDescription = txtEventDescription.getText();

		if (eventName.isBlank()) {
			Alert alert = AlertUtil.error("ERROR", "ERROR", "Missing event name", null);
			alert.showAndWait();
			return;
		}
		if (eventDate.isBlank()) {
			Alert alert = AlertUtil.error("ERROR", "ERROR", "Missing event date", null);
			alert.showAndWait();
			return;
		}
		if (eventLocal.isBlank()) {
			Alert alert = AlertUtil.error("ERROR", "ERROR", "Missing event local", null);
			alert.showAndWait();
			return;
		}
		if (eventDescription.isBlank()) {
			Alert alert = AlertUtil.error("ERROR", "ERROR", "Missing event description", null);
			alert.showAndWait();
			return;
		}
		Events evn = new Events(eventName, eventDate, eventLocal, eventDescription);
		new EventsDAO().persist(evn);
		if (!updating) {
			if (user.getEvents() == null) {
				user.setEvents(new ArrayList<Events>());
			}
			user.getEvents().add(evn);
			new UserDAO().persist(user);
		} else {
			user = new UserDAO().get(user.getUsername());
		}

		clearFields();

	}

	@FXML
	private void showEvents() {
		if (user == null)
			return;
		List<String> userEvents = new ArrayList<>();
		for (Events evn : user.getEvents()) {
			userEvents.add(evn.getEventName());
		}
		listEvents.setItems(FXCollections.observableArrayList(userEvents));
	}

	@FXML
	private void removeEvents() {
		String eventSelected = listEvents.getSelectionModel().getSelectedItem();
		Events event = new EventsDAO().get(eventSelected);
		for (int i = 0; i < user.getEvents().size(); i++) {
			if (user.getEvents().get(i).getEventName().contentEquals(eventSelected)) {
				user.getEvents().remove(i);
			}
		}
		new EventsDAO().remove(event);
		new UserDAO().persist(user);
		clearFields();
	}
	
	
	@FXML
	private void updateDescription() {
		String eventSelected = listEvents.getSelectionModel().getSelectedItem();
		Events event = new EventsDAO().get(eventSelected);
		txtEventName.setText(event.getEventName());
		txtEventName.setDisable(true);
		lblEventName.setText(event.getEventName());
		lblEventDate.setText(event.getEventDate());
		lblEventLocal.setText(event.getEventLocal());
		lblEventDescription.setText(event.getEventDescription());
		btnDeleteEvent.setDisable(false);
		updating = true;
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		btnDeleteEvent.setDisable(true);

	}

}
