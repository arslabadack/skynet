package arslabadack.ifsc.oop2.skynet.db;

import java.util.List;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;

import arslabadack.ifsc.oop2.skynet.entities.Events;
import arslabadack.ifsc.oop2.skynet.entities.Marketplace;
import arslabadack.ifsc.oop2.skynet.entities.Post;
import arslabadack.ifsc.oop2.skynet.entities.User;

public class UserDAO implements InterfaceDAO<User> {

	@Override
	public void persist(User t) {
		EntityManager em = UtilDB.getEntityManager();
		try {
			em.getTransaction().begin();
			em.persist(t);
			em.getTransaction().commit();
		} catch (EntityExistsException e) {
			em.getTransaction().rollback();
			User original = get(t.getUsername());
			em.getTransaction().begin();
			original.setPassword(t.getPassword());
			original.getProducts().clear();
			for (Marketplace mk : t.getProducts())
				original.getProducts().add(mk);
			original.getEvents().clear();
			for (Events ev : t.getEvents())
				original.getEvents().add(ev);
			original.getPosts().clear();
			for (Post po : t.getPosts())
				original.getPosts().add(po);
			em.getTransaction().commit();
		}

	}

	@Override
	public void remove(User t) {
		EntityManager em = UtilDB.getEntityManager();
		em.getTransaction().begin();
		em.remove(t);
		em.getTransaction().commit();

	}

	@Override
	public User get(Object pk) {
		EntityManager em = UtilDB.getEntityManager();
		User t = em.find(User.class, pk);
		return t;
	}

	@Override
	public List<User> getAll() {
		return UtilDB.getEntityManager().createQuery("SELECT u FROM User u", User.class).getResultList();
	}

}
