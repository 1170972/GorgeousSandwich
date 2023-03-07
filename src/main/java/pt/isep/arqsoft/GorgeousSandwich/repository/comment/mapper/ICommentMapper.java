package pt.isep.arqsoft.GorgeousSandwich.repository.comment.mapper;

import java.util.List;

public interface ICommentMapper<T,E> {
    T convertToDomain(E persistence);

    E convertToPersistence(T domain);

    List<T> convertListToDomain(List<E> persistenceList);

    List<E> convertListToPersistence(List<T> domainList);
}
