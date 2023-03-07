package pt.isep.arqsoft.GorgeousSandwich.persistence.order;

import org.springframework.data.relational.core.mapping.Table;

@Table("ORDER_ITEM")
public class OrderItemPersistenceJDBC {
	
    public final Long sandwichId;
    
    public final int quantity;

    public OrderItemPersistenceJDBC(Long sandwichId, int quantity) {
        this.sandwichId = sandwichId;
        this.quantity = quantity;
    }
}
