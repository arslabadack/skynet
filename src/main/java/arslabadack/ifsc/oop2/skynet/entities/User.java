package arslabadack.ifsc.oop2.skynet.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class User {

	@Id
	private String username;

	private String name;

	private String email;

	private String password;

	private String birthdate;

	private String relationship;

	@ManyToMany
	private List<Marketplace> products;

	@ManyToMany
	private List<Events> events;

	@ManyToMany
	private List<Post> posts;

	public User() {
	}
	
	

	public User(String username, String name, String email, String password, String birthdate, String relationship,
			List<Marketplace> products, List<Events> events, List<Post> posts) {
		super();
		this.username = username;
		this.name = name;
		this.email = email;
		this.password = password;
		this.birthdate = birthdate;
		this.relationship = relationship;
		this.products = new ArrayList<>();
		this.events = new ArrayList<>();
		this.posts = new ArrayList<>();
	}

	public User(String username, String name, String email, String password, String birthdate, String relationship) {
		super();
		this.username = username;
		this.name = name;
		this.email = email;
		this.password = password;
		this.birthdate = birthdate;
		this.relationship = relationship;
	}

	public User(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(String birthdate) {
		this.birthdate = birthdate;
	}

	public String getRelationship() {
		return relationship;
	}

	public void setRelationship(String relationship) {
		this.relationship = relationship;
	}

	public List<Marketplace> getProducts() {
		return products;
	}

	public void setProducts(List<Marketplace> products) {
		this.products = products;
	}

	public List<Events> getEvents() {
		return events;
	}

	public void setEvents(List<Events> events) {
		this.events = events;
	}

	public List<Post> getPosts() {
		return posts;
	}

	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((username == null) ? 0 : username.hashCode());
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
		User other = (User) obj;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}

}