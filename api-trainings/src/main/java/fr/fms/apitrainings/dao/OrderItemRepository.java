package fr.fms.apitrainings.dao;

import fr.fms.apitrainings.entities.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

    public List<OrderItem> findByOrdersId(long id);

}
