package com.hyc.pojo;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author hyc
 * @date 2021/5/8
 **/
public class StudentTest {
    @Test
    public void studentTest01(){
        //使用反射创造对象
        Class<Student> clazz = Student.class;
        try {
            Constructor<Student> cons = clazz.getDeclaredConstructor();
            cons.setAccessible(true);
            Method setNameMethod = clazz.getDeclaredMethod("setName", String.class);
            setNameMethod.setAccessible(true);
            Method setAgeMethod = clazz.getDeclaredMethod("setAge", Integer.class);
            setAgeMethod.setAccessible(true);
            Method setIdMethod = clazz.getDeclaredMethod("setId", Integer.class);
            setIdMethod.setAccessible(true);

            Student student = cons.newInstance();
            setNameMethod.invoke(student,"Jerry");
            setAgeMethod.invoke(student,18);
            setIdMethod.invoke(student,3);

            System.out.println(student);

        } catch (Exception e) {
            e.printStackTrace();
        }


        System.out.println("========使用spring创建对象==========");
        String config = "beans.xml";
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext(config);
        Student student1 = (Student) applicationContext.getBean("student");
        System.out.println(student1);
    }
    @Test
    public void refTest(){
        String config = "beans.xml";
        ApplicationContext ac = new ClassPathXmlApplicationContext(config);
        Student student = (Student) ac.getBean("student");
        System.out.println(student);
    }
}
