package pt.isep.arqsoft.GorgeousSandwich.repository.comment.mapper;

import pt.isep.arqsoft.GorgeousSandwich.domain.comment.Comment;
import pt.isep.arqsoft.GorgeousSandwich.domain.comment.CommentID;
import pt.isep.arqsoft.GorgeousSandwich.domain.sandwich.SandwichID;
import pt.isep.arqsoft.GorgeousSandwich.domain.shared.Description;
import pt.isep.arqsoft.GorgeousSandwich.domain.shared.UserEmail;
import pt.isep.arqsoft.GorgeousSandwich.persistence.comment.CommentPersistenceJDBC;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class CommentMapperJDBC implements ICommentMapper<Comment, CommentPersistenceJDBC>{

    @Override
    public Comment convertToDomain(CommentPersistenceJDBC persistence) {
       if (persistence.commentId == null){
           return new Comment(Description.valueOf(persistence.description), SandwichID.valueOf(persistence.sandwichId), UserEmail.valueOf(persistence.email));
       }
       return new Comment(Description.valueOf(persistence.description),SandwichID.valueOf(persistence.sandwichId),  UserEmail.valueOf(persistence.email),CommentID.valueOf(persistence.commentId));
    }

    @Override
    public CommentPersistenceJDBC convertToPersistence(Comment domain) {
        if(domain.obtainCommentId() == null){
            return new CommentPersistenceJDBC(null,domain.obtainSandwichId().obtainID(),domain.obtainDescription().obtainName(),domain.obtainEmail().obtainName());
        }
        return  new CommentPersistenceJDBC(domain.obtainCommentId().obtainID(),domain.obtainSandwichId().obtainID(),domain.obtainDescription().obtainName(),domain.obtainEmail().obtainName());
    }

    @Override
    public List<Comment> convertListToDomain(List<CommentPersistenceJDBC> persistenceList) {
        List<Comment> comments = new ArrayList<>();
        for (CommentPersistenceJDBC c:persistenceList) {
            comments.add(convertToDomain(c));
        }
        return comments;
    }

    @Override
    public List<CommentPersistenceJDBC> convertListToPersistence(List<Comment> domainList) {
        List<CommentPersistenceJDBC> comments = new ArrayList<>();
        for (Comment c:domainList) {
            comments.add(convertToPersistence(c));
        }
        return comments;
    }
}
