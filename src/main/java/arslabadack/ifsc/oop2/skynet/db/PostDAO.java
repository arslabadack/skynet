package arslabadack.ifsc.oop2.skynet.db;

import java.util.List;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;

import arslabadack.ifsc.oop2.skynet.entities.Post;

public class PostDAO implements InterfaceDAO<Post> {

	@Override
	public void persist(Post t) {
		EntityManager em = UtilDB.getEntityManager();
		try {
			em.getTransaction().begin();
			em.persist(t);
			em.getTransaction().commit();
		} catch (EntityExistsException e) {
			em.getTransaction().rollback();
			Post original = get(t.getPostTitle());
			em.getTransaction().begin();
			original.setNewPost(t.getNewPost());
			em.getTransaction().commit();
		}

	}

	@Override
	public void remove(Post t) {
		EntityManager em = UtilDB.getEntityManager();
		em.getTransaction().begin();
		em.remove(t);
		em.getTransaction().commit();

	}

	@Override
	public Post get(Object pk) {
		EntityManager em = UtilDB.getEntityManager();
		Post t = em.find(Post.class, pk);
		return t;
	}

	@Override
	public List<Post> getAll() {
		return UtilDB.getEntityManager().createQuery("SELECT u FROM Post u", Post.class).getResultList();
	}
}
