package test;

import org.junit.Test;
import utils.JDBCUtils;

import java.sql.Connection;

import static org.junit.Assert.*;

/**
 * @author hyc
 * @date 2020/11/18
 */
public class JDBCUtilsTest {

    @Test
    public void getConnection() {
        Connection conn = JDBCUtils.getConnection();
        System.out.println(conn);
    }
}