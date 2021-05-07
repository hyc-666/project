package reflect.io;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author hyc
 * @date 2021/4/20
 */
public class LoadFile {
    public static void main(String[] args) {
        //通过反射来加载配置文件,配置文件的相对目录默认是在src目录下,而不是整个工程下
        ClassLoader loader = LoadFile.class.getClassLoader();
        InputStream stream = loader.getResourceAsStream("reflect/io/jdbc.properties");
        Properties pro = new Properties();
        try {
            pro.load(stream);
            String username = pro.getProperty("username");
            String password = pro.getProperty("password");

            System.out.println("username = " + username);
            System.out.println("password = " + password);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (stream != null){
                try {
                    stream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
