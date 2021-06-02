package book.dao.impl;

import book.dao.OrderDao;
import book.pojo.Order;
import book.utils.JdbcUtils;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author hyc
 * @date 2021/5/8
 **/
public class OrderDaoImpl extends BaseDao implements OrderDao {
    @Override
    public int saveOrder(Order order) {
        String sql = "insert into t_order(`order_id`,`create_time`,`price`,`status`,`user_id`) values(?,?,?,?,?)";
        return update(sql,order.getOrderId(),order.getCreateTime(),order.getPrice(),order.getStatus(),order.getUserId());
    }

    @Override
    public List<Order> queryOrdersByUserId(Integer userId) throws SQLException {
        String sql = "select `order_id` , `create_time` , `price` , `status` , `user_id` from t_order where user_id = ?";
        Connection conn = JdbcUtils.getConnection();
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setInt(1,userId);
        ResultSet resultSet = statement.executeQuery();
        List<Order> orderList = new ArrayList<>();
        while (resultSet.next()){
            String orderId = resultSet.getString("order_id");
            Date createTime = resultSet.getDate("create_time");
            BigDecimal price = resultSet.getBigDecimal("price");
            Integer status = resultSet.getInt("status");
            Order order = new Order(orderId,createTime,price,status,userId);
            orderList.add(order);
        }
        return orderList;
    }

    @Override
    public List<Order> queryOrders() throws SQLException {
        String sql = "select `order_id` , `create_time` , `price` , `status` , `user_id` from t_order";
        Connection conn = JdbcUtils.getConnection();
        PreparedStatement statement = conn.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();
        List<Order> orderList = new ArrayList<>();
        while (resultSet.next()){
            String orderId = resultSet.getString("order_id");
            Date createTime = resultSet.getDate("create_time");
            BigDecimal price = resultSet.getBigDecimal("price");
            Integer status = resultSet.getInt("status");
            Integer userId = resultSet.getInt("user_id");
            Order order = new Order(orderId,createTime,price,status,userId);
            orderList.add(order);
        }
        return orderList;
    }

    @Override
    public String queryUsernameByUserId(Integer userId) {
        String sql = "select `username` from t_user where id = ?";
        return (String)queryForSingleValue(sql,userId);
    }

    @Override
    public int updateStatus(int status,String orderId) {
        String sql = "update t_order set `status` = ? where order_id = ?";
        return update(sql,status,orderId);
    }
}
