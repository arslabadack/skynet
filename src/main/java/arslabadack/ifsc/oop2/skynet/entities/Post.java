package arslabadack.ifsc.oop2.skynet.entities;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Post {

	@Id
	private String postTitle;

	private String newPost;

	public Post() {

	}

	public Post(String postTitle, String newPost) {
		super();
		this.postTitle = postTitle;
		this.newPost = newPost;
	}

	public Post(String newPost) {
		super();
		this.newPost = newPost;
	}

	public String getPostTitle() {
		return postTitle;
	}

	public void setPostTitle(String postTitle) {
		this.postTitle = postTitle;
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
		result = prime * result + ((postTitle == null) ? 0 : postTitle.hashCode());
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
		if (postTitle == null) {
			if (other.postTitle != null)
				return false;
		} else if (!postTitle.equals(other.postTitle))
			return false;
		return true;
	}

}
