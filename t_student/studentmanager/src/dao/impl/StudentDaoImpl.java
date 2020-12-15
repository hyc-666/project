package dao.impl;

import dao.StudentDao;
import pojo.Student;

import java.util.List;

/**
 * @author hyc
 * @date 2020/11/4
 **/
public class StudentDaoImpl extends StudentBaseDao implements StudentDao {

    /**
     * 添加新字段
     * @param student 传入封装好的实体类对象
     * @return 返回-1表示没有添加成功
     */
    @Override
    public int addStudent(Student student) {
        String sql = "insert into s_student(`s_number`,`s_name`,`s_password`,`s_college`,`s_class`)values(?,?,?,?,?);";
        return update(sql,student.getS_number(),student.getS_name(),student.getS_password(),student.getS_college(),student.getS_class());
    }

    /**
     * 依据学号进行删除
     * @param student 实体类对象
     * @return 返回-1则表示删除失败
     */
    @Override
    public int deleteStudentByS_number(Student student) {
        String sql = "delete from s_student where s_number = ?";
        return update(sql,student.getS_number());
    }

    /**
     * 更新信息
     * @param student 只更新需要的,不跟新的一律按照原样填回去
     * @return 返回-1表示没有更新成功
     */
    @Override
    public int updateStudent(Student student) {
        String sql = "update s_student set `s_name` = ?,`s_password` = ?,`s_college` = ?,`s_class` = ? where s_number = ?";
        return update(sql,student.getS_name(),student.getS_password(),student.getS_college(),student.getS_class(),student.getS_number());
    }

    /**
     * 依据学号进行查询,查询到单个
     * @param s_number 学号
     * @return 返回查询到的实体类对象
     */
    @Override
    public Student queryStudentByS_number(String s_number) {
        String sql = "select * from s_student where s_number = ?";
        return queryForOne(Student.class,sql,s_number);
    }

    /**
     * 查询返回整个列表
     * @return
     */
    @Override
    public List<Student> queryStudents() {
        String sql = "select * from s_student";
        return queryForList(Student.class,sql);
    }

    @Override
    public Student queryStudentByS_numberAndS_password(Student student) {
        String sql = "select * from s_student where s_number = ? and s_password = ?";
        return queryForOne(Student.class,sql,student.getS_number(),student.getS_password());
    }
}
