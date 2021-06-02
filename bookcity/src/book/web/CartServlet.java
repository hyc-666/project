package book.web;

import book.pojo.Book;
import book.pojo.Cart;
import book.pojo.CartItem;
import book.service.BookService;
import book.service.impl.BookServiceImpl;
import book.utils.WebUtils;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author hyc
 * @date 2021/5/7
 **/
public class CartServlet extends BaseServlet{

    private BookService bookService = new BookServiceImpl();

    /**
     * 修改商品数量
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void updateCount(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取请求的参数
        int count = WebUtils.parseInt(req.getParameter("count"),0);
        int id = WebUtils.parseInt(req.getParameter("id"),0);
        //获取session
        HttpSession session = req.getSession();
        Cart cart = (Cart)session.getAttribute("cart");
        if (cart != null){
            cart.updateCount(id,count);
            //然后跳转到购物车页面
            resp.sendRedirect(req.getHeader("Referer"));
        }
    }

    /**
     * 清空购物车
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void clear(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //清空购物车不需要参数
        //获取session
        HttpSession session = req.getSession();
        Cart cart = (Cart)session.getAttribute("cart");
        if (cart != null){
            cart.clear();
            //然后跳转到购物车页面
            resp.sendRedirect(req.getHeader("Referer"));
        }
    }
    /**
     * 删除商品项
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void deleteItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取请求的参数
        int id = WebUtils.parseInt(req.getParameter("id"),0);
        //获取session
        HttpSession session = req.getSession();
        //从session中获取cart对象
        Cart cart = (Cart) session.getAttribute("cart");
        if (cart != null){
            cart.deleteItem(id);
            //请求重定向到购物车页面
            resp.sendRedirect(req.getHeader("Referer"));
        }
    }

    /**
     * 添加商品
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void addItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取请求的参数,商品id
        int id = WebUtils.parseInt(req.getParameter("id"),0);
        //调用bookService.queryBookById()得到图书信息
        Book book = bookService.queryBookById(id);
        //将图书信息封装成CartItem对象
        CartItem cartItem = new CartItem(book.getId(),book.getName(),1,book.getPrice(),book.getPrice());
        //调用addItem()方法把商品项添加到购物车
        //查看是否有购物车
        HttpSession session = req.getSession();
        Cart cart = (Cart) session.getAttribute("cart");
        if (cart == null){
            cart = new Cart();
            //将此cart放入session中
            session.setAttribute("cart",cart);
        }
        cart.addItem(cartItem);
        //将最后一次添加的图书保存到session域中
        session.setAttribute("lastName",cartItem.getName());
        //页面重定向到原来的商品页面
//        System.out.println(req.getHeader("Referer"));
        resp.sendRedirect(req.getHeader("Referer"));
    }

    /**
     * 现在使用ajax请求添加商品项到购物车
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void ajaxAddItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取请求的参数,商品id
        int id = WebUtils.parseInt(req.getParameter("id"),0);
        //调用bookService.queryBookById()得到图书信息
        Book book = bookService.queryBookById(id);
        //将图书信息封装成CartItem对象
        CartItem cartItem = new CartItem(book.getId(),book.getName(),1,book.getPrice(),book.getPrice());
        //调用addItem()方法把商品项添加到购物车
        //查看是否有购物车
        HttpSession session = req.getSession();
        Cart cart = (Cart) session.getAttribute("cart");
        if (cart == null){
            cart = new Cart();
            //将此cart放入session中
            session.setAttribute("cart",cart);
        }
        cart.addItem(cartItem);
        //将最后一次添加的图书保存到session域中
        session.setAttribute("lastName",cartItem.getName());

        //只不过不适用重定向请求整个页面,只返回页面需要的数据
        Map<String,Object> resultMaP = new HashMap<String,Object>();
        //返回购物车的总商品数量
        resultMaP.put("totalCount",cart.getTotalCount());
        //返回最后一次添加的商品名称
        resultMaP.put("lastName",cartItem.getName());
        Gson gson = new Gson();
        String json = gson.toJson(resultMaP);
        //返回请求信息
        resp.getWriter().write(json);
    }
}
