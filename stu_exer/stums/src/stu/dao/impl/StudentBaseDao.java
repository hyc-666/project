package stu.dao.impl;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import stu.pojo.Student;
import stu.utils.JDBCUtils;

import java.nio.channels.AsynchronousByteChannel;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * @author hyc
 * @date 2020/11/12
 */
public abstract class StudentBaseDao {
    private QueryRunner queryRunner = new QueryRunner();

    /**
     * 数据更新操作,包括增删改
     * @param sql sql语句
     * @param args 可变参数列表
     * @return 返回执行结果影响的行数,返回-1表示执行失败
     */
    public int update(String sql,Object ... args){
        Connection conn = null;
        try {
            conn = JDBCUtils.getConnection();
            return queryRunner.update(conn,sql,args);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            JDBCUtils.close(conn);
        }
        return -1;
    }

    /**
     * 查询单个数据,查询某一行
     * @param type Javabean的类
     * @param sql sql语句
     * @param args 可变参数列表
     * @param <T> 指定泛型类
     * @return 返回查询的泛型
     */
    public <T>T queryForOne(Class<T> type,String sql,Object ... args){
        Connection conn = JDBCUtils.getConnection();
        try {
            return queryRunner.query(conn,sql,new BeanHandler<T>(type),args);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JDBCUtils.close(conn);
        }
        return null;
    }

    /**
     * 将查询结果封装为JavaBean并且以list方式返回
     * @param type 基础类型
     * @param sql sql
     * @param args args
     * @param <T> 泛型类
     * @return 返回封装好的一个list
     */
    public <T>List<T> queryForList(Class<T> type,String sql,Object ... args){
        Connection conn = JDBCUtils.getConnection();
        try {
            return queryRunner.query(conn,sql,new BeanListHandler<T>(type),args);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JDBCUtils.close(conn);
        }
        return null;
    }
}
