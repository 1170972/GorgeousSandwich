package pt.isep.arqsoft.GorgeousSandwich.repository.review;

import java.util.List;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pt.isep.arqsoft.GorgeousSandwich.persistence.review.ReviewPersistenceJDBC;

@Repository
public interface IReviewRepositoryJDBC extends CrudRepository<ReviewPersistenceJDBC, Long> {
	
	@Query("SELECT * FROM REVIEWS WHERE SANDWICH_ID =:sandwichId")
	List<ReviewPersistenceJDBC> findBySandwichId(long sandwichId);

	@Query("SELECT * FROM REVIEWS WHERE EMAIL =:email")
	List<ReviewPersistenceJDBC> findByEmail(String email);

}
