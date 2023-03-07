package pt.isep.arqsoft.GorgeousSandwich.repository.review.mapper;

import pt.isep.arqsoft.GorgeousSandwich.domain.review.Grade;
import pt.isep.arqsoft.GorgeousSandwich.domain.review.Review;
import pt.isep.arqsoft.GorgeousSandwich.domain.review.ReviewID;
import pt.isep.arqsoft.GorgeousSandwich.domain.sandwich.SandwichID;
import pt.isep.arqsoft.GorgeousSandwich.domain.shared.Description;
import pt.isep.arqsoft.GorgeousSandwich.domain.shared.UserEmail;
import pt.isep.arqsoft.GorgeousSandwich.persistence.review.ReviewPersistenceJDBC;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class ReviewMapperJDBC implements IReviewMapper<Review, ReviewPersistenceJDBC> {
    @Override
    public Review convertToDomain(ReviewPersistenceJDBC persistence) {
        if (persistence.reviewId == null) {
            return new Review(Description.valueOf(persistence.description), Grade.valueOf(persistence.grade), SandwichID.valueOf(persistence.sandwichId), UserEmail.valueOf(persistence.email));
        }
        return new Review(Description.valueOf(persistence.description),Grade.valueOf(persistence.grade), SandwichID.valueOf(persistence.sandwichId), ReviewID.valueOf(persistence.reviewId), UserEmail.valueOf(persistence.email));
    }

    @Override
    public ReviewPersistenceJDBC convertToPersistence(Review domain) {
        if (domain.obtainReviewId() == null) {
            return new ReviewPersistenceJDBC(null, domain.obtainSandwichId().obtainID(), domain.obtainDescription().obtainName(),domain.obtainGrade().obtainValue(),domain.obtainUserEmail().obtainName());
        }
        return new ReviewPersistenceJDBC(domain.obtainReviewId().obtainID(), domain.obtainSandwichId().obtainID(), domain.obtainDescription().obtainName(),domain.obtainGrade().obtainValue(),domain.obtainUserEmail().obtainName());
    }

    @Override
    public List<Review> convertListToDomain(List<ReviewPersistenceJDBC> persistenceList) {
        List<Review> comments = new ArrayList<>();
        for (ReviewPersistenceJDBC r : persistenceList) {
            comments.add(convertToDomain(r));
        }
        return comments;
    }

    @Override
    public List<ReviewPersistenceJDBC> convertListToPersistence(List<Review> domainList) {
        List<ReviewPersistenceJDBC> comments = new ArrayList<>();
        for (Review r : domainList) {
            comments.add(convertToPersistence(r));
        }
        return comments;
    }
}
