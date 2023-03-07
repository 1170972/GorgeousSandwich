package pt.isep.arqsoft.GorgeousSandwich.repository.comment.wrapper;

import java.util.List;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;
import pt.isep.arqsoft.GorgeousSandwich.domain.comment.Comment;
import pt.isep.arqsoft.GorgeousSandwich.persistence.comment.CommentPersistenceJPA;
import pt.isep.arqsoft.GorgeousSandwich.repository.comment.ICommentRepositoryJPA;
import pt.isep.arqsoft.GorgeousSandwich.repository.comment.mapper.CommentMapperJPA;

@Service("CommentRepositoryWrapperJPA")
@ConditionalOnProperty(
		value = "persistence.framework",
		havingValue = "jpa"
)
public class CommentRepositoryWrapperJPA implements ICommentRepositoryWrapper<Comment> {

	private CommentMapperJPA mapper;
	private ICommentRepositoryJPA repository;

	public CommentRepositoryWrapperJPA(CommentMapperJPA mapper, ICommentRepositoryJPA repository) {
		this.mapper = mapper;
		this.repository = repository;
	}

	@Override
	public Comment save(Comment model) {
		CommentPersistenceJPA comment = mapper.convertToPersistence(model);
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
