package book.filter;

import book.utils.JdbcUtils;

import javax.servlet.*;
import java.io.IOException;

/**
 * @author hyc
 * @date 2021/5/9
 **/
public class TransactionFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        try {
            filterChain.doFilter(servletRequest,servletResponse);
            //此处提交事务
            JdbcUtils.commitAndClose();
        } catch (Exception e) {
            e.printStackTrace();
//            此处回滚事务
            JdbcUtils.rollbackAndClose();

            //而且仍然需要把错误扔出去,tomcat才会跳转到错误页面!!
            throw new RuntimeException(e);
        }
    }

    @Override
    public void destroy() {

    }
}
