package pt.isep.arqsoft.GorgeousSandwich.repository.sandwich.mapper;

import pt.isep.arqsoft.GorgeousSandwich.domain.sandwich.*;
import pt.isep.arqsoft.GorgeousSandwich.domain.shared.Description;
import pt.isep.arqsoft.GorgeousSandwich.persistence.sandwich.SandwichPersistenceJDBC;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class SandwichMapperJDBC implements ISandwichMapper<Sandwich, SandwichPersistenceJDBC> {

	@Override
	public Sandwich convertToDomain(SandwichPersistenceJDBC persistence) {
		if(persistence.sandwichId == null){
			return new Sandwich(Type.valueOf(persistence.type), Stock.valueOf(persistence.stock), Designation.valueOf(persistence.designation), Description.valueOf(persistence.description));
		}
		return new Sandwich(Type.valueOf(persistence.type), Stock.valueOf(persistence.stock), Designation.valueOf(persistence.designation), Description.valueOf(persistence.description), SandwichID.valueOf(persistence.sandwichId));
	}

	@Override
	public SandwichPersistenceJDBC convertToPersistence(Sandwich domain) {
		if(domain.obtainSandwichID() == null){
			return new SandwichPersistenceJDBC(null, domain.obtainType().name(), domain.obtainStock().obtainUnits(), domain.obtainDesignation().obtainName(), domain.obtainDescription().obtainName());
		}
		return new SandwichPersistenceJDBC(domain.obtainSandwichID().obtainID(), domain.obtainType().name(), domain.obtainStock().obtainUnits(), domain.obtainDesignation().obtainName(), domain.obtainDescription().obtainName());
	}

	@Override
	public List<Sandwich> convertListToDomain(List<SandwichPersistenceJDBC> persistenceList) {
		List<Sandwich> sandwiches = new ArrayList<>();
		for (SandwichPersistenceJDBC s : persistenceList) {
			sandwiches.add(convertToDomain(s));
		}
		return sandwiches;
	}

	@Override
	public List<SandwichPersistenceJDBC> convertListToPersistence(List<Sandwich> domainList) {
		List<SandwichPersistenceJDBC> sandwiches = new ArrayList<>();
		for (Sandwich s : domainList) {
			sandwiches.add(convertToPersistence(s));
		}
		return sandwiches;
	}

}
