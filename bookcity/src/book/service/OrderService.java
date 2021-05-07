package book.service;

import book.pojo.Cart;

/**
 * @author hyc
 * @date 2021/5/8
 **/
public interface OrderService {
    String createOrder(Cart cart,Integer userId);
}
