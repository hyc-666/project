package stu.dao;

import stu.pojo.Student;

import java.util.List;

/**
 * @author hyc
 * @date 2020/11/3
 **/
public interface StudentDao {
    //增
    public int addStudent(Student student);
    //删
    public int deleteStudentByS_number(Student student);
    //改
    public int updateStudent(Student student);
    //查单个
    public Student queryStudentByS_number(String s_number);
    //查全部
    public List<Student> queryStudents();
    public Student queryStudentByS_numberAndS_password(Student student);
}
