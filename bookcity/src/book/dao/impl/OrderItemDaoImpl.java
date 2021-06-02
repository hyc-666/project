package book.dao.impl;

import book.dao.OrderItemDao;
import book.pojo.Order;
import book.pojo.OrderItem;
import book.utils.JdbcUtils;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author hyc
 * @date 2021/5/8
 **/
public class OrderItemDaoImpl extends BaseDao implements OrderItemDao {
    @Override
    public int saveOrderItem(OrderItem orderItem) {
        String sql = "insert into t_order_item(`name`,`count`,`price`,`total_price`,`order_id`) values(?,?,?,?,?)";
        return update(sql,orderItem.getName(),orderItem.getCount(),
                orderItem.getPrice(),orderItem.getTotalPrice(),orderItem.getOrderId());
    }

    @Override
    public List<OrderItem> queryOrderItemsByOrderId(String orderId) throws SQLException {
        String sql = "select `id`,`name`,`count`,`price`,`total_price`,`order_id` from t_order_item where order_id = ?";
        //由于现在数据库表中定义的字段名和实体类中定义的字段名不一致,导致底层在查询到数据进行封装成对象的时候
        //字段名相互有对不上的,会导致封装对象时有些值不能成功注入,因此有两种解决办法:
        //1.重新定义数据库中的字段名或者实体类中的字段名,但是都需要在现有的代码中的很多地方做出相应的改动
        //2.封装成类的方法在这一层手动实现,其他地方的代码不需要更改
        Connection conn = JdbcUtils.getConnection();
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1,orderId);
        ResultSet resultSet = statement.executeQuery();
        List<OrderItem> orderItemList = new ArrayList<>();
        while (resultSet.next()){
            Integer id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            Integer count = resultSet.getInt("count");
            BigDecimal price = resultSet.getBigDecimal("price");
            BigDecimal totalPrice = resultSet.getBigDecimal("total_price");
            OrderItem item = new OrderItem(id,name,count,price,totalPrice,orderId);
            orderItemList.add(item);
        }
//        return queryForList(OrderItem.class,sql,orderId);
        return orderItemList;
    }
}
