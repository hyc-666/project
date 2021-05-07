package book.test;

import book.pojo.Cart;
import book.pojo.CartItem;
import org.junit.Test;
import sun.util.resources.et.CalendarData_et;

import java.math.BigDecimal;
import java.net.Inet4Address;
import java.net.UnknownHostException;

import static org.junit.Assert.*;

/**
 * @author hyc
 * @date 2021/5/7
 **/
public class CartTest {
    Cart cart = new Cart();
    @Test
    public void addItem() {
        cart.addItem(new CartItem(1,"java核心技术",1,new BigDecimal(200),new BigDecimal(200)));
        cart.addItem(new CartItem(1,"java核心技术",1,new BigDecimal(200),new BigDecimal(200)));
        cart.addItem(new CartItem(2,"java编程思想",1,new BigDecimal(100),new BigDecimal(100)));
        System.out.println(cart);
    }

    @Test
    public void deleteItem() throws UnknownHostException {
        cart.addItem(new CartItem(1,"java核心技术",1,new BigDecimal(200),new BigDecimal(200)));
        cart.addItem(new CartItem(1,"java核心技术",1,new BigDecimal(200),new BigDecimal(200)));
        cart.addItem(new CartItem(2,"java编程思想",1,new BigDecimal(100),new BigDecimal(100)));
        cart.deleteItem(1);
        System.out.println(Inet4Address.getLocalHost());
        System.out.println(cart);
    }

    @Test
    public void clear() {
        cart.addItem(new CartItem(1,"java核心技术",1,new BigDecimal(200),new BigDecimal(200)));
        cart.addItem(new CartItem(1,"java核心技术",1,new BigDecimal(200),new BigDecimal(200)));
        cart.addItem(new CartItem(2,"java编程思想",1,new BigDecimal(100),new BigDecimal(100)));
        cart.clear();
        System.out.println(cart);
    }

    @Test
    public void updateCount() {
        cart.addItem(new CartItem(1,"java核心技术",1,new BigDecimal(200),new BigDecimal(200)));
        cart.addItem(new CartItem(1,"java核心技术",1,new BigDecimal(200),new BigDecimal(200)));
        cart.addItem(new CartItem(2,"java编程思想",1,new BigDecimal(100),new BigDecimal(100)));
        cart.updateCount(1,10);
        System.out.println(cart);
    }
}