package pt.isep.arqsoft.GorgeousSandwich.repository.sandwich.wrapper;

import java.util.List;

public interface ISandwichRepositoryWrapper<T> {
	
	public T save(T model);
	
	public List<T> findAll();

	public boolean checkIfExists(Long Id);

	public T getById(Long Id);
	
	public T update(T model);
}
