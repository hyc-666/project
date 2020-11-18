package test;

import dao.StudentDao;
import dao.impl.StudentDaoImpl;
import org.junit.Test;
import pojo.Student;

import static org.junit.Assert.*;

/**
 * @author hyc
 * @date 2020/11/17
 */
public class StudentDaoTest {

    StudentDao studentDao = new StudentDaoImpl();
    @Test
    public void addStudent() {
        Student s = new Student("342468521","刘备","liubei");
        studentDao.addStudent(s);
    }

    @Test
    public void deleteStudentByS_number() {
    }

    @Test
    public void updateStudent() {
    }

    @Test
    public void queryStudentByS_number() {
    }

    @Test
    public void queryStudents() {
    }
}