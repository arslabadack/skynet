package arslabadack.ifsc.oop2.skynet.db;

import java.util.List;

public interface InterfaceDAO<T> {

	public void persist(T t);

	public void remove(T t);
	
	public T get(Object pk);

	public List<T> getAll();

}