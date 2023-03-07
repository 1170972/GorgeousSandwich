package pt.isep.arqsoft.GorgeousSandwich.repository.comment.wrapper;

import java.util.List;

public interface ICommentRepositoryWrapper<T> {
	
	public T save(T model);
	
	public List<T> findBySandwichId(Long sandwichId);

	public List<T> findByEmail(String email);

}
