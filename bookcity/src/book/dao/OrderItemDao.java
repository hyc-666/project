package book.dao;

import book.pojo.OrderItem;

/**
 * @author hyc
 * @date 2021/5/8
 **/
public interface OrderItemDao {
    int saveOrderItem(OrderItem orderItem);
}
