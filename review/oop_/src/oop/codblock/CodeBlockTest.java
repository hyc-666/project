package oop.codblock;

/** 代码块的使用
 * @author hyc
 * @date 2020/12/7
 *
 * 代码块有静态代码块和费静态代码块
 *
 * 静态代码块在类加载时自动执行,只会执行一次
 * 非静态代码块在对象创建时自定执行,每次创建对象都会被执行一次.
 * 不能显示调用代码块的执行.
 *
 *
 * 非静态代码块可以用来初始化成员信息,关于初始化顺序:
 * 代码块的初始化顺序和就地初始化的顺序取决于声明的顺序
 */
public class CodeBlockTest {
    public static void main(String[] args) {
        //观察执行顺序
        CodeBlock codeBlock = new CodeBlock();
        System.out.println(CodeBlock.className);

    }
}

class CodeBlock{
    /*
    int id = 3;
    {
        id = 4;
    }
    * 在这种情况下,是先执行id = 3,后执行 id = 4
     */

    {
        id = 4;
    }
    int id = 3;
    //在这种情况下是先执行id = 4,后执行id = 3
    String name;
    static String className = CodeBlock.class.getName();

    {
        System.out.println("非静态的代码块");
    }

    static {
        System.out.println("static修饰的静态代码块");
    }
}
