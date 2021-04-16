package net.fileSenter;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

/** 客户端发送文件到服务端,服务端将文件保存到本地
 * @author hyc
 * @date 2021/4/15
 */
public class FileClient {
    public static void main(String[] args) {
        Socket client = null;//客户端socket
        OutputStream os = null;//客户端输出到服务端的stream
        InputStream is = null;//服务端输入到客户端的stream
        FileInputStream fis = null;//本地文件输入流
        ByteArrayOutputStream baos = null;//接受客户端的字节流,将其缓冲到缓冲区,以便于转为字符输出
        BufferedInputStream bis = null;//本地读入文件的缓冲流
        try {
            //取得服务端 InetAddress

            InetAddress address = InetAddress.getByName("192.168.197.1");
            client = new Socket(address,45540);//建立socket,指定端口号和ip
            os = client.getOutputStream();//获得输出流
            File file = new File("oop_/雨碎江南.png");//取得本地文件的路径和具体文件
            //读入文件
            fis = new FileInputStream(file);
            //写入缓冲
            bis = new BufferedInputStream(fis);
            byte[] buffer = new byte[64];
            int len;
            while ((len = bis.read(buffer)) != -1){//从缓冲中将数据写入buffer字节数组
                os.write(buffer,0,len);//将字节数组buffer写入从socket获得的输出流
            }
            System.out.println("文件已发送完成");
            //发送完文件以后客户端要明确将完成信息传递给服务端,否则服务端的read方法会一直阻塞等待客户端再次发送信息
            client.shutdownOutput();//告知服务端完成发送

            is = client.getInputStream();//获得输入流,接受服务器反馈回来的消息
            //使用ByteArrayOutputStream 将字节流转换为字符流
            baos = new ByteArrayOutputStream();
            while ((len = is.read(buffer)) != -1){
                baos.write(buffer,0,len);
            }
            System.out.println("接收到来自服务端的反馈:\n" + baos.toString());

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //关闭资源
            if (bis != null){
                try {
                    bis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fis != null){
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (os != null){
                try {
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (is != null){
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (baos != null){
                try {
                    baos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (client != null){
                try {
                    client.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
