package oop.obj;

import sun.font.FontRunIterator;

/**
 * @author hyc
 * @date 2020/12/5
 */
public class ArgsTest {
    //可变长参数列表和数组形参一致.
    public static void main(String ... args) {
        System.out.println("helloword");
        //可以直接使用索引访问
        //配置参数时可以把参数掺进来,如果是命令行操作则把参数跟在运行命令后面即可,如:
        //java ArgsTest demo1 demo2
        //等等
        for (int i = 0; i < args.length; i++) {
            System.out.println(args[i]);
        }
    }

}
