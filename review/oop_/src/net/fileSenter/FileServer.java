package net.fileSenter;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

/** 服务端接受客户端发来的文件
 * @author hyc
 * @date 2021/4/15
 */
public class FileServer {
    public static void main(String[] args) {
        ServerSocket ss = null;//服务端socket
        Socket socket = null;//接收到来自客户端的socket
        InputStream is = null;//客户端输入到服务端的stream
        OutputStream os = null;//服务端输出到客户端的stream
        FileOutputStream fos = null;//服务端输出文件到本地的stream
        BufferedOutputStream bos = null;//服务端文件输出缓冲流
        /**
         * 查看本机ip
            try {
                System.out.println(InetAddress.getLocalHost().getHostAddress());
            } catch (UnknownHostException e) {
                e.printStackTrace();
            }*/
        try {
            ss = new ServerSocket(45540);//创建服务端socket
            socket = ss.accept();//获取与服务端连接的socket
            is = socket.getInputStream();//获得与服务端连接的socket的输入流,相对于服务器来说是输入
            //创建本地目录,一定得是个文件,不能是目录
            File file = new File("oop_/src/net/fileSenter/recive.png");
            fos = new FileOutputStream(file);//本地文件输出流
            //创建文件缓冲流
            bos = new BufferedOutputStream(fos);//缓冲流
            byte[] buffer = new byte[64];
            //将接收到的数据写入缓冲流
            int len;
            while ((len = is.read(buffer)) != -1){//从socket获得的输入流读出数据到buffer字节数组
                bos.write(buffer,0,len);//写入本地缓冲流
            }
            bos.flush();//刷新缓冲到文件
            System.out.println("接收到来自" + socket.getInetAddress() + "的文件!");

            //将反馈信息发送给客户端
            os = socket.getOutputStream();
            os.write("文件已收到".getBytes());

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //关闭资源
            if (bos != null){
                try {
                    bos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fos != null){
                try {
                    fos.close();
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
            if (os != null){
                try {
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (socket != null){
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (ss != null){
                try {
                    ss.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
