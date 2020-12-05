package oop.obj;
//main方法的使用和说明
/**
 * @author hyc
 * @date 2020/12/5
 */
public class MainTest {
    public static void main(String[] args) {
        Main.main(new String[15]);
    }
}
class Main{
    //main方法是一个程序的入口,同时main方法也是一个方法
    //main方法声明为static的,可以通过类名调用
    public static void main(String[] args) {
        for (int i = 0; i < args.length; i++) {
            args[i] = "args_" + i;
            System.out.println(args[i]);
        }
    }
}