package book.test;

import book.dao.OrderItemDao;
import book.dao.impl.OrderItemDaoImpl;
import book.pojo.OrderItem;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

/**
 * @author hyc
 * @date 2021/5/8
 **/
public class OrderItemDaoTest {
    private OrderItemDao orderItemDao = new OrderItemDaoImpl();
    @Test
    public void saveOrderItem() {
        OrderItem orderItem = new OrderItem(null,"book01",1,
                new BigDecimal(100),new BigDecimal(100),"order00");
        System.out.println(orderItemDao.saveOrderItem(orderItem));
    }
}