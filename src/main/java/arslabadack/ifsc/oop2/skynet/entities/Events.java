package arslabadack.ifsc.oop2.skynet.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Events {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int eventId;

	private String eventName;

	private String eventDate;

	private String eventLocal;

	private String eventDescription;

	public Events() {

	}

	public Events(int eventId) {
		super();
		this.eventId = eventId;
	}

	public Events(int eventId, String eventName, String eventDate, String eventLocal, String eventDescription) {
		super();
		this.eventId = eventId;
		this.eventName = eventName;
		this.eventDate = eventDate;
		this.eventLocal = eventLocal;
		this.eventDescription = eventDescription;
	}

	public Events(String eventName, String eventDate, String eventLocal, String eventDescription) {
		super();
		this.eventName = eventName;
		this.eventDate = eventDate;
		this.eventLocal = eventLocal;
		this.eventDescription = eventDescription;
	}

	public int getEventId() {
		return eventId;
	}

	public String getEventName() {
		return eventName;
	}

	public void setEventName(String eventName) {
		this.eventName = eventName;
	}

	public String getEventDate() {
		return eventDate;
	}

	public void setEventDate(String eventDate) {
		this.eventDate = eventDate;
	}

	public String getEventLocal() {
		return eventLocal;
	}

	public void setEventLocal(String eventLocal) {
		this.eventLocal = eventLocal;
	}

	public String getEventDescription() {
		return eventDescription;
	}

	public void setEventDescription(String eventDescription) {
		this.eventDescription = eventDescription;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + eventId;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Events other = (Events) obj;
		if (eventId != other.eventId)
			return false;
		return true;
	}

}
