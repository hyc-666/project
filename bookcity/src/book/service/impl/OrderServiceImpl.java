package book.service.impl;

import book.dao.BookDao;
import book.dao.OrderDao;
import book.dao.OrderItemDao;
import book.dao.impl.BookDaoImpl;
import book.dao.impl.OrderDaoImpl;
import book.dao.impl.OrderItemDaoImpl;
import book.pojo.*;
import book.service.OrderService;

import java.util.Date;
import java.util.Map;

/**
 * @author hyc
 * @date 2021/5/8
 **/
public class OrderServiceImpl implements OrderService {
    private OrderDao orderDao = new OrderDaoImpl();
    private OrderItemDao orderItemDao = new OrderItemDaoImpl();
    private BookDao bookDao = new BookDaoImpl();
    @Override
    public String createOrder(Cart cart, Integer userId) {
        //订单号要唯一
        String orderId = System.currentTimeMillis() + "_" + userId;
        //创建和生成订单
        Order order = new Order(orderId,new Date(),cart.getTotalPrice(),0,userId);
        //保存订单
        orderDao.saveOrder(order);
        //将购物车中的每一个cartItem转成OrderItem保存到数据库
        for (Map.Entry<Integer, CartItem> entry : cart.getItems().entrySet()){
            CartItem cartItem = entry.getValue();
            OrderItem orderItem = new OrderItem(null,cartItem.getName(),cartItem.getCount(),
                    cartItem.getPrice(),cartItem.getTotalPrice(),orderId);
            //保存订单项
            orderItemDao.saveOrderItem(orderItem);
            //然后还要更新库存和销量
            Book book = bookDao.queryBookById(cartItem.getId());
            book.setSales(book.getSales() + cartItem.getCount());
            book.setStock(book.getStock() - cartItem.getCount());
            bookDao.updateBook(book);
        }
        //结账完成后要清空购物车
        cart.clear();
        //返回订单号
        return orderId;
    }
}
