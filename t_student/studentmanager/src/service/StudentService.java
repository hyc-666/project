package service;

import pojo.Student;

import java.util.List;

/**
 * @author hyc
 * @date 2020/11/12
 */
public interface StudentService {
    public void addStudent(Student student);

    public void deleteStudent(Student student);

    public void updateStudent(Student student);

    public Student queryStudentByNumber(String number);

    public List<Student> queryStudents();

    public Student queryStudentByNumberAndPassword(Student student);
}
