package pt.isep.arqsoft.GorgeousSandwich.repository.order.mapper;

import java.util.List;

public interface IOrderMapper<T,E> {
	
	T convertToDomain(E persistence);

	E convertToPersistence(T domain);

	List<T> convertListToDomain(List<E> persistenceList);

	List<E> convertListToPersistence(List<T> domainList);
}
