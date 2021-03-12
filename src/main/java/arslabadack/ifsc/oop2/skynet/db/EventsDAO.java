package arslabadack.ifsc.oop2.skynet.db;

import java.util.List;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;

import arslabadack.ifsc.oop2.skynet.entities.Events;

public class EventsDAO implements InterfaceDAO<Events> {

	@Override
	public void persist(Events t) {
		EntityManager em = UtilDB.getEntityManager();
		try {
			em.getTransaction().begin();
			em.persist(t);
			em.getTransaction().commit();
		} catch (EntityExistsException e) {
			em.getTransaction().rollback();
			Events original = get(t.getEventId());
			em.getTransaction().begin();
			original.setEventName(t.getEventName());
			original.setEventDate(t.getEventDate());
			original.setEventLocal(t.getEventLocal());
			original.setEventDescription(t.getEventDescription());
			em.getTransaction().commit();
		}

	}

	@Override
	public void remove(Events t) {
		EntityManager em = UtilDB.getEntityManager();
		em.getTransaction().begin();
		em.remove(t);
		em.getTransaction().commit();

	}

	@Override
	public Events get(Object pk) {
		EntityManager em = UtilDB.getEntityManager();
		Events t = em.find(Events.class, pk);
		return t;
	}

	@Override
	public List<Events> getAll() {
		return UtilDB.getEntityManager().createQuery("SELECT u FROM Events u", Events.class).getResultList();
	}
}
