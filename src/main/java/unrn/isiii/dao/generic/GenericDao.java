package unrn.isiii.dao.generic;

import java.util.List;

public interface GenericDao<T> {
	public T create(T t) ;
	public T find(Object id);
	public void delete(Object id) ;
	public T update(T item) ;	
	public List<T> findAll() ;
}