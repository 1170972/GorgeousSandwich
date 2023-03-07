package pt.isep.arqsoft.GorgeousSandwich.repository.review.wrapper;

import java.util.List;

public interface IReviewRepositoryWrapper<T> {
	
	public T save(T model);
	
	public List<T> findBySandwichId(Long sandwichId);

	public List<T> findByEmail(String email);

}
