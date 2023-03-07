package pt.isep.arqsoft.GorgeousSandwich.repository.review.wrapper;

import java.util.List;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

import pt.isep.arqsoft.GorgeousSandwich.domain.review.Review;
import pt.isep.arqsoft.GorgeousSandwich.persistence.review.ReviewPersistenceJPA;
import pt.isep.arqsoft.GorgeousSandwich.repository.review.IReviewRepositoryJPA;
import pt.isep.arqsoft.GorgeousSandwich.repository.review.mapper.ReviewMapperJPA;

@Service("ReviewRepositoryWrapperJPA")
@ConditionalOnProperty(
        value = "persistence.framework",
        havingValue = "jpa"
)
public class ReviewRepositoryWrapperJPA implements IReviewRepositoryWrapper<Review> {
	
	private ReviewMapperJPA mapper;
	
	private IReviewRepositoryJPA repository;
	
	public ReviewRepositoryWrapperJPA(ReviewMapperJPA mapper, IReviewRepositoryJPA repository) {
		this.mapper = mapper;
		this.repository = repository;
	}

	@Override
	public Review save(Review model) {
		ReviewPersistenceJPA review = this.mapper.convertToPersistence(model);
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
