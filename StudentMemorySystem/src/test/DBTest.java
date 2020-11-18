package test;

import dao.StudentDao;
import dao.impl.StudentDaoImpl;
import pojo.Student;
import utils.JDBCUtils;

import java.sql.Connection;
import java.util.List;

/**
 * @author hyc
 * @date 2020/11/18
 */
public class DBTest {
    public static void main(String[] args) {
        StudentDao studentDao = new StudentDaoImpl();
        List<Student> students = studentDao.queryStudents();
        for (Student s : students){
            System.out.println(s);
        }
    }
}
