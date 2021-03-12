package arslabadack.ifsc.oop2.skynet.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Post {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int postId;

	private String newPost;

	public Post() {

	}

	public Post(int postId, String newPost) {
		super();
		this.postId = postId;
		this.newPost = newPost;
	}

	public Post(String newPost) {
		super();
		this.newPost = newPost;
	}

	public int getPostId() {
		return postId;
	}

	public String getNewPost() {
		return newPost;
	}

	public void setNewPost(String newPost) {
		this.newPost = newPost;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + postId;
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
		Post other = (Post) obj;
		if (postId != other.postId)
			return false;
		return true;
	}

}
