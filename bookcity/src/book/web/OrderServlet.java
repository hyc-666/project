package book.web;

import book.pojo.*;
import book.service.OrderService;
import book.service.impl.OrderServiceImpl;
import book.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.*;

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
        //已经在所有工程路径下使用filter进行了事务管理,则不用在service层加
        String orderId = orderService.createOrder(cart, loginUser.getId());
        //得到订单号将订单号保存到session域中
        session.setAttribute("orderId",orderId);
        //请求重定向到"pages/cart/checkout.jsp
//        req.getRequestDispatcher("pages/cart/checkout.jsp").forward(req,resp);
        //使用请求重定向防止多次生成订单
        resp.sendRedirect(req.getContextPath() + "/pages/cart/checkout.jsp");
    }

    /**
     * 根据保存的用户id查询订单
     * @param
     */
    protected void queryOrdersByUserId(HttpServletRequest req, HttpServletResponse resp) throws SQLException, ServletException, IOException {
        HttpSession session = req.getSession();
        //获取当前登录的用户id
        Integer userId = WebUtils.parseInt(req.getParameter("userId"),1);
        //根据用户id查询到用户的所有订单
        List<Order> orderList = orderService.queryOrdersByUserId(userId);
        //将此用户的所有订单保存到session域中
        session.setAttribute("orderList",orderList);
        //请求重定向到order.jsp页面
        req.getRequestDispatcher("/pages/order/order.jsp").forward(req,resp);
    }

    /**
     * 根据订单号查询订单
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void queryOrderItemsByOrderId(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SQLException {
        HttpSession session = req.getSession();
        String orderId = req.getParameter("orderId");
        List<OrderItem> orderItemList = orderService.queryOrderItemsByOrderId(orderId);
        session.setAttribute("orderItemList",orderItemList);
        //页面转到详情页
        req.getRequestDispatcher("pages/order/order_details.jsp").forward(req,resp);
    }

    /**
     * 订单管理
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void orderManager(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SQLException {
        HttpSession session = req.getSession();
        //这里要查询两个信息,一个是查询所有的订单,另一个是要根据订单中的用户id查询到用户名
        //以方便管理员知道订单是哪个用户的
        //首先要查询所有的订单
        List<Order> orders = orderService.queryOrders();
        //将所有订单保存在session域中
//        session.setAttribute("orders",orders);
        //然后根据用户id查询用户名,将订单号与用户名建立关联
        //由于这里多次测试后始终不能关联,因此只能就地使用内部类重新封装订单详情.
        //然后存储到session域中
        List<UserOrder> userOrders = new ArrayList<>();
        for (Order order : orders){
            String username = orderService.queryUsernameByUserId(order.getUserId());
//            System.out.println(order.getUserId().toString());
            UserOrder userOrder = new UserOrder(username,order.getCreateTime(),order.getPrice(),
                    order.getStatus(),order.getOrderId());
            userOrders.add(userOrder);
//            session.setAttribute(order.getUserId().toString(),username);
        }
        //然后把这个关联关系也要保存到session域中.然后显示页面
        session.setAttribute("orderUsers",userOrders);
        req.getRequestDispatcher("pages/manager/order_manager.jsp").forward(req,resp);
    }

    /**
     * 完成订单,修改状态为已发货
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void complete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取请求的参数
        //根据订单号来查询订单,然后修改状态
        String orderId = req.getParameter("orderId");
        //根据订单号修改订单状态
        orderService.updateStatus(1,orderId);
        //刷新页面
        req.getRequestDispatcher("orderServlet?action=orderManager").forward(req,resp);
    }
}
