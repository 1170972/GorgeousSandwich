package pt.isep.arqsoft.GorgeousSandwich.persistence.order;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Set;

@Table("ORDERS")
public class OrderPersistenceJDBC {

    public final String orderStatus;
    
    public final LocalTime startTime;
    
    public final LocalTime endTime;
    
    public final LocalDate deliveryDate;
    
    public final LocalDate orderDate;

    public final String email;
    
    @Id
    public final Long orderId;
    
    @MappedCollection(idColumn = "ORDER_ID", keyColumn = "ORDER_ID")
    public final Set<OrderItemPersistenceJDBC> orderItems;

    static OrderPersistenceJDBC of(String orderStatus, LocalTime startTime, LocalTime endTime, LocalDate deliveryDate, LocalDate orderDate, Set<OrderItemPersistenceJDBC> orderItems,String email){
        return new OrderPersistenceJDBC(orderStatus, startTime, endTime, deliveryDate, orderDate, null, orderItems,email);
    }

    public OrderPersistenceJDBC(String orderStatus, LocalTime startTime, LocalTime endTime, LocalDate deliveryDate, LocalDate orderDate, Long orderId, Set<OrderItemPersistenceJDBC> orderItems,String email) {
        this.orderStatus = orderStatus;
        this.startTime = startTime;
        this.endTime = endTime;
        this.deliveryDate = deliveryDate;
        this.orderDate = orderDate;
        this.orderId = orderId;
        this.orderItems = orderItems;
        this.email = email;
    }

    OrderPersistenceJDBC withId(Long orderId){
        return new OrderPersistenceJDBC(this.orderStatus, this.startTime, this.endTime, this.deliveryDate, this.orderDate, orderId, this.orderItems,this.email);
    }
}
