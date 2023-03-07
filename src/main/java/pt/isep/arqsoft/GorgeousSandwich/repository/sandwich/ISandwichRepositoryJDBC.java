package pt.isep.arqsoft.GorgeousSandwich.repository.sandwich;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import pt.isep.arqsoft.GorgeousSandwich.persistence.sandwich.SandwichPersistenceJDBC;

@Repository
public interface ISandwichRepositoryJDBC extends CrudRepository<SandwichPersistenceJDBC, Long>{

}
