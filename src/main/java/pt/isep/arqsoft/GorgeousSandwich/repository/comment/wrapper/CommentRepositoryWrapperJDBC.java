package pt.isep.arqsoft.GorgeousSandwich.repository.comment.wrapper;

import java.util.List;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;
import pt.isep.arqsoft.GorgeousSandwich.domain.comment.Comment;
import pt.isep.arqsoft.GorgeousSandwich.persistence.comment.CommentPersistenceJDBC;
import pt.isep.arqsoft.GorgeousSandwich.repository.comment.ICommentRepositoryJDBC;
import pt.isep.arqsoft.GorgeousSandwich.repository.comment.mapper.CommentMapperJDBC;

@Service("CommentRepositoryWrapperJDBC")
@ConditionalOnProperty(
		value = "persistence.framework",
		havingValue = "jdbc"
)
public class CommentRepositoryWrapperJDBC implements ICommentRepositoryWrapper<Comment> {

	private CommentMapperJDBC mapper;

	private ICommentRepositoryJDBC repository;

	public CommentRepositoryWrapperJDBC(CommentMapperJDBC mapper, ICommentRepositoryJDBC repository) {
		this.mapper = mapper;
		this.repository = repository;
	}

	@Override
	public Comment save(Comment model) {
		CommentPersistenceJDBC comment = mapper.convertToPersistence(model);
		return this.mapper.convertToDomain(this.repository.save(comment));
	}

	@Override
	public List<Comment> findBySandwichId(Long sandwichId) {
		return this.mapper.convertListToDomain(this.repository.findBySandwichId(sandwichId));
	}

	@Override
	public List<Comment> findByEmail(String email) {
		return this.mapper.convertListToDomain(this.repository.findByEmail(email));
	}

}
