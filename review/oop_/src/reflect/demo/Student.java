package reflect.demo;

/**
 * @author hyc
 * @date 2021/4/16
 */
public class Student {
    public String name;
    private int age;

    public Student(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public Student() {
    }

    private Student(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
    private int show(int height){
        System.out.println(name + "身高:" + height + "cm");
        return height;
    }
    public void read(){
        System.out.println(name + "在阅读!");
    }
}
