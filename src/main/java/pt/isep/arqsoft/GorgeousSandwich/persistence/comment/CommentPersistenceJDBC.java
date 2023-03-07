package pt.isep.arqsoft.GorgeousSandwich.persistence.comment;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("COMMENTS")
public class CommentPersistenceJDBC {

    @Id
    public final Long commentId;
    public final Long sandwichId;
    public final String description;
    public final String email;

    static CommentPersistenceJDBC of(Long sandwichId,String description,String email){
        return new CommentPersistenceJDBC(null,sandwichId,description,email);
    }

    public CommentPersistenceJDBC(Long commentId, Long sandwichId, String description,String email) {
        this.commentId = commentId;
        this.sandwichId = sandwichId;
        this.description = description;
        this.email = email;
    }

    CommentPersistenceJDBC withId(Long commentId){
        return new CommentPersistenceJDBC(commentId, this.sandwichId,this.description,this.email);
    }
}
