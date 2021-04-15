package net;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

/** 网络通信服务端
 * 启动时要先启动服务端,否则客户端会在建立连接时失败触发异常
 * @author hyc
 * @date 2021/4/15
 */
public class Server {
    public static void main(String[] args) {

        ServerSocket serverSocket = null;//服务端只需要指定端口号即可
        Socket clientSocket = null;//接受来自于客户端的socket
        InputStream clientInputStream = null;//获得客户端传来的输入流
        ByteArrayOutputStream baos = null;
        try {
            serverSocket = new ServerSocket(9920);

            clientSocket = serverSocket.accept();

            clientInputStream = clientSocket.getInputStream();

            baos = new ByteArrayOutputStream();//发过来的是字节数据,需要先缓冲然后再转换为字符串
            byte[] buffer = new byte[64];
            int len;
            while ((len = clientInputStream.read(buffer)) != -1){//将客户端发送过来的数据读入字节数组
                baos.write(buffer,0,len);//将字节数组写入缓冲区
            }
            System.out.println(baos.toString());//显示发送过来的数据
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (baos != null){
                try {
                    baos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (clientInputStream != null){
                try {
                    clientInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (clientSocket != null){
                try {
                    clientSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (serverSocket != null){
                try {
                    serverSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        //关闭流资源

    }
}
