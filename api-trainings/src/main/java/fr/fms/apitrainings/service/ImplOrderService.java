package fr.fms.apitrainings.service;

import fr.fms.apitrainings.dao.OrderItemRepository;
import fr.fms.apitrainings.dao.OrdersRepository;
import fr.fms.apitrainings.entities.OrderItem;
import fr.fms.apitrainings.entities.Orders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ImplOrderService implements IService<Orders> {

    private int orderNumber = 20220;

    @Autowired
    OrdersRepository ordersRepository;

    @Autowired
    OrderItemRepository orderItemRepository;

    @Override
    public List<Orders> getAll() {
        return ordersRepository.findAll();
    }

    @Override
    public Optional<Orders> getOneById(long id) {
        return ordersRepository.findById(id);
    }

    @Override
    public Orders save(Orders obj) {
        List<OrderItem> orderItems = obj.getOrderItems();
        obj.setNumber(getLastOrderNumber());
        ordersRepository.save(obj);
        long lastOrderId = getLastOrderId();
        for (OrderItem i : orderItems) {
            orderItemRepository.save(new OrderItem(null, i.getQuantity(), i.getPrice(), i.getTraining(), getOneById(lastOrderId).get()));
        }
        return getOneById(lastOrderId).get();
    }

    public List<OrderItem> getOrderItemsByOrderId(long id){
        return orderItemRepository.findByOrdersId(id);
    }

    @Override
    public void delete(long id) {

    }

    public long getLastOrderId() {
        long lastInsertedId = 0;
        List<Orders> orders = ordersRepository.findAll();
        if (orders.size() != 0) {
            Orders lastOrder = orders.get(orders.size() - 1);
            lastInsertedId = lastOrder.getId();
        }
        return lastInsertedId;
    }

    public int getLastOrderNumber() {
        List<Orders> orders = ordersRepository.findAll();
        if (orders.size() != 0) {
            Orders lastOrder = orders.get(orders.size() - 1);
            orderNumber = lastOrder.getNumber();
        }
        System.out.println(orderNumber);
        return orderNumber += 1;
    }
}
