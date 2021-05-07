package book.dao;

import book.pojo.Order;

/**
 * @author hyc
 * @date 2021/5/8
 **/
public interface OrderDao {
    int saveOrder(Order order);
}
