package pt.isep.arqsoft.GorgeousSandwich.repository.order.wrapper;

import pt.isep.arqsoft.GorgeousSandwich.exceptions.InvalidOperationException;

import java.util.List;

public interface IOrderRepositoryWrapper<T> {

	public T save(T model);
	
	public List<T> findAll();

	public T update(T model) throws InvalidOperationException;

	public T getById(Long Id);

	public List<T> getByEmail(String email);
}
