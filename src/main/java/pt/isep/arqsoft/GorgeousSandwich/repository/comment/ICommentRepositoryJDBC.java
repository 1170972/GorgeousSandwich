package pt.isep.arqsoft.GorgeousSandwich.repository.comment;


import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pt.isep.arqsoft.GorgeousSandwich.persistence.comment.CommentPersistenceJDBC;

import java.util.List;

@Repository
public interface ICommentRepositoryJDBC extends CrudRepository<CommentPersistenceJDBC, Long> {
    @Query("SELECT * FROM COMMENTS WHERE SANDWICH_ID =:sandwichId")
    List<CommentPersistenceJDBC> findBySandwichId(@Param("sandwichId")long sandwichId);

    @Query("SELECT * FROM COMMENTS WHERE EMAIL =:email")
    List<CommentPersistenceJDBC> findByEmail(@Param("email")String email);
}
