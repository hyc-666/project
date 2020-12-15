package service.impl;

import dao.StudentDao;
import dao.impl.StudentDaoImpl;
import pojo.Student;
import service.StudentService;

import java.util.List;

/**
 * @author hyc
 * @date 2020/11/17
 */
public class  StudentServiceImpl implements StudentService {
    StudentDao studentDao = new StudentDaoImpl();
    @Override
    public void addStudent(Student student) {
        studentDao.addStudent(student);
    }

    @Override
    public void deleteStudent(Student student) {
        studentDao.deleteStudentByS_number(student);
    }

    @Override
    public void updateStudent(Student student) {
        studentDao.updateStudent(student);
    }

    @Override
    public Student queryStudentByNumber(String number) {
        return studentDao.queryStudentByS_number(number);
    }

    @Override
    public List<Student> queryStudents() {
        return queryStudents();
    }

    @Override
    public Student queryStudentByNumberAndPassword(Student student) {
        return studentDao.queryStudentByS_numberAndS_password(student);
    }
}
