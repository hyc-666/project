package book.service;

import book.pojo.Cart;
import book.pojo.Order;
import book.pojo.OrderItem;

import java.sql.SQLException;
import java.util.List;

/**
 * @author hyc
 * @date 2021/5/8
 **/
public interface OrderService {
    String createOrder(Cart cart,Integer userId);
    List<Order> queryOrdersByUserId(Integer userId) throws SQLException;
    List<Order> queryOrders() throws SQLException;
    List<OrderItem> queryOrderItemsByOrderId(String orderId) throws SQLException;

    String queryUsernameByUserId(Integer userId);

    int updateStatus(int status,String orderId);
}
