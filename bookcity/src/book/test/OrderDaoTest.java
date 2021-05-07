package book.test;

import book.dao.OrderDao;
import book.dao.impl.OrderDaoImpl;
import book.pojo.Order;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Date;

import static org.junit.Assert.*;

/**
 * @author hyc
 * @date 2021/5/8
 **/
public class OrderDaoTest {
    private OrderDao orderDao = new OrderDaoImpl();
    @Test
    public void saveOrder() {
        Order order = new Order("order01", new Date(), new BigDecimal(100), 0, 10);
        System.out.println(orderDao.saveOrder(order));
    }
}