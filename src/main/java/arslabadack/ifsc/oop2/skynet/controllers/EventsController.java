package arslabadack.ifsc.oop2.skynet.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import arslabadack.ifsc.oop2.skynet.AlertUtil;
import arslabadack.ifsc.oop2.skynet.App;
import arslabadack.ifsc.oop2.skynet.db.EventsDAO;
import arslabadack.ifsc.oop2.skynet.db.UserDAO;
import arslabadack.ifsc.oop2.skynet.entities.Events;
import arslabadack.ifsc.oop2.skynet.entities.User;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class EventsController implements Initializable {

	@FXML
	private Button btnCreateEvent;

	@FXML
	private Button btnEventClose;

	@FXML
	private Button btnSaveEvent;

	@FXML
	private Button btnDeleteEvent;

	@FXML
	private TextField txtEventName;

	@FXML
	private TextField txtEventDate;

	@FXML
	private TextField txtEventLocal;

	@FXML
	private TextField txtEventDescription;

	@FXML
	private TextField txtEventID;

	@FXML
	private TextField txtEventNewName;

	@FXML
	private TextField txtEventNewDate;

	@FXML
	private TextField txtEventNewLocal;

	@FXML
	private TextField txtEventNewDescription;

	@FXML
	private ListView<Events> listEvents;

	private User user;
	
	public void setUser(User u) {
		user = u;
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
		if (user.getEvents() == null) {
			user.setEvents(new ArrayList<Events>());
		}
		user.getEvents().add(evn);
		new UserDAO().persist(user);

		showEvents();
	}

	@FXML
	private void showEvents() {
		if (user == null)
			return;
		List<Events> userEvents = new ArrayList<>();
		for (Events evn : user.getEvents()) {
			userEvents.add(
					new Events(evn.getEventName(), evn.getEventDate(), evn.getEventLocal(), evn.getEventDescription()));
		}
		listEvents.setItems(FXCollections.observableArrayList(userEvents));
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub

	}

}
