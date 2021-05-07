package reflect.demo;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/** 反射调用对象的私有构造器,属性和方法
 * @author hyc
 * @date 2021/4/16
 */
public class ReflectPrivateTest {
    public static void main(String[] args) {
        Class<Student> clazz = Student.class;

        try {
            //调用私有构造器
            Constructor<Student> cons = clazz.getDeclaredConstructor(String.class);
            cons.setAccessible(true);
            Student stu = cons.newInstance("Tony");
            System.out.println(stu);

            //调用私有属性
            Field field = clazz.getDeclaredField("age");
            field.setAccessible(true);
            field.set(stu,12);
            System.out.println(stu);

            //调用私有方法
            Method method = clazz.getDeclaredMethod("show",int.class);
            method.setAccessible(true);
            method.invoke(stu,180);

        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
