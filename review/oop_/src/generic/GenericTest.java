package generic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 泛型编程
 *
 * 泛型编程包括泛型类,泛型接口和泛型方法
 * @author hyc
 * @date 2021/4/5
 */
//泛型类指泛型类成员中有不确定的类型成员
public class GenericTest<T> {
    T data;

    public static void main(String[] args) {
        //在使用时必须带上泛型,不带则默认为Object类型
        Node node = new Node();
        Object data = node.getData();
        System.out.println(data);
        //带上泛型后则限制了类型,自动生成类型后是指定的类型
        Node<String> n = new Node<>();
        String data1 = n.getData();
        System.out.println(data1);
    }
}
class Node<T>{
    //比如单链表的数据域是一个不确定的类型
    T data;
    Node next;

    public Node(T data) {
        this.data = data;
    }

    public Node() {
    }

    /**
     * 并不是带泛型的就是泛型方法,下面这个只是一个普通方法,不是泛型方法
     * @return
     */
    public T getData(){
        return data;
    }

    /**
     * 泛型方法在声明的时候要声明为带泛型的,与上面普通方法不同
     * @param arr
     * @param <E>
     */
    public <E> List<E> show(E[] arr){
        for(E e : arr){
            System.out.println(e);
        }
        return Arrays.asList(arr);
    }
}