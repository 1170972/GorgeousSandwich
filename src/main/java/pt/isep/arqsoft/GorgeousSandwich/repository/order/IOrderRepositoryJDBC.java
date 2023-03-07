package pt.isep.arqsoft.GorgeousSandwich.repository.order;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import pt.isep.arqsoft.GorgeousSandwich.persistence.order.OrderPersistenceJDBC;

import java.util.List;

@Repository
public interface IOrderRepositoryJDBC extends CrudRepository<OrderPersistenceJDBC, Long>{

    @Query("SELECT * FROM ORDERS WHERE EMAIL =:email")
    List<OrderPersistenceJDBC> findByEmail(String email);
}
