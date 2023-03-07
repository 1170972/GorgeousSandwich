package pt.isep.arqsoft.GorgeousSandwich.repository.review.mapper;

import java.util.List;

public interface IReviewMapper<T,E> {
    T convertToDomain(E persistence);

    E convertToPersistence(T domain);

    List<T> convertListToDomain(List<E> persistenceList);

    List<E> convertListToPersistence(List<T> domainList);
}
