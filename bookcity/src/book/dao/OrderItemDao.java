package book.dao;

import book.pojo.OrderItem;

import java.sql.SQLException;
import java.util.List;

/**
 * @author hyc
 * @date 2021/5/8
 **/
public interface OrderItemDao {
    int saveOrderItem(OrderItem orderItem);

    List<OrderItem> queryOrderItemsByOrderId(String orderId) throws SQLException;
}
