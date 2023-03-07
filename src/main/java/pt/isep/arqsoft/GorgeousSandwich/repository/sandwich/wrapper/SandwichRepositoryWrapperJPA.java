package pt.isep.arqsoft.GorgeousSandwich.repository.sandwich.wrapper;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

import pt.isep.arqsoft.GorgeousSandwich.domain.sandwich.Sandwich;
import pt.isep.arqsoft.GorgeousSandwich.persistence.sandwich.SandwichPersistenceJPA;
import pt.isep.arqsoft.GorgeousSandwich.repository.sandwich.ISandwichRepositoryJPA;
import pt.isep.arqsoft.GorgeousSandwich.repository.sandwich.mapper.SandwichMapperJPA;

@Service("SandwichRepositoryWrapperJPA")
@ConditionalOnProperty(
        value = "persistence.framework",
        havingValue = "jpa"
)
public class SandwichRepositoryWrapperJPA implements ISandwichRepositoryWrapper<Sandwich> {
	
	private SandwichMapperJPA mapper;
	
	private ISandwichRepositoryJPA repository;
	
	public SandwichRepositoryWrapperJPA(SandwichMapperJPA mapper, ISandwichRepositoryJPA repository) {
		this.mapper = mapper;
		this.repository = repository;
	}

	@Override
	public Sandwich save(Sandwich model) {
		SandwichPersistenceJPA sandwichJPA = this.mapper.convertToPersistence(model);
		return this.mapper.convertToDomain(this.repository.save(sandwichJPA));
	}

	@Override
	public List<Sandwich> findAll() {
		List<SandwichPersistenceJPA> sandwichesJPA = this.repository.findAll();
		return this.mapper.convertListToDomain(sandwichesJPA);
	}

	@Override
	public boolean checkIfExists(Long Id) {
		return this.repository.findById(Id).isPresent();
	}

	@Override
	public Sandwich getById(Long Id) {
		return this.mapper.convertToDomain(this.repository.findById(Id).get());
	}

	@Override
	public Sandwich update(Sandwich model) {
		if(this.repository.existsById(model.obtainSandwichID().obtainID())) {
			SandwichPersistenceJPA sandwichJPA = this.mapper.convertToPersistence(model);
			return this.mapper.convertToDomain(this.repository.save(sandwichJPA));
		} else {
			throw new NoSuchElementException();
		}
	}

}
