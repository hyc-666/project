package book.utils;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.mysql.jdbc.log.NullLogger;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class JdbcUtils {

    private static DruidDataSource dataSource;
    private static ThreadLocal<Connection> cons = new ThreadLocal<>();

    static {
        try {
            Properties properties = new Properties();
            // 读取 jdbc.properties属性配置文件
            InputStream inputStream = JdbcUtils.class.getClassLoader().getResourceAsStream("jdbc.properties");
            // 从流中加载数据
            properties.load(inputStream);
            // 创建 数据库连接 池
            dataSource = (DruidDataSource) DruidDataSourceFactory.createDataSource(properties);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 获取数据库连接池中的连接
     * @return 如果返回null,说明获取连接失败<br/>有值就是获取连接成功
     */
    public static Connection getConnection(){

        //从threadLocal中获取连接对象,
        Connection conn = cons.get();

        if (conn == null){
            try {
                conn = dataSource.getConnection();
                cons.set(conn);//将连接对象放入到threadLocal中
                conn.setAutoCommit(false);//设置连接的事务管理为手动提交
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return conn;

        /**
        try {
            conn = dataSource.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }*/
    }

    /**
     * 提交事务并关闭链接
     */
    public static void commitAndClose(){
        Connection connection = cons.get();//链接从localThread中得到
        if (connection != null){//如果链接不为空则说明之前使用过这个链接操作过数据库
            try {
                connection.commit();//提交事务
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }finally {
                try {
                    connection.close();//关闭链接
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }
        cons.remove();
    }

    /**
     * 回滚事务并关闭链接
     */
    public static void rollbackAndClose(){
        Connection connection = cons.get();
        if (connection != null){
            try {
                connection.rollback();//回滚
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }finally {
                try {
                    connection.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }
        cons.remove();
    }



    /**
     * 关闭连接，放回数据库连接池
     * @param conn

    public static void close(Connection conn){
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    */
}