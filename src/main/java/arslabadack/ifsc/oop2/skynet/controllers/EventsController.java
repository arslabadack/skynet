package arslabadack.ifsc.oop2.skynet.controllers;

import arslabadack.ifsc.oop2.skynet.AlertUtil;
import arslabadack.ifsc.oop2.skynet.App;
import arslabadack.ifsc.oop2.skynet.db.EventsDAO;
import arslabadack.ifsc.oop2.skynet.entities.Events;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class EventsController {

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
	private ListView listEvents;
	

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


		new EventsDAO().persist(new Events(eventName, eventDate, eventLocal, eventDescription));

		AlertUtil.info("DONE", "DONE", "Event created").show();
	}

}
