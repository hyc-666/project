package book.dao;

import book.pojo.Order;

import java.sql.SQLException;
import java.util.List;

/**
 * @author hyc
 * @date 2021/5/8
 **/
public interface OrderDao {
    int saveOrder(Order order);
    //根据用户id查询订单
    List<Order> queryOrdersByUserId(Integer userId) throws SQLException;
    List<Order> queryOrders() throws SQLException;

    String queryUsernameByUserId(Integer userId);

    int updateStatus(int status,String orderId);
}
