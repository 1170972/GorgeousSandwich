package pt.isep.arqsoft.GorgeousSandwich.persistence.sandwich;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("SANDWICHES")
public class SandwichPersistenceJDBC {

    @Id
    public final Long sandwichId;
    
    public final String type;
    
    public final int stock;
    
    public final String designation;
    
    public final String description;

    static SandwichPersistenceJDBC of(String type, int stock, String designation, String description){
        return new SandwichPersistenceJDBC(null, type, stock, designation, description);
    }

    public SandwichPersistenceJDBC(Long sandwichId, String type, int stock, String designation, String description) {
        this.sandwichId = sandwichId;
        this.type = type;
        this.stock = stock;
        this.designation = designation;
        this.description = description;
    }

    SandwichPersistenceJDBC withId(Long sandwichId){
        return new SandwichPersistenceJDBC(sandwichId, this.type, this.stock, this.designation, this.description);
    }
}
