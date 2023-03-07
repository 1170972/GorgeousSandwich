package pt.isep.arqsoft.GorgeousSandwich.repository.review.wrapper;

import java.util.List;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

import pt.isep.arqsoft.GorgeousSandwich.domain.review.Review;
import pt.isep.arqsoft.GorgeousSandwich.persistence.review.ReviewPersistenceJDBC;
import pt.isep.arqsoft.GorgeousSandwich.repository.review.IReviewRepositoryJDBC;
import pt.isep.arqsoft.GorgeousSandwich.repository.review.mapper.ReviewMapperJDBC;

@Service("ReviewRepositoryWrapperJDBC")
@ConditionalOnProperty(
        value = "persistence.framework",
        havingValue = "jdbc"
)
public class ReviewRepositoryWrapperJDBC implements IReviewRepositoryWrapper<Review> {
	
	private ReviewMapperJDBC mapper;
	
	private IReviewRepositoryJDBC repository;
	
	public ReviewRepositoryWrapperJDBC(ReviewMapperJDBC mapper, IReviewRepositoryJDBC repository) {
		this.mapper = mapper;
		this.repository = repository;
	}

	@Override
	public Review save(Review model) {
		ReviewPersistenceJDBC review = this.mapper.convertToPersistence(model);
		return this.mapper.convertToDomain(this.repository.save(review));
	}

	@Override
	public List<Review> findBySandwichId(Long sandwichId) {
		return this.mapper.convertListToDomain(this.repository.findBySandwichId(sandwichId));
	}

	@Override
	public List<Review> findByEmail(String email) {
		return this.mapper.convertListToDomain(this.repository.findByEmail(email));
	}

}
