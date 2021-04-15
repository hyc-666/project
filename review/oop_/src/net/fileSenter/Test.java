package net.fileSenter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author hyc
 * @date 2021/4/15
 */
public class Test {
    public static void main(String[] args) {
        File file = new File("test.txt");
        FileOutputStream fis = null;
        try {
             fis = new FileOutputStream(file);
            fis.write("测试文件".getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fis != null){
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
