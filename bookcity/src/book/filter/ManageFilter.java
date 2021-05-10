package book.filter;

import book.pojo.User;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/** 使用filter过滤器拦截影虎未登录的请求
 * 并且拦截非管理员访问后台管理的请求
 * @author hyc
 * @date 2021/5/9
 **/
public class ManageFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        //强转为HttpServletRequest
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        //获取session对象
        HttpSession session = req.getSession();
        //从session域中获取登录的用户
        User user = (User) session.getAttribute("user");
        if (user == null){
            //如果用户还没有登录要先让用户登录,跳转到用户登录界面
            req.getRequestDispatcher("/pages/user/login.jsp").forward(servletRequest,servletResponse);
        } else{
            filterChain.doFilter(servletRequest,servletResponse);
        }

    }

    @Override
    public void destroy() {

    }
}
