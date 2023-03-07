package pt.isep.arqsoft.GorgeousSandwich.repository.order.wrapper;

import java.time.Duration;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.apache.commons.lang.Validate;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

import pt.isep.arqsoft.GorgeousSandwich.domain.order.DeliveryTime;
import pt.isep.arqsoft.GorgeousSandwich.domain.order.Order;
import pt.isep.arqsoft.GorgeousSandwich.dto.order.DeliveryTimeDTO;
import pt.isep.arqsoft.GorgeousSandwich.exceptions.InvalidOperationException;
import pt.isep.arqsoft.GorgeousSandwich.persistence.order.OrderPersistenceJDBC;
import pt.isep.arqsoft.GorgeousSandwich.repository.order.IOrderRepositoryJDBC;
import pt.isep.arqsoft.GorgeousSandwich.repository.order.mapper.OrderMapperJDBC;

@Service("OrderRepositoryWrapperJDBC")
@ConditionalOnProperty(
        value = "persistence.framework",
        havingValue = "jdbc"
)
public class OrderRepositoryWrapperJDBC implements IOrderRepositoryWrapper<Order> {
	
	private OrderMapperJDBC mapper;
	
	private IOrderRepositoryJDBC repository;
	
	public OrderRepositoryWrapperJDBC(OrderMapperJDBC mapper, IOrderRepositoryJDBC repository) {
		this.mapper = mapper;
		this.repository = repository;
	}

	@Override
	public Order save(Order model) {
		this.validateModel(model);
		OrderPersistenceJDBC orderJDBC = this.mapper.convertToPersistence(model);
		return this.mapper.convertToDomain(this.repository.save(orderJDBC));
	}

	@Override
	public List<Order> findAll() {
		List<OrderPersistenceJDBC> ordersJDBC = new ArrayList<>();
		this.repository.findAll().forEach(ordersJDBC::add);
		return this.mapper.convertListToDomain(ordersJDBC);
	}

	@Override
	public Order update(Order model) throws InvalidOperationException {
		if(this.repository.existsById(model.obtainOrderId().obtainID())){
			this.validateModel(model);
			OrderPersistenceJDBC order = this.mapper.convertToPersistence(model);
			return this.mapper.convertToDomain(this.repository.save(order));
		}else {
			throw  new NoSuchElementException();
		}
	}

	@Override
	public Order getById(Long Id) {
		return this.mapper.convertToDomain(this.repository.findById(Id).get());
	}

	@Override
	public List<Order> getByEmail(String email) {
		return this.mapper.convertListToDomain(this.repository.findByEmail(email));
	}

	private void validateModel(Order model) {
		List<DeliveryTimeDTO> list = DeliveryTime.calculateIntervals();
		DeliveryTimeDTO time = new DeliveryTimeDTO(model.obtainDeliveryTime().obtainStartTime().toString(),model.obtainDeliveryTime().obtainEndTime().toString());
		Validate.isTrue(list.contains(time),"Invalid delivery time interval");
	}

}
