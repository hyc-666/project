package book.web;

import book.pojo.Cart;
import book.pojo.User;
import book.service.OrderService;
import book.service.impl.OrderServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.SessionCookieConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author hyc
 * @date 2021/5/8
 **/
public class OrderServlet extends BaseServlet{
    OrderService orderService = new OrderServiceImpl();

    protected void createOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //从session域中获得Cart对象和User对象
        HttpSession session = req.getSession();
        Cart cart = (Cart) session.getAttribute("cart");
        User loginUser = (User) session.getAttribute("user");
        //如果用户没有登录,获取不到userID的值,需要请求重定向到登录页面提示用户登录
        if (loginUser == null){
            req.getRequestDispatcher("pages/user/login.jsp").forward(req,resp);
            return;
        }
        //调用OrderService.createOrder()方法生成和保存订单
        String orderId = orderService.createOrder(cart, loginUser.getId());
        //得到订单号将订单号保存到session域中
        session.setAttribute("orderId",orderId);
        //请求重定向到"pages/cart/checkout.jsp
//        req.getRequestDispatcher("pages/cart/checkout.jsp").forward(req,resp);
        //使用请求重定向防止多次生成订单
        resp.sendRedirect(req.getContextPath() + "/pages/cart/checkout.jsp");
    }
}
