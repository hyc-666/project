package ctrl;

import pojo.Student;
import service.StudentService;
import service.impl.StudentServiceImpl;

/**
 * @author hyc
 * @date 2020/11/18
 */
public class BaseCtrl {
    private StudentService studentService = new StudentServiceImpl();

    public Student queryStudentBynNuAndPs(Student student){
        return studentService.queryStudentByNumberAndPassword(student);
    }
}
