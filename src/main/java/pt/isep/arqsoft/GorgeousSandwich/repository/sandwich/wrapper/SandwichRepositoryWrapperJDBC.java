package pt.isep.arqsoft.GorgeousSandwich.repository.sandwich.wrapper;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

import pt.isep.arqsoft.GorgeousSandwich.domain.sandwich.Sandwich;
import pt.isep.arqsoft.GorgeousSandwich.persistence.sandwich.SandwichPersistenceJDBC;
import pt.isep.arqsoft.GorgeousSandwich.repository.sandwich.ISandwichRepositoryJDBC;
import pt.isep.arqsoft.GorgeousSandwich.repository.sandwich.mapper.SandwichMapperJDBC;

@Service("SandwichRepositoryWrapperJDBC")
@ConditionalOnProperty(
        value = "persistence.framework",
        havingValue = "jdbc"
)
public class SandwichRepositoryWrapperJDBC implements ISandwichRepositoryWrapper<Sandwich> {
	
	private SandwichMapperJDBC mapper;
	
	private ISandwichRepositoryJDBC repository;
	
	public SandwichRepositoryWrapperJDBC(SandwichMapperJDBC mapper, ISandwichRepositoryJDBC repository) {
		this.mapper = mapper;
		this.repository = repository;
	}

	@Override
	public Sandwich save(Sandwich model) {
		SandwichPersistenceJDBC sandwichJDBC = this.mapper.convertToPersistence(model);
		return this.mapper.convertToDomain(this.repository.save(sandwichJDBC));
	}

	@Override
	public List<Sandwich> findAll() {
		List<SandwichPersistenceJDBC> sandwichesJDBC = new ArrayList<>();
		this.repository.findAll().forEach(sandwichesJDBC::add);
		return this.mapper.convertListToDomain(sandwichesJDBC);
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
			SandwichPersistenceJDBC sandwichJDBC = this.mapper.convertToPersistence(model);
			return this.mapper.convertToDomain(this.repository.save(sandwichJDBC));
		} else {
			throw new NoSuchElementException();
		}
	}

}
