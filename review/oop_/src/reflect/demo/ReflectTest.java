package reflect.demo;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/** 反射入门
 * @author hyc
 * @date 2021/4/16
 */
public class ReflectTest {
    public static void main(String[] args) {
        //使用反射可以调用类的公有的构造器,方法和属性

        Class clazz = Student.class;

        try {
            Constructor cons = clazz.getDeclaredConstructor(String.class, int.class);

            //构造对象
            Object obj = cons.newInstance("Tom", 18);
            Student stu = (Student)obj;
            System.out.println(stu);

            //设置属性
            Field field = clazz.getField("name");
            field.set(stu,"Jerry");
            System.out.println(stu);

            //调用方法
            Method method = clazz.getMethod("read");
            method.invoke(stu);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
