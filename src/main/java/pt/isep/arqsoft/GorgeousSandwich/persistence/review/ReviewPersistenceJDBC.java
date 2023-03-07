package pt.isep.arqsoft.GorgeousSandwich.persistence.review;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("REVIEWS")
public class ReviewPersistenceJDBC {
    @Id
    public final Long reviewId;
    public final Long sandwichId;
    public final String description;
    public final int grade;
    public final String email;

    static ReviewPersistenceJDBC of(Long sandwichId, String description,int grade,String email){
        return new ReviewPersistenceJDBC(null,sandwichId,description,grade,email);
    }

    public ReviewPersistenceJDBC(Long reviewId, Long sandwichId, String description,int grade,String email) {
        this.reviewId = reviewId;
        this.sandwichId = sandwichId;
        this.description = description;
        this.grade = grade;
        this.email = email;
    }

    ReviewPersistenceJDBC withId(Long reviewId){
        return new ReviewPersistenceJDBC(reviewId, this.sandwichId,this.description,this.grade,this.email);
    }
}
