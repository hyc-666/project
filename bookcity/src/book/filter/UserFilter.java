package book.filter;

import book.pojo.User;
import com.sun.deploy.net.HttpResponse;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author hyc
 * @date 2021/6/2
 **/
public class UserFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    /**
     * 过滤非管理员请求
     * @param servletRequest
     * @param servletResponse
     * @param filterChain
     * @throws IOException
     * @throws ServletException
     */
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
            //过滤非管理员用户的请求
            if(user.getId() != 1){//根据用户id来确定身份
                filterChain.doFilter(servletRequest,servletResponse);
            }else{//否则转回首页,提示用户权限不够
//                HttpResponse response = (HttpResponse) servletResponse;
                req.getRequestDispatcher("index.jsp?action=null").forward(req,servletResponse);
            }

        }
    }

    @Override
    public void destroy() {

    }
}
