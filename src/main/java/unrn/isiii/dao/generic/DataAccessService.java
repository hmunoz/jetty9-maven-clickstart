package unrn.isiii.dao.generic;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.transaction.annotation.Transactional;

public abstract class DataAccessService<T> {

	@PersistenceContext
	private EntityManager em;

	public EntityManager getEm() {
		return em;
	}

	public DataAccessService() {
	}

	private Class<T> type;

	/**
	 * Default constructor
	 * 
	 * @param type
	 *            entity class
	 */
	public DataAccessService(Class<T> type) {
		this.type = type;
	}

	/**
	 * Stores an instance of the entity class in the database
	 * 
	 * @param T
	 *            Object
	 * @return
	 */
	@Transactional
	public T create(T t) {
		this.em.persist(t);
		this.em.flush();
		this.em.refresh(t);
		return t;
	}

	/**
	 * Retrieves an entity instance that was previously persisted to the
	 * database
	 * 
	 * @param T
	 *            Object
	 * @param id
	 * @return
	 */
	@Transactional(readOnly  = true)
	public T find(Object id) {
		return this.em.find(this.type, id);
	}

	/**
	 * Removes the record that is associated with the entity instance
	 * 
	 * @param type
	 * @param id
	 */
	@Transactional
	public void delete(Object id) {
		Object ref = this.em.getReference(this.type, id);
		this.em.remove(ref);
	}

	/**
	 * Updates the entity instance
	 * 
	 * @param <T>
	 * @param t
	 * @return the object that is updated
	 */
	@Transactional
	public T update(T item) {

		return (T) this.em.merge(item);

	}
	@Transactional(readOnly = true)
	public List<T> findAll() {
		return this.em.createQuery("select o from " + this.type.getName() + " o").getResultList();
	}
    


}