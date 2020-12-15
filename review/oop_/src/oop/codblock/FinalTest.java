package oop.codblock;

/** final 的用法
 * @author hyc
 * @date 2020/12/15
 * final修饰类表示此类不可被继承
 * final修饰方法表示方法不可被重写
 * final修饰变量表示常量
 * final修饰形参表示形参是一个常量,可以使用但不可更改
 */
public class FinalTest {
    public int addOne(final int x){
//        return ++x;//错误!final不可被修改
        return x + 1;
    }

}
/**
 * final 修饰形参对象,表示此对象不可变,但是对象内部的有些属性可以改变
 * 例如如下代码:
 */
class Other{
    public int i;
}
class SomeThing{
    public static void main(String[] args) {

    }

    public int addOne(final Other o){
//        o = new Other();//不可被重新赋值
        return o.i++;//操作合法
    }
}