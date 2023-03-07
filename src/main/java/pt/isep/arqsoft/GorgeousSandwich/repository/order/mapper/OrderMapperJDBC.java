package pt.isep.arqsoft.GorgeousSandwich.repository.order.mapper;

import pt.isep.arqsoft.GorgeousSandwich.domain.order.*;
import pt.isep.arqsoft.GorgeousSandwich.domain.sandwich.SandwichID;
import pt.isep.arqsoft.GorgeousSandwich.domain.shared.UserEmail;
import pt.isep.arqsoft.GorgeousSandwich.persistence.order.OrderItemPersistenceJDBC;
import pt.isep.arqsoft.GorgeousSandwich.persistence.order.OrderPersistenceJDBC;

import java.util.*;

import org.springframework.stereotype.Service;

@Service
public class OrderMapperJDBC implements IOrderMapper<Order, OrderPersistenceJDBC> {

	@Override
	public Order convertToDomain(OrderPersistenceJDBC persistence) {
		Set<OrderItem> set = new HashSet<>();
		for (OrderItemPersistenceJDBC l : persistence.orderItems) {
			OrderItem orderItem = OrderItem.valueOf(SandwichID.valueOf(l.sandwichId), Quantity.valueOf(l.quantity));
			set.add(orderItem);
		}
		if(persistence.orderId == null){
			return new Order(OrderStatus.valueOf(persistence.orderStatus), DeliveryTime.valueOf(persistence.startTime, persistence.endTime), DeliveryDate.valueOf(persistence.deliveryDate), OrderDate.valueOf(persistence.orderDate), set, UserEmail.valueOf(persistence.email));
		}
		return new Order(OrderStatus.valueOf(persistence.orderStatus), DeliveryTime.valueOf(persistence.startTime, persistence.endTime), DeliveryDate.valueOf(persistence.deliveryDate), OrderDate.valueOf(persistence.orderDate), OrderID.valueOf(persistence.orderId), set,UserEmail.valueOf(persistence.email));
	}

	@Override
	public OrderPersistenceJDBC convertToPersistence(Order domain) {
		Set<OrderItemPersistenceJDBC> set = new HashSet<>();
		for(OrderItem orderItem : domain.obtainOrderItems()){
			set.add(new OrderItemPersistenceJDBC(orderItem.obtainSandwichId().obtainID(), orderItem.obtainQuantity().obtainUnits()));
		}
		if(domain.obtainOrderId() == null){
			return new OrderPersistenceJDBC(domain.obtainOrderStatus().obtainName(), domain.obtainDeliveryTime().obtainStartTime(), domain.obtainDeliveryTime().obtainEndTime(), domain.obtainDeliveryDate().obtainDate(), domain.obtainOrderDate().obtainDate(), null, set,domain.obtainUserEmail().obtainName());
		}
		return new OrderPersistenceJDBC(domain.obtainOrderStatus().obtainName(), domain.obtainDeliveryTime().obtainStartTime(), domain.obtainDeliveryTime().obtainEndTime(), domain.obtainDeliveryDate().obtainDate(), domain.obtainOrderDate().obtainDate(), domain.obtainOrderId().obtainID(), set,domain.obtainUserEmail().obtainName());
	}

	@Override
	public List<Order> convertListToDomain(List<OrderPersistenceJDBC> persistenceList) {
		List<Order> orders = new ArrayList<>();
		for (OrderPersistenceJDBC o : persistenceList) {
			orders.add(convertToDomain(o));
		}
		return orders;
	}

	@Override
	public List<OrderPersistenceJDBC> convertListToPersistence(List<Order> domainList) {
		List<OrderPersistenceJDBC> orders = new ArrayList<>();
		for (Order o : domainList) {
			orders.add(convertToPersistence(o));
		}
		return orders;
	}

}
