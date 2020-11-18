package utils;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 * @author hyc
 * @date 2020/10/31
 */
public class JDBCUtils {

    private static DataSource dataSource;
    /**
    //将jdbc.properties的信息作为类静态成员由静态代码块在程序启动时读取进来
    private static String url;
    private static String user;
    private static String password;
    private static String driver;//数据库驱动driver也作为静态资源读取进来

    //使用静态代码块获取资源连接对象
    static {
        try {
            Properties properties =new Properties();
            //properties是直接映射到src的文件目录下,这样可以避免直接写相对路径或者绝对路径而带来额外的路径问题,
            // 如程序移植或者迁移到其他平台的问题
            ClassLoader classLoader = JDBCUtils.class.getClassLoader();//获取当前类的类加载器
            //直接获取jdbc.properties配置文件的url,这个url是在src目录下的
            URL resource = classLoader.getResource("jdbc.properties");//由文件名获取到文件路径的url
            //将路径url转成字符串,还要断言非空,即url不能为空,否则启动失败
            assert resource != null;
            String path = resource.getPath();//将路径url转为字符串
            //加载文件,读取文件内容
            FileReader fileReader = new FileReader(path);
            //读取文件内容,按照key-value的形式由键值对获取到参数
            properties.load(fileReader);
            url = properties.getProperty("url");
            user = properties.getProperty("username");
            password = properties.getProperty("password");
            driver = properties.getProperty("driver");
            //注册驱动
            Class.forName(driver);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
     */
    static {
        try {
            Properties pro = new Properties();
            InputStream inputStream =JDBCUtils.class.getClassLoader().getResourceAsStream("jdbc.properties");
            pro.load(inputStream);
            dataSource = DruidDataSourceFactory.createDataSource(pro);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     *
     * @return 返回数据库连接的对象
     * @throws SQLException
     */
    public static Connection getConnection()  {
        Connection conn = null;
        try {
            conn = dataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
//        try {
//            conn =  DriverManager.getConnection(url,user,password);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
        return conn;
    }

    /**
     * 用于关闭数据库连接
     * @param statement 数据库执行语句
     * @param connection 数据库连接
     */
    public static void close(Statement statement, Connection connection) {
        //资源的关闭顺序也不能错,先关闭statement,再关闭connection
        if (statement != null){
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (connection != null){
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        //两个关闭资源的操作要分开写,不能写在一起
    }

    /**
     * 重载的关闭资源的方法,可以将resultSet资源也一并关闭
     * @param resultSet 数据库查询结果集
     * @param statement 数据库执行语句
     * @param connection 数据库连接对象
     */
    public static void close(ResultSet resultSet, Statement statement, Connection connection){
        //同样注意资源关闭顺序,并且也要分开写,不能写在一个try-catch里
        if (resultSet != null){
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (statement != null){
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (connection != null){
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    public static void close(Connection conn){
        if (conn != null){
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
